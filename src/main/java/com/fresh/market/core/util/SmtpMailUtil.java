/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author Adisorn.jo
 */
public class SmtpMailUtil {

    static Map<String, String> CONFIG;

    public static void send(String to, String subject, String message) throws EmailException {
        CONFIG = LoadConfig.loadFileDefault();
        HtmlEmail email = new HtmlEmail();
        email.setStartTLSEnabled(true);
        email.setHostName(CONFIG.get(LoadConfig._SMTP_HOST));
        email.setSmtpPort(NumberUtils.toInt(CONFIG.get(LoadConfig._SMTP_PORT)));
        email.setAuthenticator(new DefaultAuthenticator(CONFIG.get(LoadConfig._SMTP_USER), CONFIG.get(LoadConfig._SMTP_PASS)));
        email.setFrom(CONFIG.get(LoadConfig._SMTP_USER));
        email.setSubject(subject);
        email.setHtmlMsg(message);
        email.setCharset("utf-8");
        email.addTo(to);
        email.send();
    }
    
    
    public static void sendMuti(List<String> toBccs,String from, String subject, String message) throws EmailException {
        CONFIG = LoadConfig.loadFileDefault();
        HtmlEmail email = new HtmlEmail();
        email.setStartTLSEnabled(true);
        email.setHostName(CONFIG.get(LoadConfig._SMTP_HOST));
        email.setSmtpPort(NumberUtils.toInt(CONFIG.get(LoadConfig._SMTP_PORT)));
        email.setAuthenticator(new DefaultAuthenticator(CONFIG.get(LoadConfig._SMTP_USER), CONFIG.get(LoadConfig._SMTP_PASS)));
        email.setFrom(from);
        email.setSubject(subject);
        email.setHtmlMsg(message);
        email.setCharset("utf-8");
        for(String emailStr:toBccs) email.addBcc(emailStr);
        
        email.send();
    }
    
    
    

    public static void test(String to, String subject, String message) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setStartTLSEnabled(true);
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("noreply@bets789.com", "gfaojkiyd"));
        email.setFrom("Adisorn.jo.se@gmail.com");
        email.setSubject(subject);
        email.setHtmlMsg(message);
        email.setCharset("utf-8");
        
        email.addBcc("adisorn.jo@doctorgaming.com");
        email.addBcc("adison99@gmail.com");
        
        email.addTo(to);
        email.send();
    }
    
    public static void test1(List<String> to,String from, String subject, String message) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setStartTLSEnabled(true);
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("noreply@bets789.com", "gfaojkiyd"));
        email.setFrom(from);
        email.setSubject(subject);
        email.setHtmlMsg(message);
        email.setCharset("utf-8");
        
        
       // email.addBcc("adisorn.jo@doctorgaming.com");
       // email.addBcc("adison99@gmail.com");
        for(String emailStr:to){
           email.addBcc(emailStr);
        }
        
        
     
        email.send();
    }

    public static void main(String[] args) {
        try {
           
           //String[] bcc={"111111@doctorgaming.com","222222@gmail.com","333333@doctorgaming.com","444444@gmail.com","555555@gmail.com","666666@gmail.com","7777777@gmail.com"};
           //String[] from={"hirochi_new@hotmail.com","Adisorn.jo.se@gmail.com"};
           String aa="SEND_EMAIL5";
           System.out.println("==================>>>>"+aa.indexOf("SEND_EMAIL"));
           
           List<String> bcc=new ArrayList();
           bcc.add("111111@doctorgaming.com");
           bcc.add("222222@gmail.com");
           bcc.add("333333@gmail.com");
           bcc.add("444444@gmail.com");
           bcc.add("555555@gmail.com");
           bcc.add("666666@gmail.com");
           bcc.add("777777@gmail.com");
           
           List<String> from=new ArrayList();
           from.add("hirochi_new@hotmail.com");
           from.add("Adisorn.jo.se@gmail.com");
           
           
           int account=bcc.size();
           //int limit=2000;
           int limit=5;
           int warningRang=3;
           int rangEmail=from.size()*limit;
           boolean chkLimitEmail=true;
           if(rangEmail<bcc.size()){
                chkLimitEmail=false;
                System.out.println("=============== Mail Account Not Support Contact Admin =======================");
           }else if(rangEmail<=(bcc.size()+warningRang)){
                System.out.println("=============== Warning Account Not Support =======================");
           }
           
           if(chkLimitEmail){
                List<String> to=new ArrayList();
                int ct=0,ctfrom=0;
                for(int i=1;i<=account;i++){
                  //  if(ct==limit-1){
                   if(i%limit==0){
                       //to.add(bcc[i-1]);
                       to.add(bcc.get(i-1));
                       System.out.println("Email==>>"+to.toString()+"<==From==>"+from.get(ctfrom));
                      // test1(to,from[ctfrom], "test", "test");
                       to=new ArrayList();
                       ctfrom++;
                    }else{
                      to.add(bcc.get(i-1));
                    }
                }
                if(to.size()>0 ){
                    System.out.println("Email==>>"+to.toString()+"<==From==>"+from.get(ctfrom));
                }
           }
           System.out.println("==========================================================");      
           
           
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SmtpMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
