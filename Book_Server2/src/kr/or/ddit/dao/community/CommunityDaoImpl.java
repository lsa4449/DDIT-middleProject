package kr.or.ddit.dao.community;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.RippleVO;

public class CommunityDaoImpl implements ICommunityDao{
	
	private SqlMapClient smc;
	private static ICommunityDao dao;
	
	private CommunityDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ICommunityDao getInstance() {
		if(dao==null) {
			dao = new CommunityDaoImpl();
		} 
		return dao;
	}
	
	//게시글 제목검색
	@Override
	public List<BoardVO> selectBoard(String boardTitle) {
		List<BoardVO> vo = null;
		 try {
			vo = smc.queryForList("board.selectBoard", boardTitle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}


	//게시글 전체조회
	@Override
	public List<BoardVO> Board() {
		List<BoardVO> list = null;
		try {
			list = smc.queryForList("board.selectCommunity");
		} catch (SQLException e) {
			System.out.println("selectAll 에러");
			e.printStackTrace();
		}
		return list;
	}

	//게시글 상세조회
	@Override
	public BoardVO detailBoard(int cnt) {
		BoardVO vo = null;
		try {
			vo = (BoardVO) smc.queryForObject("board.detail", cnt);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return vo;
	}

	//게시판 글쓰기 등록
	@Override
	public int insertBoard(BoardVO boardVo) {
		
		/*
		 insert -> 성공시 null , 실패 err
		update -> 성공시 1, 실패시 0
		delete -> 성공시 삭제한 row수, 실패시 0
		
		select -> 성공시 vo, list<vo>
		*/
		int cnt = 0;
		try {
			Object object= smc.insert("board.insertCommunity", boardVo);
			if(object == null) {
				cnt = 1;
			}
		}catch(SQLException e) {
			System.out.println("insert에러");
			e.printStackTrace();
		}
		return cnt;
	}

	//게시글 수정
	@Override
	public int updateBoard(BoardVO boardVo) {
		int cnt = 0;

		try {
			cnt = smc.update("board.updateBoard", boardVo);
			System.out.println(cnt);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	
	}

	//게시글 삭제
	@Override
	public int deleteBoard(int boardNum) {	
	int cnt = 0;

	try {
		cnt= smc.delete("board.deleteBoard", boardNum);
		
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
	public int upboardCount(BoardVO vo){
		int cnt = 0;

		try {
			cnt = smc.update("board.upboardCount", vo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}
	

}
