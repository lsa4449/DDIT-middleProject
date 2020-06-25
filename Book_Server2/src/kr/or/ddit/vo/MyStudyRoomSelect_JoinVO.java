package kr.or.ddit.vo;

import java.io.Serializable;

public class MyStudyRoomSelect_JoinVO implements Serializable{

	private String adMyStudyRoomName; // 이름
	private String adMyStudyRoomReserveTime; // 대여한 시간
	private String adMyStudyRoomReturnTime; // 반납할 시간
	private int adMyStudyRoomNum; // 대여한 스터디 룸 번호
	public String getAdMyStudyRoomName() {
		return adMyStudyRoomName;
	}
	public void setAdMyStudyRoomName(String adMyStudyRoomName) {
		this.adMyStudyRoomName = adMyStudyRoomName;
	}
	public String getAdMyStudyRoomReserveTime() {
		return adMyStudyRoomReserveTime;
	}
	public void setAdMyStudyRoomReserveTime(String adMyStudyRoomReserveTime) {
		this.adMyStudyRoomReserveTime = adMyStudyRoomReserveTime;
	}
	public String getAdMyStudyRoomReturnTime() {
		return adMyStudyRoomReturnTime;
	}
	public void setAdMyStudyRoomReturnTime(String adMyStudyRoomReturnTime) {
		this.adMyStudyRoomReturnTime = adMyStudyRoomReturnTime;
	}
	public int getAdMyStudyRoomNum() {
		return adMyStudyRoomNum;
	}
	public void setAdMyStudyRoomNum(int adMyStudyRoomNum) {
		this.adMyStudyRoomNum = adMyStudyRoomNum;
	}
	
	
}
