package kr.or.ddit.vo;

import java.io.Serializable;

public class MonthFileVO implements Serializable {

	private int fileNum; // 이달의도서 파일 번호
	private String fileName; // 이달의도서 파일명
	private int monthBookNum; // 이달의도서 번호

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getMonthBookNum() {
		return monthBookNum;
	}

	public void setMonthBookNum(int monthBookNum) {
		this.monthBookNum = monthBookNum;
	}

	@Override
	public String toString() {
		return "monthFileVO [fileNum=" + fileNum + ", fileName=" + fileName + ", monthBookNum=" + monthBookNum + "]";
	}

}
