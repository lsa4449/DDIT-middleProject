package kr.or.ddit.dao.usedbook;

import java.util.List;

import kr.or.ddit.vo.BookVO;

public interface IUseBookDao {
	

	/**
	 * 대출 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> rentalBook(int memNum);
	
	/**
	 * 반납 예정 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> returnBook(int memNum);
	
	/**
	 * 연체 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> overBook(int memNum);
	
}
