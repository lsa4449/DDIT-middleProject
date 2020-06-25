package kr.or.ddit.service.book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.WannaBookVO;

public interface IWannaBookService extends Remote{
	// 희망도서
	
		/**
		 * 희망도서 신청 (희망도서 테이블에 insert, 승인 상태는 0으로 삽입)
		 * @param WannaBookVO
		 * @return 성공하면 null 실패하면 0 -> int cnt로 반환값 재설정
		 * @author jaeho
		 */
		
		public int insertWannaBook(WannaBookVO wannaBookvo) throws RemoteException;
		
		/**
		 * 희망도서 신청 승인 (희망 도서 테이블의 승인 상태 값 1로 변경)
		 * @param 상태값 String
		 * @return 성공하면 null 실패하면 0 -> int cnt로 반환값 재설정
		 * @author jaeho
		 */
		public int updateAcceptWannaBook(String num) throws RemoteException;
		
		/**
		 * 희망도서 신청 반려(희망 도서 테이블의 승인 상태값 2로 변경)
		 * @param 상태값 String
		 * @return 성공하면 null 실패하면 0 -> int cnt로 반환값 재설정
		 * @author jaeho
		 */
		public int updateRejectWannaBook(String num) throws RemoteException;
		
		/**
		 * 전체 희망도서 신청 내역 확인
		 * @param 로그인 세션의 회원번호를 키값으로 줌 memNum
		 * @return 희망도서 vo
		 * @author jaeho
		 */
		public List<WannaBookVO> selectWannaBook() throws RemoteException;
}
