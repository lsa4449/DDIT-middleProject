package kr.or.ddit.vo;

import java.io.Serializable;

public class WannaBookVO implements Serializable{

	private int wannaBookNum; // 희망 도서 번호
	private int memNum; // 회원 번호
	private int categoryNum; // 분류 번호
	private String wannaTitle; // 도서명
	private String wannaAuthor; // 저자
	private String wannaPublicaiontDate; // 출간일
	private String wannaPublisher; // 출판사
	private String wannaTranslator; // 역자
	private String wannaState; // 승인상태

	public int getWannaBookNum() {
		return wannaBookNum;
	}

	public void setWannaBookNum(int wannaBookNum) {
		this.wannaBookNum = wannaBookNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}

	public String getWannaTitle() {
		return wannaTitle;
	}

	public void setWannaTitle(String wannaTitle) {
		this.wannaTitle = wannaTitle;
	}

	public String getWannaAuthor() {
		return wannaAuthor;
	}

	public void setWannaAuthor(String wannaAuthor) {
		this.wannaAuthor = wannaAuthor;
	}

	public String getWannaPublicaiontDate() {
		return wannaPublicaiontDate;
	}

	public void setWannaPublicaiontDate(String wannaPublicaiontDate) {
		this.wannaPublicaiontDate = wannaPublicaiontDate;
	}

	public String getWannaPublisher() {
		return wannaPublisher;
	}

	public void setWannaPublisher(String wannaPublisher) {
		this.wannaPublisher = wannaPublisher;
	}

	public String getWannaTranslator() {
		return wannaTranslator;
	}

	public void setWannaTranslator(String wannaTranslator) {
		this.wannaTranslator = wannaTranslator;
	}

	public String getWannaState() {
		return wannaState;
	}

	public void setWannaState(String wannaState) {
		this.wannaState = wannaState;
	}

	@Override
	public String toString() {
		return "WannaBookVO [wannaBookNum=" + wannaBookNum + ", memNum=" + memNum + ", categoryNum=" + categoryNum
				+ ", wannaTitle=" + wannaTitle + ", wannaAuthor=" + wannaAuthor + ", wannaPublicaiontDate="
				+ wannaPublicaiontDate + ", wannaPublisher=" + wannaPublisher + ", wannaTranslator=" + wannaTranslator
				+ ", wannaState=" + wannaState + "]";
	}

}
