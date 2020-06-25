package kr.or.ddit.service.mypage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMyPageInfoService extends Remote{
	
		/**
		 * 회원정보 확인
		 * @param id
		 * @return
		 */
		public List<MemberVO> selectMyInfo (String id) throws RemoteException;
		
		
		/**
		 * 회원정보 수정
		 * @param memberVo
		 * @return
		 */
		public int updateMyInfo (MemberVO memberVo) throws RemoteException;
	
}
