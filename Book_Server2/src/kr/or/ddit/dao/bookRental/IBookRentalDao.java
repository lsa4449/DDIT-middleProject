package kr.or.ddit.dao.bookRental;

import java.util.List;

import kr.or.ddit.vo.BookRentalVO;

public interface IBookRentalDao {
	
	public List<BookRentalVO> selectAllBookRental();
	
	//도서 대여 테이블에 정보 삽입
	public int insertBookRental(BookRentalVO vo);
	
	

}
