package kr.or.ddit.service.admin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberManageVO;

public interface IAdminService extends Remote{

	// 회원 전체 조회
	public List<MemberManageVO> selectMemberAll() throws RemoteException;

	// 회원 상태 정지로
	public int updateMemberStateStop(int memNum) throws RemoteException;

	// 회원 상태 우수
	public int updateMemberStateGood(int memNum) throws RemoteException;

	// 회원 상태 일반
	public int updateMemberStateNomal(int memNum) throws RemoteException;

	// 회원 상태 경고
	public int updateMemberStateWarning(int memNum) throws RemoteException;
}
