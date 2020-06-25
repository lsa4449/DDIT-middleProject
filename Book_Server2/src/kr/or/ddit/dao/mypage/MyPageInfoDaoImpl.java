package kr.or.ddit.dao.mypage;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.dao.notice.INoticeDao;
import kr.or.ddit.dao.notice.NoticeDaoImpl;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;



public class MyPageInfoDaoImpl implements IMyPageInfoDao{

	private SqlMapClient smc;
	private static IMyPageInfoDao dao;
	
	private MyPageInfoDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	
	public static IMyPageInfoDao getInstance() {
		if(dao==null) {
			dao = new MyPageInfoDaoImpl();
		} 
		return dao;
	}
	
	
	//회원정보확인
	@Override
	public List<MemberVO> selectMyInfo(String id) {
		List<MemberVO> vo = null;
		
		try {
			vo =  smc.queryForList("info.selectMyInfo", id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return vo;
	}

	
	//회원정보수정
	@Override
	public int updateMyInfo(MemberVO memberVo) {
		int cnt = 0;

		try {
			cnt = smc.update("info.updateMyinfo", memberVo);
			System.out.println(cnt);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}

	
	
	
	
	

	
}
