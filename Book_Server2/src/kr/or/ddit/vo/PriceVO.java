package kr.or.ddit.vo;

import java.io.Serializable;

public class PriceVO implements Serializable {
	
	private int boardNum;
	private String tradeMoney;

	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}


}
