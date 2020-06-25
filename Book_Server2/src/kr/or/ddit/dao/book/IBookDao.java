package kr.or.ddit.dao.book;

import java.util.List;

import kr.or.ddit.vo.BookVO;

public interface IBookDao {

	/**
	 * 도서 등록 
	 * @param BookVO
	 * @author sua
	 */
	public int insertBook(BookVO bookVo);
	
	/**
	 * 도서 수정
	 * @param BookVO
	 * @author sua
	 */
	public int updateBook(BookVO bookVo);
	
	/**
	 * 도서 삭제
	 * @param BookVO
	 * @author sua
	 */
	public int delete(int bookNum);
	
	
	/**
	 * 도서 검색 
	 * @param BookVO
	 * @author sua
	 */
	public List<BookVO> selectSearchBook(BookVO bookVo);
	
	
	/**
	 * 도서 예약 (예약 전)
	 * @param bookstate = 0 대기 상태 
	 * @author sua 
	 */
	public int beforeReserBook(String bookState);
	
	
	/**
	 * 도서 예약 (예약 후)
	 * @param bookstate = 1 예약
	 * @author sua 
	 */
	public int afterReserBook(int bookNum);
	
	
	
	/**
	 * 도서 반납
	 * @param  
	 * @author sua
	 */
	public int returnBook(String bookState);
	
	

	/**
	 * 전체 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> bookList();
	
	
	/**
	 * 카테고리별 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> categoryBookList(int CategoryNum);
	
	
	
	/**
	 * 구매 예정 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> purchaseExpectBook(int memNum);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
