package kr.or.ddit.service.mypage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.mypage.IMyPageInfoDao;
import kr.or.ddit.dao.mypage.MyPageInfoDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MyPageInfoServiceImpl extends UnicastRemoteObject implements IMyPageInfoService{

	private static IMyPageInfoService service;
	private IMyPageInfoDao dao;
	
	
	protected MyPageInfoServiceImpl() throws RemoteException {
		super();
		dao = MyPageInfoDaoImpl.getInstance();
	}

	public static IMyPageInfoService getInstance() throws RemoteException{
		if(service == null) {
			service = new MyPageInfoServiceImpl();
		}
		return service;
	}
	
	
	
	//회원정보 확인
	@Override
	public List<MemberVO> selectMyInfo(String id) throws RemoteException {
		return dao.selectMyInfo(id);
	}

	
	//회원정보 수정
	@Override
	public int updateMyInfo(MemberVO memberVo) throws RemoteException {
		return dao.updateMyInfo(memberVo);
	}

	
	
}
