package kr.or.ddit.vochat;

import java.io.Serializable;

public class MessageData extends SendData implements Serializable{
	private static final long serialVersionUID = 3946361883789311376L;
	String content;
	String sender;
	public MessageData() {
	}
	public MessageData(String content, String sender) {
		super();
		this.content = content;
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String printMessage() {
		String message = sender + ":" + content + "\n";
		return message;
	}
	
}
