package kr.or.ddit.dao.study;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;


import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.RippleVO;

public class StudyDaoImpl implements IStudyDao {
	
	private SqlMapClient smc;
	private static IStudyDao dao;
	
	private StudyDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IStudyDao getInstance() {
		if(dao==null) {
			dao = new StudyDaoImpl();
		} 
		return dao;
	}

	//게시판 제목검색
	@Override
	public List<BoardVO> selectStudy(String boardTitle) {
		List<BoardVO> vo = null;
		 try {
			vo = smc.queryForList("studyboard.selectBoard", boardTitle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
		

	//게시판 전체조회
	@Override
	public List<BoardVO> BoardStudy() {
		List<BoardVO> list = null;
		try {
			list = smc.queryForList("studyboard.selectStudy");
		} catch (SQLException e) {
			System.out.println("selectAll 에러");
			e.printStackTrace();
		}
		return list;
	}

	//게시판 상세조회
	@Override
	public BoardVO detailStudy(int cnt) {
		BoardVO vo = null;
		try {
			vo = (BoardVO) smc.queryForObject("studyboard.detailStudy", cnt);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return vo;
	}

	//게시판 글작성
	@Override
	public int insertStudy(BoardVO boardVo) {
		int cnt = 0;
		try {
			Object object= smc.insert("studyboard.insertStudy", boardVo);
			if(object == null) {
				cnt = 1;
			}
		}catch(SQLException e) {
			System.out.println("insert에러");
			e.printStackTrace();
		}
		return cnt;
	}

	//게시판 수정
	@Override
	public int updateStudy(BoardVO boardVo) {
		int cnt = 0;

		try {
			cnt = smc.update("studyboard.updateStudy", boardVo);
			System.out.println(cnt);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	
	}

	//게시판 삭제
	@Override
	public int deleteStudy(int boardNum) {
		int cnt = 0;

		try {
			cnt= smc.delete("studyboard.deleteStudy", boardNum);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 조회수 증가
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int upboardCount(BoardVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("questions.upboardCount", vo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	
}
