package kr.or.ddit.vo;

import java.io.Serializable;

public class MessageVO implements Serializable{

	private int messageNum; // 메세지 번호
	private String messageContent; // 메세지 내용
	private String writeTime; // 글 쓴 시간(보낸 시간)
	private int chatNum; // 채팅방 번호
	private int memNum; // 회원 번호

	public int getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}

	public int getChatNum() {
		return chatNum;
	}

	public void setChatNum(int chatNum) {
		this.chatNum = chatNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	@Override
	public String toString() {
		return "MessageVO [messageNum=" + messageNum + ", messageContent=" + messageContent + ", writeTime=" + writeTime
				+ ", chatNum=" + chatNum + ", memNum=" + memNum + "]";
	}

}
