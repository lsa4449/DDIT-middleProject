package kr.or.ddit.vo;

import java.io.Serializable;

public class CategoryVO implements Serializable{

	private int categoryNum; // 분류 번호
	private String categoryName; // 분류 명

	public int getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryVO [categoryNum=" + categoryNum + ", categoryName=" + categoryName + "]";
	}

}
