package kr.or.ddit.vo;

import java.io.Serializable;

public class BookRentalVO implements Serializable{

	private int bookRentalNum; // 대여 번호
	private String rentalDate; // 대출 일자
	private String returnDate; // 반납 일자
	private String expectReturnDate; // 예상 반납 일자
	private int memNum; // 회원 번호
	private int bookNum; // 도서 번호
	private int bookReserveNum; // 도서 예약 번호
	private String state; // 상태
	
	

	public String getExpectReturnDate() {
		return expectReturnDate;
	}

	public void setExpectReturnDate(String expectReturnDate) {
		this.expectReturnDate = expectReturnDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getBookRentalNum() {
		return bookRentalNum;
	}

	public void setBookRentalNum(int bookRentalNum) {
		this.bookRentalNum = bookRentalNum;
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

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public int getBookReserveNum() {
		return bookReserveNum;
	}

	public void setBookReserveNum(int bookReserveNum) {
		this.bookReserveNum = bookReserveNum;
	}

	@Override
	public String toString() {
		return "BookRentalVO [bookRentalNum=" + bookRentalNum + ", rentalDate=" + rentalDate + ", returnDate="
				+ returnDate + ", memNum=" + memNum + ", bookNum=" + bookNum + ", bookReserveNum=" + bookReserveNum
				+ ", state=" + state + "]";
	}

}
