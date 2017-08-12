/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mr.Aekasit Sengnualnim (Aek) Information System
 *
 * Siam Samsung Life Insurance Company Limited Charnissara II Tower 15 Floor,
 * 2922/222-227 Phetchaburi Road Tadmai Bangkapi, Huai Khwang, Bangkok 10310
 * THAILAND Tel : +66 0230 82245 #8242 Fax : +66 2298 0053 Mobile : +66 8912
 * 90006 Skype : s.aekasit Email : aekasit@siamsamsung.co.th MSN :
 * s.aekasit@hotmail.com http://www.siamsamsung.co.th
 *
 * @create 31-01-2554 13:27:31
 */
public class MD5Generator {

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String md5(String text) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }

    public static void main(String[] args) {
        try {
            String txt = md5("8888");
             System.out.println(txt);
            System.out.println(md5(md5("8888")));
            System.out.println(txt.length());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(MD5Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
