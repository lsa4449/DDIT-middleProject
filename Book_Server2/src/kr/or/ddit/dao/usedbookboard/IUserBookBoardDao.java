package kr.or.ddit.dao.usedbookboard;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.PriceVO;

public interface IUserBookBoardDao {
	/**
	 * 중고서적 거래 게시판 목록 조회
	 */
	public List<BoardVO> selectAll(int num) throws RemoteException;

	/**
	 * 중고서적 검색 조회
	 */
	public List<BoardVO> selectTitleUserBookBoard(BoardVO vo) throws RemoteException;
	public List<BoardVO> selectMemUserBookBuyBoard(String str) throws RemoteException;
	public List<BoardVO> selectMemUserBookCellBoard(String str) throws RemoteException;
	public List<FilesVO> selectFile(int num) throws RemoteException;
	public PriceVO selectPrice(int num) throws RemoteException;
	
	/**
	 * 중고서적 상세 검색 조회 
	 */
	public BoardVO detailUserBookBoard(BoardVO vo) throws RemoteException;
	
	/**
	 * 중고서적 거래 게시판 작성
	 */
	public int insertUserBookBoard(BoardVO vo) throws RemoteException;
	public int searchboardNum() throws RemoteException;
	public int insertfile(FilesVO vo) throws RemoteException;
	public int insertBookBoardBuyPrice(PriceVO vo) throws RemoteException;
	/**
	 * 중고서적 거래 게시판 삭제
	 */
	public int deleteUsedBookBoard(int num) throws RemoteException;
	public int deletefile(int num) throws RemoteException;
	public int deletePrice(int num) throws RemoteException;
	/**
	 * 중고서적 거래 게시판 수정
	 */
	public int updateUserBookBoard(BoardVO vo) throws RemoteException;
	public int updatefile(FilesVO vo) throws RemoteException;	
	/**
	 * 중고서적 조회수
	 */
	public int upboardCount(BoardVO vo)throws RemoteException;
	

}
