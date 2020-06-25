package kr.or.ddit.vo;

import java.io.Serializable;

public class SeatRentalVO implements Serializable{

	private int memNum; // 회원 번호
	private int seatNum; // 좌석 관리 번호
	private int seatRentalNum; // 대여 번호
	private String seatRentalTime; // 대여 시간
	private String seatReturnTime; // 반납 시간

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public int getSeatRentalNum() {
		return seatRentalNum;
	}

	public void setSeatRentalNum(int seatRentalNum) {
		this.seatRentalNum = seatRentalNum;
	}

	public String getSeatRentalTime() {
		return seatRentalTime;
	}

	public void setSeatRentalTime(String seatRentalTime) {
		this.seatRentalTime = seatRentalTime;
	}

	public String getSeatReturnTime() {
		return seatReturnTime;
	}

	public void setSeatReturnTime(String seatReturnTime) {
		this.seatReturnTime = seatReturnTime;
	}

	@Override
	public String toString() {
		return "SeatRentalVO [memNum=" + memNum + ", seatNum=" + seatNum + ", seatRentalNum=" + seatRentalNum
				+ ", seatRentalTime=" + seatRentalTime + ", seatReturnTime=" + seatReturnTime + "]";
	}

}
