package com.fresh.market.core.util;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import jxl.CellView;
import jxl.Range;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class GenerateExcelReport {

    public byte[] generateExcel(String nameSheet, String[] col, String[][] row, String[] headcol, String[] column, String[] datetime, HashMap<Integer, String> footer, boolean reportTypeIncomeSummary) {
        //	InputStream in =null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            //create Excel
            jxl.WorkbookSettings wbSetttings = new jxl.WorkbookSettings();
            wbSetttings.setRationalization(false);

            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(out);

            jxl.write.WritableSheet ws = wwb.createSheet(nameSheet, 0);
            jxl.write.WritableFont wf = new jxl.write.WritableFont(jxl.write.WritableFont.TAHOMA, 10, jxl.write.WritableFont.BOLD);
            // WritableFont wf = new WritableFont(WritableFont.TIMES, 15, WritableFont.BOLD, false,UnderlineStyle.SINGLE);

            jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(wf);
            wcf.setAlignment(jxl.format.Alignment.LEFT);

            manual_width(ws, false, 0, 500);
            // Write a few headers
            addCaption(ws, 1, 0, headcol[0], wcf);
            //  addCaption(ws, 1, 1, headcol[1],wcf);
            // addCaption(ws, 1, 2, headcol[2],wcf);
            manual_width(ws, false, 1, 5000);

            int col_blank = 3, col_total = 2;
            for (int i = 0; i < column.length; i++) {
                //addCaption(ws, i+1, 3, dimensional[i],wcf);
                if (i == 0) {
                    addCell(ws, Border.BOTTOM, BorderLineStyle.DOUBLE, i + 2, 2, column[i], wf);
                    manual_width(ws, false, i + 2, 4000);
                } else {
                    col_total = i + (col_blank++);
                    manual_width(ws, false, col_total - 1, 500);
                    addCell(ws, Border.BOTTOM, BorderLineStyle.DOUBLE, col_total, 2, column[i], wf);
                    manual_width(ws, false, col_total, 4000);
                }
            }
            /*addCell(ws, Border.LEFT, BorderLineStyle.DOTTED, 1, 1, "Left - dotted");
		        	    addCell(ws, Border.RIGHT, BorderLineStyle.DASHED, 1, 3, "Right - dashed");
		        	    addCell(ws, Border.TOP, BorderLineStyle.DOTTED, 1, 5, "Top - dotted");
		        	    addCell(ws, Border.BOTTOM, BorderLineStyle.DASHED, 1, 7, "Bottom - dashed");
		        	    addCell(ws, Border.ALL, BorderLineStyle.THIN, 1, 9, "All - thin");*/

            WritableFont wf_right = new WritableFont(WritableFont.TAHOMA, 8);
            WritableCellFormat wcf_right = new WritableCellFormat(wf_right);
            wcf_right.setAlignment(jxl.format.Alignment.RIGHT);
            addCaption(ws, col_total, 0, datetime[0], wcf_right);
            //addCaption(ws, col_total, 1, datetime[1],wcf_right);

            WritableFont wf_right_bold = new WritableFont(WritableFont.TAHOMA, 8, jxl.write.WritableFont.BOLD);
            WritableCellFormat wcf_right_bold = new WritableCellFormat(wf_right_bold);
            wcf_right_bold.setAlignment(jxl.format.Alignment.RIGHT);
            manual_width(ws, false, col_total, 4000);

            jxl.write.WritableFont wf1 = new jxl.write.WritableFont(jxl.write.WritableFont.TAHOMA, 10);
            jxl.write.NumberFormat fivedps = new jxl.write.NumberFormat("#,##0.00");
            jxl.write.WritableCellFormat fivedpsFormat = new jxl.write.WritableCellFormat(fivedps);
            fivedpsFormat.setFont(wf1);

            for (int i = 0; i < row.length; i++) {
                int col_blank_row_ = 2, col_total_row = 0;
                boolean flag_border_top = false;
                boolean flag_border_buttom = false;
                String[] str = row[i][0].substring(6, row[i][0].length()).split("\\|");

                /*  if(str[0].indexOf("Total Assets")>-1 || str[0].indexOf("Total Liabilities and Equity")>-1 || str[0].indexOf("Net Income")>-1){
                                                                  flag_border_buttom=true;
                                                          }else if((str[0].indexOf("Net")>-1 && str[0].indexOf("Net")<15) || (str[0].indexOf("Total")>-1&&str[0].indexOf("Total")<15)){
                                                                  if(str[0].indexOf("Total Income")>-1 && reportTypeIncomeSummary){
                                                                          flag_border_buttom=true; 
                                                                  }else{
                                                                          flag_border_top=true;
                                                                  }
                                                          } 
                 */
                if (str[0].indexOf("Total") > -1) {
                    flag_border_buttom = true;
                }

                for (int j = 0; j < col.length; j++) {
                    String check = row[i][j].substring(0, 6);
                    if (j > 1) {
                        col_total_row = col_total_row + col_blank_row_;
                    } else if (j == col.length - 1) {
                        col_total_row = col_total_row - 1;
                        if (col_total_row == 0) {
                            col_total_row = 2;
                        }
                    } else {
                        col_total_row = j + 1;
                    }
                    //col_total_row=j;

                    if (check.equals("Number")) {
                        String Data = row[i][j].substring(6, row[i][j].length());
                        if (!Data.equals("null") && Data.trim().length() != 0) {
                            jxl.write.WritableCellFormat fivedpsFormat1 = new jxl.write.WritableCellFormat(fivedps);
                            fivedpsFormat1.setFont(wf1);
                            if (flag_border_top) {
                                fivedpsFormat1.setBorder(Border.TOP, BorderLineStyle.THICK);
                                jxl.write.Number number4 = new jxl.write.Number(col_total_row, 3 + i, Double.parseDouble(Data), fivedpsFormat1);
                                ws.addCell(number4);
                            } else if (flag_border_buttom) {
                                fivedpsFormat1.setBorder(Border.TOP, BorderLineStyle.THICK);
                                fivedpsFormat1.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
                                jxl.write.Number number4 = new jxl.write.Number(col_total_row, 3 + i, Double.parseDouble(Data), fivedpsFormat1);
                                ws.addCell(number4);
                            } else {
                                jxl.write.Number number4 = new jxl.write.Number(col_total_row, 3 + i, Double.parseDouble(Data), fivedpsFormat1);
                                ws.addCell(number4);
                            }
                        }
                    } else if (check.equals("Percen")) {
                        String Data = row[i][j].substring(6, row[i][j].length());
                        if (!Data.equals("null") && Data.trim().length() != 0) {
                            jxl.biff.DisplayFormat percendps = NumberFormats.PERCENT_FLOAT;
                            //DecimalFormat format2 = new DecimalFormat("#,##0.00");
                            //Data=format2.format(Double.parseDouble(Data));
                            //  jxl.write.NumberFormat fivedps = new jxl.write.NumberFormat("#,##0.00"); 
                            // jxl.write.NumberFormat currencyEuro = new jxl.write.NumberFormat("#,#00.00",  jxl.write.NumberFormats.PERCENT_FLOAT);
                            jxl.write.WritableCellFormat fivedpsFormat1 = new jxl.write.WritableCellFormat(percendps);
                            fivedpsFormat1.setFont(wf1);
                            if (flag_border_top) {
                                fivedpsFormat1.setBorder(Border.TOP, BorderLineStyle.THICK);
                                jxl.write.Number number4 = new jxl.write.Number(col_total_row, 3 + i, Double.parseDouble(Data), fivedpsFormat1);
                                ws.addCell(number4);
                            } else if (flag_border_buttom) {
                                fivedpsFormat1.setBorder(Border.TOP, BorderLineStyle.THICK);
                                fivedpsFormat1.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
                                jxl.write.Number number4 = new jxl.write.Number(col_total_row, 3 + i, Double.parseDouble(Data), fivedpsFormat1);
                                ws.addCell(number4);
                            } else {
                                jxl.write.Number number4 = new jxl.write.Number(col_total_row, 3 + i, Double.parseDouble(Data), fivedpsFormat1);
                                //  Label number4 = new jxl.write.Label(col_total_row, 3+i,Data,fivedpsFormat1);
                                ws.addCell(number4);
                            }
                        }
                    } else {
                        String[] data = row[i][j].substring(6, row[i][j].length()).split("\\|");
                        if (!data[0].equals("null") && data[0].trim().length() != 0) {
                            Label labelRow = new jxl.write.Label(col_total_row, 3 + i, data[0], fivedpsFormat);
                            ws.addCell(labelRow);
                        }
                    }
                }
            }
/*
            Label labelCriteria = new jxl.write.Label(1, row.length + 6, "Criteria Report", wcf);
            ws.addCell(labelCriteria);
            int rows = row.length + 4;
            for (int q = 0; q < footer.size(); q++) {
                Label labelRow = new jxl.write.Label(1, rows + q, (String) footer.get(q), wcf);
                ws.addCell(labelRow);
            }
            //sheetAutoFitColumns(ws);
*/
            wwb.write();
            wwb.close();

            //in =new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    private void manual_width(WritableSheet ws, boolean autoSize, int col, int width) {
        CellView cv1 = ws.getColumnView(col);
        if (width != 0) {
            cv1.setSize(width);
        }
        cv1.setAutosize(autoSize);
        ws.setColumnView(col, cv1);
    }

    private void addCell(WritableSheet sheet, Border border, BorderLineStyle borderLineStyle, int col, int row, String desc, WritableFont font) throws WriteException {
        WritableCellFormat cellFormat = new WritableCellFormat(font);
        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
        cellFormat.setWrap(true);
        cellFormat.setBorder(border, borderLineStyle);
        Label label = new Label(col, row, desc, cellFormat);
        sheet.addCell(label);
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s, CellFormat st) throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, st);
        sheet.addCell(label);
    }

    public Range mergeCells(WritableSheet sheet,
            int from,
            int to) throws WriteException, RowsExceededException {

        Range range = sheet.mergeCells(from, (short) 0, to, (short) 0);

        return range;
    }

    public static Range mergeCellsMuti(WritableSheet sheet, int from, int to, int rowOnMerge) throws WriteException, RowsExceededException {

        Range range = sheet.mergeCells(from, (short) rowOnMerge, to, (short) rowOnMerge);

        return range;
    }

    public String excelFormatString(String formatString) {
        return "NotNum" + formatString;
    }

    public String excelFormatNumber(String formatNumber) {
        return "Number" + formatNumber;
    }

    public String excelFormatPercentage(String formatPercen) {
        return "Percen" + formatPercen;
    }

}
