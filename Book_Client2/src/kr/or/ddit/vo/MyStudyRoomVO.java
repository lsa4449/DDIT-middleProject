package kr.or.ddit.vo;

import java.io.Serializable;

public class MyStudyRoomVO implements Serializable{

	private int myStudyRoomNum; // 나의 스터디룸 번호
	private int studyRoomRentalNum; // 대여 번호
	private int studyRoomNum; // 스터디룸 관리 번호
	public int getMyStudyRoomNum() {
		return myStudyRoomNum;
	}
	public void setMyStudyRoomNum(int myStudyRoomNum) {
		this.myStudyRoomNum = myStudyRoomNum;
	}
	public int getStudyRoomRentalNum() {
		return studyRoomRentalNum;
	}
	public void setStudyRoomRentalNum(int studyRoomRentalNum) {
		this.studyRoomRentalNum = studyRoomRentalNum;
	}
	public int getStudyRoomNum() {
		return studyRoomNum;
	}
	public void setStudyRoomNum(int studyRoomNum) {
		this.studyRoomNum = studyRoomNum;
	}
	@Override
	public String toString() {
		return "MyStudyRoomVO [myStudyRoomNum=" + myStudyRoomNum + ", studyRoomRentalNum=" + studyRoomRentalNum
				+ ", studyRoomNum=" + studyRoomNum + "]";
	}

	
}
