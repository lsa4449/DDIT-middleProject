package kr.or.ddit.dao.questions;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.RippleVO;

public class QuestionsDaoImpl implements IQuestionsDao{
	
	private SqlMapClient smc;
	private static IQuestionsDao dao;
	
	private QuestionsDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	public static IQuestionsDao getInstance() {
		if(dao==null) {
			dao = new QuestionsDaoImpl();
		} 
		return dao;
	}


	//문의사항 게시판 제목검색
	@Override
	public List<BoardVO> selectQuestions(String boardTitle){
		List<BoardVO> vo = null;
		 try {
			vo = smc.queryForList("questions.selecttitleQuestions", boardTitle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	//문의사항 전체조회
	@Override
	public List<BoardVO> Questions() {
		List<BoardVO> list = null;
		try {
			list = smc.queryForList("questions.selectQuestions");
		} catch (SQLException e) {
			System.out.println("selectAll 에러");
			e.printStackTrace();
		}
		return list;
	}

	//문의사항 상세조회
	@Override
	public BoardVO detailQuestions(int cnt){
		BoardVO vo = null;
		try {
			vo = (BoardVO) smc.queryForObject("questions.detailQuestions", cnt);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return vo;
	}

	//문의사항 게시판 글 쓰기  등록
	@Override
	public int insertQuestions(BoardVO boardVo) {
		int cnt = 0;
		try {
			Object object= smc.insert("questions.insertQuestions", boardVo);
			if(object == null) {
				cnt = 1;
			}
		}catch(SQLException e) {
			System.out.println("insert에러");
			e.printStackTrace();
		}
		return cnt;
	}

	//문의사항 게시판 글 수정
	@Override
	public int updateQuestions(BoardVO boardVo) {
		int cnt = 0;

		try {
			cnt = smc.update("questions.updateQuestions", boardVo);
			System.out.println(cnt);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	
	}

	//문의사항 게시판 글 삭제
	@Override
	public int deleteQuestions(int boardNum) {
		int cnt = 0;

		try {
			cnt= smc.delete("questions.deleteQuestions", boardNum);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	//문의사항 게시판 전체댓글조회
	@Override
	public RippleVO ripple(int cnt) {
		RippleVO vo = new RippleVO();
		try {
			vo = (RippleVO) smc.queryForObject("questions.rippleSelect", cnt);
		} catch (SQLException e) {
			System.out.println("selectAll 에러");
			e.printStackTrace();
		}
		return vo;
	}

	//문의사항 게시판 댓글 달기  
	@Override
	public int rippleQuestions(RippleVO rippleVo){
		int cnt = 0;
		try {
			Object object= smc.insert("questions.rippleQuestion", rippleVo);
			if(object == null) {
				cnt = 1;
			}
		}catch(SQLException e) {
			System.out.println("insert에러");
			e.printStackTrace();
		}
		return cnt;
	}

	//문의사항 조회수
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
