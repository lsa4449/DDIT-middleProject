package kr.or.ddit.dao.monthBook;

import java.util.List;

import kr.or.ddit.vo.MonthBookVO;
import kr.or.ddit.vo.MonthFileVO;

public interface IMonthBookDao {
	
	/**
	 * 이달의도서 조회
	 * @param BookVO
	 * @author sua
	 */
	public List<MonthBookVO> selectMonthBookAll();
	
	/**
	 * 이달의도서 등록 
	 * @param BookVO
	 * @author sua
	 */
	public int insertMonthBook(MonthBookVO mVo);
	

	/**
	 * 이달의도서 삭제 
	 * @param BookVO
	 * @author sua
	 */
	public int deleteMonthBook(int monthBookNum);
	
	
	/**
	 * 파일 조회 
	 * @param BookVO
	 * @author sua
	 */
	public MonthFileVO selectFiles(int num);
	
	/**
	 * 파일 등록
	 * @param BookVO
	 * @author sua
	 */
	public int insertfiles(MonthFileVO vo);
}
