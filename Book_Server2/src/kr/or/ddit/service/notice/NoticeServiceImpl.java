package kr.or.ddit.service.notice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.notice.INoticeDao;
import kr.or.ddit.dao.notice.NoticeDaoImpl;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;

public class NoticeServiceImpl extends UnicastRemoteObject implements INoticeService{

	private INoticeDao dao;
	private static INoticeService service;
	
	private NoticeServiceImpl() throws RemoteException {
		super();
		dao = NoticeDaoImpl.getInstance();
	}
	
	public static INoticeService getInstance() throws RemoteException{
		if(service == null) {
			service = new NoticeServiceImpl();
		}
		return service;
	}
	
	
    //제목검색메소드
	@Override 
	public List<BoardVO> selectNotice(String boardTitle) throws RemoteException {
		return dao.selectNotice(boardTitle);
	}

	
	//공지사항전체 게시글 메소드
	@Override
	public List<BoardVO> selectAll() throws RemoteException {
		return dao.selectAll();
	}

	
	//상세보기 메소드 - 지워야하는데..잠시보류
	@Override
	public List<BoardVO> detailNotice(String boardTitle) throws RemoteException {
		return dao.detailNotice(boardTitle);
	}

	
	//글 등록 & 첨부파일 메소드
	@Override
	public int insertNotice(BoardVO boardVo, FilesVO filesVo) throws RemoteException {
		return dao.insertNotice(boardVo, filesVo);
	}

	
	//글 수정 메소드
	@Override
	public int updateNotice(BoardVO boardVo) throws RemoteException {
		return dao.updateNotice(boardVo);
	}

	
	//글 삭제 메소드
	@Override
	public int deleteNotice(int boardNum) throws RemoteException {
		return dao.deleteNotice(boardNum);
	}

	
	//첨부파일 등록 메소드
//	@Override
//	public int insertFile(FilesVO filesVo) throws RemoteException {
//		return dao.insertFile(filesVo);
//	}

	
	//첨부파일 삭제 메소드
	@Override
	public int deleteFile(int filesNum) throws RemoteException {
		return dao.deleteFile(filesNum);
	}

	//해당 게시판의 첨부파일 정보 조회
	@Override
	public FilesVO selectNoticeFile(int boardNum) throws RemoteException{
		return dao.selectNoticeFile(boardNum);
	}

	
	//조회수
	@Override
	public int upboardCount(BoardVO vo) throws RemoteException {
		return dao.upboardCount(vo);
	}

	
	
	
	
	

}
