package kr.or.ddit.dao.notice;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;

public interface INoticeDao {

	/**
	 * 공지사항 게시판 제목검색
	 * @param boardTitle
	 * @return 
	 */
	public List<BoardVO> selectNotice (String boardTitle);
	
	
	/**
	 * 공지사항 게시판 전체조회
	 * @param boardVo
	 * @return
	 */
	public List<BoardVO> selectAll ();
	
	
	/**
	 * 공지사항 게시글 상세조회
	 * 제목을 클릭하면 상세조회 보기 
	 * @param boardTitle
	 * @return
	 */
	public List<BoardVO> detailNotice(String boardTitle);
	
	
	/**
	 * 공지사항 게시글 등록 & 첨부파일
	 * @param boardVo
	 * @return
	 */
	public int insertNotice(BoardVO boardVo, FilesVO filesVo);
	
	
	/**
	 * 공지사항 게시글 수정
	 * @param boardVo
	 * @return
	 */
	public int updateNotice(BoardVO boardVo);
	
	/**
	 * 공지사항 게시글 삭제
	 * @param boardVo
	 * @return
	 */
	public int deleteNotice(int boardNum);
	
	
	/**
	 * 첨부파일 등록
	 * @param filesVo
	 * @return
	 */
//	public int insertFile(FilesVO filesVo);
	
	
	/**
	 * 첨부파일 삭제
	 * @param filesVo
	 * @return
	 */
	public int deleteFile(int filesNum);




	/**
	 * 공지사항 첨부파일 조회
	 * @param boardKey
	 * @return
	 */
	public FilesVO selectNoticeFile(int boardNum);
	
	
	/**
	 * 중고서적 조회수
	 */
	public int upboardCount(BoardVO vo)throws RemoteException;

}
