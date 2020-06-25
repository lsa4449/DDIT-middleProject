package kr.or.ddit.dao.bookRental;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BookRentalVO;

public class BookRentalDaoImpl implements IBookRentalDao {

	private SqlMapClient smc;
	private static IBookRentalDao dao;

	public BookRentalDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IBookRentalDao getInstance() {
		if (dao == null) {
			dao = new BookRentalDaoImpl();
		}
		return dao;
	}

	@Override
	public List<BookRentalVO> selectAllBookRental() {

		List<BookRentalVO> list = null;

		try {
			list = smc.queryForList("bookRental.selectAllBookRental");
		} catch (Exception e) {
			System.out.println("전체 조회 에러");
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public int insertBookRental(BookRentalVO vo) {
		int cnt =0;
		try {
			Object object = smc.insert("bookRental.insertBookRental", vo);
			if(object ==null) {
				cnt = 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
