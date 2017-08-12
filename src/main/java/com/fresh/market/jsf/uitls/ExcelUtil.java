package com.fresh.market.jsf.uitls;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.slf4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.LoggerFactory;

public class ExcelUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelUtil.class);
    private static final String TEMPLATE_PATH = "/resources/excel/";
    private static final String WITHDRAW = "withdraw.xls";
    private static final String DEPOSIT = "deposit.xls";
    private static final String USER_PROFILE = "userProfile.xls";
    private static final String FONT = "Tahoma";

    private ExcelUtil() {
    }

    public static void exportWithdraw() {
       
    }
    
    public static HSSFCellStyle getFontStyleByResult(HSSFWorkbook wb, String value) {

        HSSFCellStyle redStyle = getDStyle(wb);
        HSSFFont rFont = redStyle.getFont(wb);
        rFont.setColor(IndexedColors.RED.getIndex());
        redStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        redStyle.setFont(rFont);

        if (value == null) {
            return redStyle;
        }

        if (value.equals("Y")) {
            HSSFCellStyle blueStyle = getDStyle(wb);
            HSSFFont bFont = blueStyle.getFont(wb);
            bFont.setColor(IndexedColors.BLUE.getIndex());
            blueStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            blueStyle.setFont(bFont);
            return blueStyle;
        }

        return redStyle;

    }

    public static HSSFCellStyle getCStyle(HSSFWorkbook wb) {
        Font font = wb.createFont();
        font.setFontName(FONT);
        font.setFontHeightInPoints((short) 9);
        Font fontError = wb.createFont();
        fontError.setFontName(FONT);
        fontError.setFontHeightInPoints((short) 9);
        fontError.setColor(IndexedColors.WHITE.getIndex());

        HSSFFont cFont = wb.createFont();
        cFont.setFontName(FONT);
        cFont.setFontHeightInPoints((short) 9);
        cFont.setColor(IndexedColors.BLACK.getIndex());
        cFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cStyle = wb.createCellStyle();
        cStyle.setFont(cFont);
        cStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//                cStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//                cStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cStyle.setBorderTop(CellStyle.BORDER_THIN);
        cStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cStyle.setBorderRight(CellStyle.BORDER_THIN);
        cStyle.setBorderBottom(CellStyle.BORDER_THIN);
        return cStyle;
    }

    public static HSSFCellStyle getDStyle(HSSFWorkbook wb) {
        HSSFCellStyle dStyle = wb.createCellStyle();
        HSSFFont dFont = wb.createFont();
        dFont.setFontName(FONT);
        dStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        dFont.setFontHeightInPoints((short) 9);
        dStyle.setFont(dFont);

        dStyle.setBorderTop(CellStyle.BORDER_THIN);
        dStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dStyle.setBorderRight(CellStyle.BORDER_THIN);
        dStyle.setBorderBottom(CellStyle.BORDER_THIN);
        return dStyle;
    }

    public static HSSFCellStyle getDSGreenBoldtyle(HSSFWorkbook wb) {
        HSSFCellStyle dStyle = wb.createCellStyle();
        HSSFFont dFont = wb.createFont();
        dFont.setFontName(FONT);
        dStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        dFont.setFontHeightInPoints((short) 9);
        dFont.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
        dFont.setColor(HSSFColor.GREEN.index);

        dStyle.setFont(dFont);

        dStyle.setBorderTop(CellStyle.BORDER_THIN);
        dStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dStyle.setBorderRight(CellStyle.BORDER_THIN);
        dStyle.setBorderBottom(CellStyle.BORDER_THIN);

        return dStyle;
    }

    public static HSSFCellStyle getDSRedBoldtyle(HSSFWorkbook wb) {
        HSSFCellStyle dStyle = wb.createCellStyle();
        HSSFFont dFont = wb.createFont();
        dFont.setFontName(FONT);
        dStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        dFont.setFontHeightInPoints((short) 9);
        dFont.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
        dFont.setColor(HSSFColor.RED.index);

        dStyle.setFont(dFont);

        dStyle.setBorderTop(CellStyle.BORDER_THIN);
        dStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dStyle.setBorderRight(CellStyle.BORDER_THIN);
        dStyle.setBorderBottom(CellStyle.BORDER_THIN);

        return dStyle;
    }

    public static HSSFCellStyle getDSBoldtyle(HSSFWorkbook wb) {
        HSSFCellStyle dStyle = wb.createCellStyle();
        HSSFFont dFont = wb.createFont();
        dFont.setFontName(FONT);
        dStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        dFont.setFontHeightInPoints((short) 9);
        dFont.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold

        dStyle.setFont(dFont);

        dStyle.setBorderTop(CellStyle.BORDER_THIN);
        dStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dStyle.setBorderRight(CellStyle.BORDER_THIN);
        dStyle.setBorderBottom(CellStyle.BORDER_THIN);

        return dStyle;
    }

    // WrapText
    public static HSSFCellStyle getWrapTextStyle(HSSFWorkbook wb) {
        HSSFCellStyle dStyle = wb.createCellStyle();
        HSSFFont dFont = wb.createFont();
        dFont.setFontName(FONT);
        dStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        dFont.setFontHeightInPoints((short) 9);
        dStyle.setFont(dFont);
        dStyle.setWrapText(true);

        dStyle.setBorderTop(CellStyle.BORDER_THIN);
        dStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dStyle.setBorderRight(CellStyle.BORDER_THIN);
        dStyle.setBorderBottom(CellStyle.BORDER_THIN);
        return dStyle;
    }

    //DarkGray Background Color
    public static HSSFCellStyle getDarkBGStyle(HSSFWorkbook wb) {
        HSSFCellStyle xStyle = wb.createCellStyle();
        xStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        xStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        return xStyle;
    }

    //Red Background Color
    public static HSSFCellStyle getRedBGStyle(HSSFWorkbook wb) {
        HSSFCellStyle xStyle = wb.createCellStyle();
        HSSFFont xFont = wb.createFont();
        xFont.setFontName(FONT);
        xFont.setFontHeightInPoints((short) 9);
        xStyle.setFont(xFont);

        xStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        xStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        xStyle.setBorderTop(CellStyle.BORDER_THIN);
        xStyle.setBorderLeft(CellStyle.BORDER_THIN);
        xStyle.setBorderRight(CellStyle.BORDER_THIN);
        xStyle.setBorderBottom(CellStyle.BORDER_THIN);

        xStyle.setFillForegroundColor(HSSFColor.RED.index);
        xStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        return xStyle;
    }

    //Green Background Color
    public static HSSFCellStyle getGreenBGStyle(HSSFWorkbook wb) {
        HSSFCellStyle xStyle = wb.createCellStyle();
        HSSFFont xFont = wb.createFont();
        xFont.setFontName(FONT);
        xFont.setFontHeightInPoints((short) 9);
        xStyle.setFont(xFont);

        xStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        xStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        xStyle.setBorderTop(CellStyle.BORDER_THIN);
        xStyle.setBorderLeft(CellStyle.BORDER_THIN);
        xStyle.setBorderRight(CellStyle.BORDER_THIN);
        xStyle.setBorderBottom(CellStyle.BORDER_THIN);

        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.LIGHT_GREEN.index, (byte) 146, (byte) 208, (byte) 80);
        xStyle.setFillForegroundColor(palette.getColor(HSSFColor.LIGHT_GREEN.index).getIndex());
        xStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        return xStyle;
    }

    //Pink Background Color
    public static HSSFCellStyle getPinkBGStyle(HSSFWorkbook wb) {
        HSSFCellStyle xStyle = wb.createCellStyle();
        HSSFFont xFont = wb.createFont();
        xFont.setFontName(FONT);
        xFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        xFont.setFontHeightInPoints((short) 9);
        xStyle.setFont(xFont);

        xStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        xStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        xStyle.setBorderTop(CellStyle.BORDER_THIN);
        xStyle.setBorderLeft(CellStyle.BORDER_THIN);
        xStyle.setBorderRight(CellStyle.BORDER_THIN);
        xStyle.setBorderBottom(CellStyle.BORDER_THIN);

        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.PINK.index, (byte) 230, (byte) 185, (byte) 184);
        xStyle.setFillForegroundColor(palette.getColor(HSSFColor.PINK.index).getIndex());
        xStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        xStyle.setWrapText(true);

        return xStyle;
    }

    private static void generateCell(HSSFCell cell, Object rowData, String defaultIfNull) {
        if (rowData == null) {
            cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
            cell.setCellValue(defaultIfNull);
        } else if (rowData instanceof Double) {
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue((Double) rowData);
        } else if (rowData instanceof Integer) {
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue((Integer) rowData);
        } else if (rowData instanceof BigDecimal) {
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(((Number) rowData).doubleValue());
        } else if (rowData instanceof Boolean) {
            cell.setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
            cell.setCellValue((Boolean) rowData);
        } else if (rowData instanceof String) {
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//            cell.setCellStyle(stringStyle);
            cell.setCellValue(String.valueOf(rowData));
//            System.out.println("-" + String.valueOf(rowData));
        } else {
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(String.valueOf(rowData));
        }
    }

    private static String formatCell(HSSFCell cell) {

        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
            return cell.getCellFormula();
        }

        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        }

        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            DecimalFormat fomtt = new DecimalFormat("###");
            return fomtt.format(cell.getNumericCellValue());
        }

        return "";

    }

}
