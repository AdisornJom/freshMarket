package com.fresh.market.core.util;


public class ExcelMutiSheetBean {
	
	private String nameSheet;
	private String[] col;
	private String[][] row;
	private String[] headcol;
	private String[] merge;
	private String mergeRow;
	private String[] footer;
	
	public String[] getFooter() {
		return footer;
	}
	public void setFooter(String[] footer) {
		this.footer = footer;
	}
	public String[] getCol() {
		return col;
	}
	public void setCol(String[] col) {
		this.col = col;
	}
	public String[] getHeadcol() {
		return headcol;
	}
	public void setHeadcol(String[] headcol) {
		this.headcol = headcol;
	}
	public String[] getMerge() {
		return merge;
	}
	public void setMerge(String[] merge) {
		this.merge = merge;
	}
	public String getNameSheet() {
		return nameSheet;
	}
	public void setNameSheet(String nameSheet) {
		this.nameSheet = nameSheet;
	}
	public String[][] getRow() {
		return row;
	}
	public void setRow(String[][] row) {
		this.row = row;
	}
	public String getMergeRow() {
		return mergeRow;
	}
	public void setMergeRow(String mergeRow) {
		this.mergeRow = mergeRow;
	}

}
