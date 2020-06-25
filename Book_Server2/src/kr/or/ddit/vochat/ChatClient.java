package kr.or.ddit.vochat;

import java.io.Serializable;

public class ChatClient implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6627747204337669686L;
	//LinkeList에 싸여있어도 제너릭 타입도 seiralizable해야함.
	
	String nickName;
	
	int cid;
	
	public ChatClient(String nickName, int cid) { // 클라이언트 객체를 만들어줌 
		this.nickName = nickName;
		this.cid = cid;
	}
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
