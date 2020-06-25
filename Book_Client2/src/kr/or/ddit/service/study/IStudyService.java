package kr.or.ddit.service.study;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.RippleVO;

public interface IStudyService extends Remote {
	
	/**
	 * 스터디 게시판 제목검색
	 * 글 제목을 키값으로  해당하는 검색정보가 담긴 vo를 반환하는 메서드
	 * @param BoardTitle
	 * @return BoardVO
	 */
	public List<BoardVO> selectStudy(String boardTitle) throws RemoteException;
	
	
	/**
	 * 스터디게시글 전체조회
	 * @param boardVo
	 * @return 
	 */
	public List<BoardVO> BoardStudy() throws RemoteException;
	
	/**
	 * 스터디게시글 상세조회
	 * @param boardVo
	 * @return 
	 */
	public BoardVO detailStudy(int cnt) throws RemoteException;
	
	/**
	 * 스터디 게시판 글 쓰기  등록
	 * boardVo DB에 insert할 자료가 저장된 boardVo 객체
	 * @param boardVo
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertStudy(BoardVO boardVo) throws RemoteException;
	
	
	/**스터디 게시판 글 수정
	 * boardVo의 정보를 이용하여 글 정보를 수정하는 메서드
	 * @param boardVo 수정할 정보가 저장된 vo객체
	 * @return 성공 : 1, 실패 : 0
	 */
	public int updateStudy(BoardVO boardVo) throws RemoteException;
	
	/**
	 * 스터디 게시판 글 삭제
	 * boardVo의 정보를 이용하여 글 정보 삭제
	 * @param boardNum 삭제할 정보가 저장된 Vo객체
	 * @return 성공 : 1, 실패 : 0
	 */
	
	
	public int deleteStudy(int boardNum) throws RemoteException;
	
	/**
	 * 조회수 증가
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int upboardCount(BoardVO vo) throws RemoteException;

}
