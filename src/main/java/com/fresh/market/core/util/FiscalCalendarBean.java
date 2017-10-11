package com.fresh.market.core.util;

public class FiscalCalendarBean {
	private Integer id;
	private String name;
	private String period;
	private String beginRange;
	private String endRange;
	private String beginRangeRef;
	private String endRangeRef;

	public String getBeginRangeRef() {
		return beginRangeRef;
	}

	public void setBeginRangeRef(String beginRangeRef) {
		this.beginRangeRef = beginRangeRef;
	}

	public String getEndRangeRef() {
		return endRangeRef;
	}

	public void setEndRangeRef(String endRangeRef) {
		this.endRangeRef = endRangeRef;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginRange() {
		return beginRange;
	}

	public void setBeginRange(String beginRange) {
		this.beginRange = beginRange;
	}

	public String getEndRange() {
		return endRange;
	}

	public void setEndRange(String endRange) {
		this.endRange = endRange;
	}

}
