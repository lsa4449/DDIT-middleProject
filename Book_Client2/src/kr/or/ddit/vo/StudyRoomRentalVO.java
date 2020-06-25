package kr.or.ddit.vo;

import java.io.Serializable;

public class StudyRoomRentalVO implements Serializable{

	private int studyRoomRentalNum; // 대여 번호
	private int studyRoomNum; // 스터디룸 관리 번호
	private int memNum; // 회원 번호
	private String studyRoomRentalTime; // 대여 시간
	private String studyRoomReturnTime; // 반납 시간

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

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public String getStudyRoomRentalTime() {
		return studyRoomRentalTime;
	}

	public void setStudyRoomRentalTime(String studyRoomRentalTime) {
		this.studyRoomRentalTime = studyRoomRentalTime;
	}

	public String getStudyRoomReturnTime() {
		return studyRoomReturnTime;
	}

	public void setStudyRoomReturnTime(String studyRoomReturnTime) {
		this.studyRoomReturnTime = studyRoomReturnTime;
	}

	@Override
	public String toString() {
		return "StudyRoomRentalVO [studyRoomRentalNum=" + studyRoomRentalNum + ", studyRoomNum=" + studyRoomNum
				+ ", memNum=" + memNum + ", studyRoomRentalTime=" + studyRoomRentalTime + ", studyRoomReturnTime="
				+ studyRoomReturnTime + "]";
	}

}
