package kr.or.ddit.vo;

import java.io.Serializable;

public class StudyRoomVO implements Serializable{

	private int studyRoomNum; // 스터디룸 번호
	private String studyRoomState; // 스터디룸 상태
	private String studyRoomReserState; // 예약 상태

	public int getStudyRoomNum() {
		return studyRoomNum;
	}

	public void setStudyRoomNum(int studyRoomNum) {
		this.studyRoomNum = studyRoomNum;
	}

	public String getStudyRoomState() {
		return studyRoomState;
	}

	public void setStudyRoomState(String studyRoomState) {
		this.studyRoomState = studyRoomState;
	}

	public String getStudyRoomReserState() {
		return studyRoomReserState;
	}

	public void setStudyRoomReserState(String studyRoomReserState) {
		this.studyRoomReserState = studyRoomReserState;
	}

	@Override
	public String toString() {
		return "StudyRoomVO [studyRoomNum=" + studyRoomNum + ", studyRoomState=" + studyRoomState
				+ ", studyRoomReserState=" + studyRoomReserState + "]";
	}

}
