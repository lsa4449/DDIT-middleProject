package kr.or.ddit.dao.login;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;

public class LoginDaoImpl implements ILoginDao {
	
	private SqlMapClient smc;
	private static ILoginDao dao;
	
	private LoginDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ILoginDao getInstance() {
		if(dao==null) {
			dao = new LoginDaoImpl();
		} 
		return dao;
	}
	//싱글톤

	@Override
	public MemberVO selectMember(String id) {

		
		
		return null;
	}

	@Override
	public MemberVO selectEmailMemberId(MemberVO memberVo) {
		MemberVO vo = null;
		
		try {
			vo = (MemberVO) smc.queryForObject("login.selectSearchID_PW" ,memberVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public MemberVO selctPhoneMemberId(MemberVO memberVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectEmailMemeberPass(MemberVO memberVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectPhoneMemberPass(MemberVO memberVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO findPassUser(MemberVO memberVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectLoginMember(MemberVO memberVo) {
		MemberVO vo = null;
		
		try {
			vo = (MemberVO) smc.queryForObject("login.selectLoginMember", memberVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
}
