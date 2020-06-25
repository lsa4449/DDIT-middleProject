package kr.or.ddit.vo;

import java.io.Serializable;

public class MyBookPageVO implements Serializable{
	private String bookName; // 도서명
	private String author; // 저자
	private String rentalDate; // 대출 일자
	private String returnDate; // 반납 일자
	private String reserveDate; // 예약 일자
	private String expectReturnDate; // 해당 도서 반납 예정일
	
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getExpectReturnDate() {
		return expectReturnDate;
	}
	public void setExpectReturnDate(String expectReturnDate) {
		this.expectReturnDate = expectReturnDate;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	
}
