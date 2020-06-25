package kr.or.ddit.vo;

import java.io.Serializable;

public class MonthBookVO implements Serializable {

	private int monthBookNum; // 이달이 도서 번호
	private int bookNum; // 도서 번호
	private String monthBookName; // 도서 이름
	private String monthBookImage; // 도서 이미지

	public int getMonthBookNum() {
		return monthBookNum;
	}

	public void setMonthBookNum(int monthBookNum) {
		this.monthBookNum = monthBookNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getMonthBookName() {
		return monthBookName;
	}

	public void setMonthBookName(String monthBookName) {
		this.monthBookName = monthBookName;
	}

	public String getMonthBookImage() {
		return monthBookImage;
	}

	public void setMonthBookImage(String monthBookImage) {
		this.monthBookImage = monthBookImage;
	}

	@Override
	public String toString() {
		return "MonthBookVO [monthBookNum=" + monthBookNum + ", bookNum=" + bookNum + ", monthBookName=" + monthBookName
				+ ", monthBookImage=" + monthBookImage + "]";
	}

}
