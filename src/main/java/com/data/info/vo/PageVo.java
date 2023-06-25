package com.data.info.vo;

public class PageVo extends BaseVo {

	private static final long serialVersionUID = -4575465359639222614L;

	private int current = 1;// 当前第几页
	private int currentNum = 1;// 当前第几页
	private int total;// 总数
	private int pages;// 页数
	private int number = 10;// 每页多少条
	private String column;
	private String order;

	public int getCurrentNum() {
		return currentNum;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
		this.currentNum = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		if (this.total % this.number == 0) {
			this.pages = this.total / this.number;
		} else {
			this.pages = this.total / this.number + 1;
		}
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
