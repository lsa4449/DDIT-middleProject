package kr.or.ddit.vo;

import java.io.Serializable;

public class SeatVO implements Serializable{

	private int seatNum; // 좌석 번호
	private String seatState; // 좌석 상태

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public String getSeatState() {
		return seatState;
	}

	public void setSeatState(String seatState) {
		this.seatState = seatState;
	}

	@Override
	public String toString() {
		return "SeatVO [seatNum=" + seatNum + ", seatState=" + seatState + "]";
	}

}
