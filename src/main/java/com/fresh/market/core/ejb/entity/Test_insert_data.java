package com.fresh.market.core.ejb.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Lenovo
 */
public class Test_insert_data {

    public static void main(String[] args) throws SQLException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/freshmarket?user=postgres&password=postgres");

            stmt = conn.createStatement();

            String FILE_NAME = "D:\\Work\\Nat\\insert_data.xls";
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                String code = currentRow.getCell(0).getStringCellValue();
                String nameEng = currentRow.getCell(1).getStringCellValue();
                String nameTh = currentRow.getCell(2).getStringCellValue();
                String unit = currentRow.getCell(3).getStringCellValue();
                Double price = currentRow.getCell(4).getNumericCellValue();
                System.out.println(code +" "+ nameEng+" "+nameTh+" "+unit+" "+price);
               //insert data
                PreparedStatement ps = null;
                String sql = "Insert into sys_item(item_code,item_eng,item_th,item_unit,item_price,item_status) "
                        + "values(?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, code);
                ps.setString(2, nameEng);
                ps.setString(3, nameTh);
                ps.setString(4, unit);
                ps.setDouble(5, price);
                ps.setString(6, "Y");
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
