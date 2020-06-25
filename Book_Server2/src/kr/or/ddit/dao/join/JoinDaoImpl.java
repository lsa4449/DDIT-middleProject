package kr.or.ddit.dao.join;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;

public class JoinDaoImpl implements IJoinDao {
	
	private SqlMapClient smc;
	private static IJoinDao dao;
	
	private JoinDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IJoinDao getInstance() {
		if(dao==null) {
			dao = new JoinDaoImpl();
		} 
		return dao;
	}

	@Override
	public int insertMemberSns(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMember(MemberVO memberVO) {
		int cnt = 0;		
		try {
			Object obj = smc.insert("join.insertMember", memberVO);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
	
	//ID중복확인
	@Override
	public int selectIdMember(String id) {
		MemberVO vo = null;
		int cnt = 0;
		try {
			vo =  (MemberVO)smc.queryForObject("join.selectIdMember",id);
			if(vo != null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println(cnt);
		return cnt;
	}
	
	//닉네임 중복확인
	@Override
	public int selectNickNameMember(String nickName) {
		MemberVO vo =null;
		int cnt = 0;
		try {
			 vo = (MemberVO) smc.queryForObject("join.selectNickNameMember", nickName);
			if(vo != null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
 
}
