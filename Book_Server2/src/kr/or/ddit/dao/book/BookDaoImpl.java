package kr.or.ddit.dao.book;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BookVO;

public class BookDaoImpl implements IBookDao {

	private SqlMapClient smc;
	private static IBookDao dao;

	public BookDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IBookDao getInstance() {
		if (dao == null) {
			dao = new BookDaoImpl();
		}
		return dao;
	}

	@Override
	public int insertBook(BookVO bookVo) {

		int cnt = 0;

		try {
			Object obj = smc.insert("book.insertBook", bookVo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int returnBook(String bookState) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BookVO> bookList() {

		List<BookVO> list = null;
		try {
			list = smc.queryForList("book.selectBook");
		} catch (Exception e) {
			System.out.println("전체 조회 에러");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookVO> categoryBookList(int CategoryNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> purchaseExpectBook(int memNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> selectSearchBook(BookVO bookVo) {

		List<BookVO> list = null;

		try {
			list = smc.queryForList("book.selectSearchBook", bookVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateBook(BookVO bookVo) {

		int cnt = 0;

		try {
			cnt = smc.update("book.updateBook", bookVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int delete(int bookNum) {

		int cnt = 0;

		try {
			cnt = smc.delete("book.deleteBook", bookNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int beforeReserBook(String bookState) {
		
		int cnt = 0;

		try {
			cnt = smc.update("book.updateBeforeReserBook", bookState);
		} catch (SQLException e) {
			System.out.println("도서 대기 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int afterReserBook(int bookNum) {
		int cnt = 0;

		try {
			cnt = smc.update("book.afterReserBook", bookNum);
			System.out.println(cnt);
		} catch (SQLException e) {
			System.out.println("도서 예약 에러");
			e.printStackTrace();
		}
		return cnt;
	}
}
