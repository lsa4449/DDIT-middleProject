package kr.or.ddit.service.book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BookVO;

public interface IBookService extends Remote {
	

	/**
	 * 도서 등록 
	 * @param BookVO
	 * @author sua
	 */
	public int insertBook(BookVO bookVo) throws RemoteException;
	
	
	/**
	 * 도서 수정
	 * @param BookVO
	 * @author sua
	 */
	public int updateBook(BookVO bookVo) throws RemoteException;
	
	
	/**
	 * 도서 삭제
	 * @param BookVO
	 * @author sua
	 */
	public int deleteBook(int bookNum) throws RemoteException;
	
	
	
	/**
	 * 도서 검색 
	 * @param BookVO
	 * @author sua
	 */
	public List<BookVO> selectSearchBook(BookVO bookVo) throws RemoteException;
	
	
	/**
	 * 도서 예약 (예약 전)
	 * @param bookstate = 0 대기 상태 
	 * @author sua 
	 */
	public int beforeReserBook(String bookState) throws RemoteException;
	
	
	/**
	 * 도서 예약 (예약 후)
	 * @param bookstate = 1 예약
	 * @author sua 
	 */
	public int afterReserBook(int bookNum) throws RemoteException;
	
	
	
	/**
	 * 도서 반납
	 * @param  
	 * @author sua
	 */
	public int returnBook(String bookState) throws RemoteException;
	
	

	/**
	 * 전체 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> bookList() throws RemoteException;
	
	
	/**
	 * 카테고리별 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> categoryBookList(int CategoryNum) throws RemoteException;
	
	
	
	/**
	 * 구매 예정 도서 조회 
	 * @param 
	 * @author sua 
	 */
	public List<BookVO> purchaseExpectBook(int memNum) throws RemoteException;
 
}
