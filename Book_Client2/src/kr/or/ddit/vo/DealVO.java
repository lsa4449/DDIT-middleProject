package kr.or.ddit.vo;

import java.io.Serializable;

public class DealVO implements Serializable{

	private int dealNum; // 거래중개 번호
	private int memNum; // 회원 번호(FK)
	private int memNum2; // 회원 번호 (회원 - 회원 끼리 중개)
	private int account; // 가상계좌
	private int dealGroupNum;
	private String memId;
	private String memId2;
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId2() {
		return memId2;
	}

	public void setMemId2(String memId2) {
		this.memId2 = memId2;
	}

	public int getDealGroupNum() {
		return dealGroupNum;
	}

	public void setDealGroupNum(int dealGroupNum) {
		this.dealGroupNum = dealGroupNum;
	}

	public int getDealNum() {
		return dealNum;
	}

	public void setDealNum(int dealNum) {
		this.dealNum = dealNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getMemNum2() {
		return memNum2;
	}

	public void setMemNum2(int memNum2) {
		this.memNum2 = memNum2;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "DealVO [dealNum=" + dealNum + ", memNum=" + memNum + ", memNum2=" + memNum2 + ", account=" + account
				+ "]";
	}


}
