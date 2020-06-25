package kr.or.ddit.dao.admin;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.MemManageVO;
import kr.or.ddit.vo.MemberManageVO;

public class AdminDaoImpl implements IAdminDao{
	
	private SqlMapClient smc;
	
	private static IAdminDao dao;

	 private AdminDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IAdminDao getInstance() {
		if (dao == null) {
			dao = new AdminDaoImpl(); 
		}
		return dao;
	}
	
	
	
	// 회원 정보 조회
	@Override
	public List<MemberManageVO> selectMemberAll() {
		
		List<MemberManageVO> list = null;
		try {
			list = smc.queryForList("admin.selectMemberAll");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 회원 상태 정지로 변경
	
	
	@Override
	public int updateMemberStateGood(int memNum) {
		int cnt = 0;
		try {
			cnt = smc.update("admin.updateMemberStateGood", memNum);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMemberStateNomal(int memNum) {
		int cnt = 0;
		try {
			cnt = smc.update("admin.updateMemberStateNomal", memNum);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int updateMemberStateStop(int memNum) {
		int cnt = 0;
		try {
			cnt = smc.update("admin.updateMemberStateStop", memNum);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int updateMemberStateWarning(int memNum) {
		int cnt = 0;
		try {
			cnt = smc.update("admin.updateMemberStateWarning", memNum);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
