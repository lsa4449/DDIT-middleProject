package kr.or.ddit.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO implements Serializable{
	
	private int boardNum; // 게시글 번호
	private String boardTitle; // 게시글 명
	private String boardContent; // 게시글 내용
	private Date boardDate; // 작성일자
	private int boardCount; // 조회수
	private int boardKindNum; // 게시판 종류 번호
	private int memNum; // 회원 번호
	private int boardGroupNum;
	private String boardDate2;
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getBoardKindNum() {
		return boardKindNum;
	}
	public void setBoardKindNum(int boardKindNum) {
		this.boardKindNum = boardKindNum;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public int getBoardGroupNum() {
		return boardGroupNum;
	}
	public void setBoardGroupNum(int boardGroupNum) {
		this.boardGroupNum = boardGroupNum;
	}
	public String getBoardDate2() {
		return boardDate2;
	}
	public void setBoardDate2(String boardDate2) {
		this.boardDate2 = boardDate2;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDate=" + boardDate + ", boardCount=" + boardCount + ", boardKindNum=" + boardKindNum
				+ ", memNum=" + memNum + ", boardGroupNum=" + boardGroupNum + ", boardDate2=" + boardDate2 + "]";
	}
	
	//DateTime 칼럼 final TableColumn<TableData, String> dateTimeColumn = new TableColumn<>("DateTime");dateTimeColumn.setCellValueFactory(item -> new ReadOnlyStringWrapper( //ZonedDateTime Formatting 하여 전시 (예: 2020-02-01 13:00:11.22)item.getValue().getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))));dateTimeColumn.setPrefWidth(220); 

		
	
}
