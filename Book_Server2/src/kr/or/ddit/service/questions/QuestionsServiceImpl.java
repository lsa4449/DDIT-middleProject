package kr.or.ddit.service.questions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;



import kr.or.ddit.dao.questions.IQuestionsDao;
import kr.or.ddit.dao.questions.QuestionsDaoImpl;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.RippleVO;

public class QuestionsServiceImpl extends UnicastRemoteObject implements IQuestionsService{

	private IQuestionsDao dao;
	
	private static IQuestionsService service;
	
	private QuestionsServiceImpl() throws RemoteException {
		super();
		dao = QuestionsDaoImpl.getInstance();
	}
	
	public static IQuestionsService getInstance() throws RemoteException {
		if(service== null) {
			service = new QuestionsServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<BoardVO> selectQuestions(String boardTitle) throws RemoteException {
		
		return dao.selectQuestions(boardTitle);
	}

	@Override
	public List<BoardVO> Questions() throws RemoteException {
		
		return dao.Questions();
	}

	@Override
	public BoardVO detailQuestions(int cnt) throws RemoteException {
		
		return dao.detailQuestions(cnt);
	}

	@Override
	public int insertQuestions(BoardVO boardVo) throws RemoteException {
		
		return dao.insertQuestions(boardVo);
	}

	@Override
	public int updateQuestions(BoardVO boardVo) throws RemoteException {
		
		return dao.updateQuestions(boardVo);
	}

	@Override
	public int deleteQuestions(int boardNum) throws RemoteException {
		
		return dao.deleteQuestions(boardNum);
	}

	@Override
	public RippleVO ripple(int cnt) throws RemoteException {
		
		return dao.ripple(cnt);
	}


	@Override
	public int rippleQuestions(RippleVO rippleVo) throws RemoteException {
		
		return dao.rippleQuestions(rippleVo);
	}

	public int upboardCount(BoardVO vo) throws RemoteException{
		
		return dao.upboardCount(vo);
	}

}
