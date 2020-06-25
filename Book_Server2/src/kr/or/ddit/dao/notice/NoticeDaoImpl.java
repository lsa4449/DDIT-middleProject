package kr.or.ddit.dao.notice;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.dao.community.CommunityDaoImpl;
import kr.or.ddit.dao.community.ICommunityDao;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;

public class NoticeDaoImpl implements INoticeDao{

	private SqlMapClient smc;
	private static INoticeDao dao;
	
	private NoticeDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static INoticeDao getInstance() {
		if(dao==null) {
			dao = new NoticeDaoImpl();
		} 
		return dao;
	}
	
	//제목검색 메소드
	@Override
	public List<BoardVO> selectNotice(String boardTitle) {
		List<BoardVO> vo = null;
		 try {
			vo = smc.queryForList("notice.selectNotice", boardTitle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	
	//글 전체 목록
	@Override
	public List<BoardVO> selectAll() {
		List<BoardVO> list=null;
		
		try {
			list = smc.queryForList("notice.selectAll");
		}catch (Exception e) {
			System.out.println("selectAll 에러");
			e.printStackTrace();
		}
		return list;
	}

	

	
	//글 상세내용 
	@Override
	public List<BoardVO> detailNotice(String boardTitle) {
		return null;
		
		
	}

	
	//글 등록 & 첨부파일 등록
	@Override
	public int insertNotice(BoardVO boardVo, FilesVO filesVo) {
		int cnt = 0;
		Connection conn = null;
		try {
			
			conn =  smc.getDataSource().getConnection();
			conn.setAutoCommit(false);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Object obj = smc.insert("notice.insertNotice", boardVo);
			filesVo.setBoardNum(boardVo.getBoardNum());
			//filesVo.setFileName("파임명");
			obj = smc.insert("notice.insertFiles", filesVo);
			
			if (obj == null) {
				cnt = 1;
			}
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return cnt;
	}
	

	//글 수정
	@Override
	public int updateNotice(BoardVO boardVo) {
		int cnt = 0;

		try {
			cnt = smc.update("notice.updateNotice", boardVo);
			System.out.println(cnt);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	
	}

	
	//글 삭제
	@Override
	public int deleteNotice(int boardNum) {
		int cnt = 0;

		try {
			cnt= smc.delete("notice.deleteNotice", boardNum);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	
	
	//첨부파일 등록
//	@Override
//	public int insertFile(FilesVO filesVo) {
//		int cnt = 0;

//		try {
//			Object obj = smc.insert("notice.insertFile", filesVo);
//			if (obj == null) {
//				cnt = 1;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return cnt;
//	}

	
	
	//첨부파일 삭제
	@Override
	public int deleteFile(int filesNum) {
		int cnt = 0;

		try {
			cnt= smc.delete("notice.deleteFile", filesNum);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;	
	}

	@Override
	public FilesVO selectNoticeFile(int boardNum) {
		FilesVO vo = new FilesVO();
		 try {
			vo = (FilesVO) smc.queryForObject("notice.selectNoticeFile", boardNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	
	
	@Override
	public int upboardCount(BoardVO vo) throws RemoteException {
		int cnt = 0;

		try {
			cnt = smc.update("notice.upboardCount", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	
	

}
