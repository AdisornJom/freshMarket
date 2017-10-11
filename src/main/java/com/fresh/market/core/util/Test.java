/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 *
 * @author Lenovo
 */
public class Test {
    public static void main(String[] args){
        try {
	  SimpleDateFormat dateFormat=new SimpleDateFormat("");
          GenerateExcelReport excel = new GenerateExcelReport();
          String headcol[] = {"จำนวนที่สั่งแยกตามบริษัท"};
          String column[] = {"AAAAAAAAAAAAAAA","B","C"};
          int row_=3;//select count detail
          
 	  String col[]=new String[column.length+1];
          String row[][] = new String[row_][column.length+1];
          int row_display=0;
          for (int i = 0; i < row_; i++) {
               int y=0; 
               row[row_display][y++] = excel.excelFormatString("Test"); 
               row[row_display][y++] = excel.excelFormatNumber("22");
               row[row_display][y++] = excel.excelFormatString("33"); 
               row[row_display][y++] = excel.excelFormatString("444"); 
               row_display++;
          }

         
          //wirte textfile
           String[] datetime={"12/12/12"};
           HashMap<Integer,String> footer=new HashMap();
           byte[] byteArray=excel.generateExcel("รายการสั่งซื้อ", col, row, headcol, column, datetime,footer,true);
           FileOutputStream fos = new FileOutputStream("D:\\Work\\Nat\\test\\test.xls");
           fos.write(byteArray);
           fos.close();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}
