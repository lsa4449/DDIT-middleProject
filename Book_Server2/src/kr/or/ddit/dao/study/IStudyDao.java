package kr.or.ddit.dao.study;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.RippleVO;

public interface IStudyDao {
	
	
	public List<BoardVO> selectStudy(String boardTitle);

	public List<BoardVO> BoardStudy();
	
	
	public BoardVO detailStudy(int cnt);
	
	public int insertStudy(BoardVO boardVo);
	
	
	public int updateStudy(BoardVO boardVo);
	
	
	public int deleteStudy(int boardNum);

	
	public int upboardCount(BoardVO vo);

}
