package kr.or.ddit.service.admin;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.admin.AdminDaoImpl;
import kr.or.ddit.dao.admin.IAdminDao;
import kr.or.ddit.vo.MemberManageVO;

public class AdminServiceImpl extends UnicastRemoteObject implements IAdminService{
	
	private static IAdminService service;
	
	private IAdminDao dao;
	
	public AdminServiceImpl() throws RemoteException{
		dao = AdminDaoImpl.getInstance();
	}
	
	public static IAdminService getInstance()  throws RemoteException{
		if(service == null) {
			service = new AdminServiceImpl();
		}
		return service;
	}

	@Override
	public List<MemberManageVO> selectMemberAll() throws RemoteException{

		return dao.selectMemberAll();
	}
	
	@Override
	public int updateMemberStateGood(int memNum) throws RemoteException {
		
		return dao.updateMemberStateGood(memNum);
	}
	
	@Override
	public int updateMemberStateNomal(int memNum) throws RemoteException {
	
		return dao.updateMemberStateNomal(memNum);
	}
	
	@Override
	public int updateMemberStateStop(int memNum) throws RemoteException {
		
		return dao.updateMemberStateStop(memNum);
	}
	
	@Override
	public int updateMemberStateWarning(int memNum) throws RemoteException {
		// 
		return dao.updateMemberStateWarning(memNum);
	}
	
	

}
