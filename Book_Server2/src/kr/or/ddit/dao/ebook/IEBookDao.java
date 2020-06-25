package kr.or.ddit.dao.ebook;

import java.util.List;

import kr.or.ddit.vo.EBookVO;

public interface IEBookDao {
	
	/*
	 * ebook 전체 조회
	 */
	public List<EBookVO> selectEBookAll();
	
	/*
	 * 제목으로 조회
	 */
	public List<EBookVO> selectEBookName(EBookVO vo);
	
	/*
	 * 저자로 조회
	 */
	public List<EBookVO> selectEBookAuthor(EBookVO vo);
	

}
