package kr.or.ddit.service.notice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;

public interface INoticeService extends Remote{
	
	
	/**
	 * 공지사항 게시판 제목검색
	 * @param boardTitle
	 * @return 
	 */
	public List<BoardVO> selectNotice (String boardTitle) throws RemoteException;
	
	
	/**
	 * 공지사항 게시판 전체조회
	 * @param boardVo
	 * @return
	 */
	public List<BoardVO> selectAll () throws RemoteException;
	
	
	
	
	/**
	 * 공지사항 게시글 등록 & 첨부파일
	 * @param boardVo
	 * @return
	 */
	public int insertNotice(BoardVO boardVo, FilesVO filesVo) throws RemoteException;
	
	
	/**
	 * 공지사항 게시글 수정
	 * @param boardVo
	 * @return
	 */
	public int updateNotice(BoardVO boardNum) throws RemoteException;
	
	
	/**
	 * 공지사항 게시글 삭제
	 * @param boardVo
	 * @return
	 */
	public int deleteNotice(int boardVo) throws RemoteException;
	
	
	/**
	 * 첨부파일 등록
	 * @param filesVo
	 * @return
	 */
//	public int insertFile(FilesVO filesVo) throws RemoteException;
	
	
	/**
	 * 첨부파일 삭제
	 * @param filesVo
	 * @return
	 */
	public int deleteFile(int filesNum) throws RemoteException; 
	
	
	/** X
	 * 공지사항 게시글 상세조회
	 * 제목을 클릭하면 상세조회 보기 
	 * @param boardTitle0.
	 * @return
	 */
	public List<BoardVO> detailNotice(String boardTitle) throws RemoteException;

	
	/**
	 * 해당 게시판의 첨부파일 정보 조회
	 * @param 
	 * @return
	 */
	public FilesVO selectNoticeFile(int boardNum) throws RemoteException; 
	 
	 
	 
	 /**
	  * 중고서적 조회수
	  */
	public int upboardCount(BoardVO vo)throws RemoteException;
	
}
