package kr.or.ddit.dao.monthBook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.MonthBookVO;
import kr.or.ddit.vo.MonthFileVO;

public class MonthBookDaoImpl implements IMonthBookDao {

	private SqlMapClient smc;
	private static IMonthBookDao dao;

	private MonthBookDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IMonthBookDao getInstance() {
		if (dao == null) {
			dao = new MonthBookDaoImpl();
		}
		return dao;
	}

	@Override
	public List<MonthBookVO> selectMonthBookAll() {

		List<MonthBookVO> list = null;

		try {
			list = smc.queryForList("monthBook.selectMonthBookAll");
		} catch (Exception e) {
			System.out.println("book에러");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertMonthBook(MonthBookVO mVo) {

		int cnt = 0;

		try {
			Object obj = smc.insert("monthBook.insertMonthBook", mVo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}


	@Override
	public MonthFileVO selectFiles(int num) {

		MonthFileVO vo = null;

		try {
			vo = (MonthFileVO) smc.queryForObject("monthBook.selectFiles", num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int insertfiles(MonthFileVO vo) {

		int cnt = 0;

		try {
			Object obj = smc.insert("monthBook.insertfiles", vo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int deleteMonthBook(int monthBookNum) {

		int cnt = 0;

		try {
			cnt = smc.delete("monthBook.deleteMonthBook", monthBookNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

}
