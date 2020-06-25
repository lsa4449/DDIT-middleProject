package kr.or.ddit.dao.mypage;

import java.util.List;

import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MyBookPageVO;
import kr.or.ddit.vo.WannaBookVO;

public interface IMyPageBookDao {
//나의 도서 내역
	
		
	
	/**
	 * 내가 현재 대여중인 도서 확인
	 * 로그인 세션의 memNum을 키값으로 상태가 반납인 도서번호 조회, 도서번호로 도서 쫙 뽑아오기
	 * @param MemeberVO vo -> 로그인세션의 memNum
	 * @return List<BookVO>
	 */
	public List<MyBookPageVO> selectMyBookRentaling(MemberVO memNum);
	
	
	/**
	 * 희망도서 신청 내역확인
	 * @param MemberVO 
	 * @return List<WannaBookVO>
	 * @author jaeho
	 * 
	 */
	public List<WannaBookVO> selectMyWannaBook(MemberVO memNum);
	
	/**
	 * 읽고 있는 전자책 확인
	 */
	
	
	/**
	 * 예약한 도서 확인
	 * 로그인 세션의 MemNum을 키값으로 예약테이블에서 예약번호를 조회하고 대여테이블에 해당 예약번호가 없는 도서 vo조회
	 * @param MemberVO
	 * @return List<BookVO>
	 * @author jaeho
	 */
	public List<MyBookPageVO> selectMyBookReserve(MemberVO memNum);
	
	/**
	 * 반납한 도서 내역 확인
	 * 로그인 세션의 MemNum을 키값으로 대여테이블에서 상태가 반납인 도서번호로 도서테이블의 도서vo 조회
	 * @param MemberVO
	 * @return List<BookVO>
	 * @author jaeho
	 */
	public List<MyBookPageVO> selectMyBookReturn(MemberVO memNum);
	
	
	
}
