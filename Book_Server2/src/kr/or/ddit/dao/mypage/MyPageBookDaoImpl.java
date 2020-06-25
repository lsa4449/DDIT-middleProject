package kr.or.ddit.dao.mypage;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MyBookPageVO;
import kr.or.ddit.vo.WannaBookVO;

public class MyPageBookDaoImpl implements IMyPageBookDao {
	
	private static IMyPageBookDao dao;
	
	private SqlMapClient smc;
	
	
	
	private MyPageBookDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMyPageBookDao getInstance() {
		if(dao ==null) {
			dao = new MyPageBookDaoImpl();
		}
		return dao;
	}; 
	
	
	
	//현재 대여중인 도서
	@Override
	public List<MyBookPageVO> selectMyBookRentaling(MemberVO memNum) {
		List<MyBookPageVO> list = null;
		try{
			list = smc.queryForList("myPageBook.selectMyBookRentaling", memNum);
		}catch (Exception e) {
			 System.out.println("현재 대여중인 도서 에러");
			e.printStackTrace();
		}
		return list;
	}

	//예약한 도서
	@Override
	public List<MyBookPageVO> selectMyBookReserve(MemberVO memNum) {
		
		
		List<MyBookPageVO> list = null;
		try {
			list = smc.queryForList("myPageBook.selectMyBookReserve", memNum);
		}catch (Exception e) {
			System.out.println("selectMyBookReserve 에러");
			e.printStackTrace();
		}
		return list;
	}

	//반납한 도서
	@Override
	public List<MyBookPageVO> selectMyBookReturn(MemberVO memNum) {
		
		List<MyBookPageVO> list =null;
		
		try {
			list = smc.queryForList("myPageBook.selectMyBookReturn", memNum);
		}catch (Exception e) {
			System.out.println("내가 빌렸던 도서 내역 에러");
			e.printStackTrace();
		}
		return list;
	}

	//내가 신청한 도서
	@Override
	public List<WannaBookVO> selectMyWannaBook(MemberVO memNum) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
