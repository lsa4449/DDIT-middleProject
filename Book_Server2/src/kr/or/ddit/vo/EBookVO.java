package kr.or.ddit.vo;

import java.io.Serializable;

public class EBookVO implements Serializable{
	int eBookNum;
	String eBookName;
	String eBookImage;
	String eBookText;
	String eBookAuthor;
	
	public String geteBookAuthor() {
		return eBookAuthor;
	}
	public void seteBookAuthor(String eBookAuthor) {
		this.eBookAuthor = eBookAuthor;
	}
	public int geteBookNum() {
		return eBookNum;
	}
	public void seteBookNum(int eBookNum) {
		this.eBookNum = eBookNum;
	}
	public String geteBookName() {
		return eBookName;
	}
	public void seteBookName(String eBookName) {
		this.eBookName = eBookName;
	}
	public String geteBookImage() {
		return eBookImage;
	}
	public void seteBookImage(String eBookImage) {
		this.eBookImage = eBookImage;
	}
	public String geteBookText() {
		return eBookText;
	}
	public void seteBookText(String eBookText) {
		this.eBookText = eBookText;
	}
	
	
}
