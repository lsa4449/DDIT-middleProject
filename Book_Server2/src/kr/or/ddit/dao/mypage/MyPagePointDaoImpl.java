package kr.or.ddit.dao.mypage;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.PointCategoryVO;
import oracle.net.aso.b;
import kr.or.ddit.vo.BookVO;
import oracle.net.aso.b;
import kr.or.ddit.vo.PointCategoryVO;

public class MyPagePointDaoImpl implements IMyPagePointDao {

	private SqlMapClient smc;
	private static IMyPagePointDao dao;

	private MyPagePointDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IMyPagePointDao getInstance() {
		if (dao == null) {
			dao = new MyPagePointDaoImpl();
		}
		return dao;
	}

	@Override

	public int insertNowPoint(PointCategoryVO pVo) {
		int cnt = 0;

		try {

			Object obj = smc.insert("myPagePoint.insertNowPoint", pVo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String selectAllPoint(int memNum) {

		String cnt = "";
		
		try {
			cnt = (String) smc.queryForObject("myPagePoint.selectAllPoint", memNum);
		} catch (Exception e) {
			System.out.println("전체 조회 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertTransPoint(PointCategoryVO pVo) {
		int cnt = 0;

		try {

			Object obj = smc.insert("myPagePoint.insertTransPoint", pVo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String selectAllPointTrans(int memNum) {
		String cnt = "";

		try {
			cnt = (String) smc.queryForObject("myPagePoint.selectAllPointTrans", memNum);
		} catch (Exception e) {
			System.out.println("전체 조회 에러");
			e.printStackTrace();
		}
		return cnt;
	}

}
