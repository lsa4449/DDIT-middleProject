package kr.or.ddit.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.service.admin.AdminServiceImpl;
import kr.or.ddit.service.admin.IAdminService;
import kr.or.ddit.service.book.BookServiceImpl;
import kr.or.ddit.service.book.IBookService;
import kr.or.ddit.service.book.IWannaBookService;
import kr.or.ddit.service.book.WannaBookServiceImpl;
import kr.or.ddit.service.bookRental.BookRentalServiceImpl;
import kr.or.ddit.service.bookRental.IBookRentalService;
import kr.or.ddit.service.community.CommunityServiceImpl;
import kr.or.ddit.service.community.ICommunityService;
import kr.or.ddit.service.deal.DealServiceImpl;
import kr.or.ddit.service.deal.IDealService;
import kr.or.ddit.service.ebook.EBookServiceImpl;
import kr.or.ddit.service.ebook.IEBookService;
import kr.or.ddit.service.join.IJoinService;
import kr.or.ddit.service.join.JoinServiceImpl;
import kr.or.ddit.service.login.ILoginService;
import kr.or.ddit.service.login.LoginServiceImpl;
import kr.or.ddit.service.monthBook.IMonthBookService;
import kr.or.ddit.service.monthBook.MonthBookServiceImpl;
import kr.or.ddit.service.mypage.IMyPageBookService;
import kr.or.ddit.service.mypage.IMyPageInfoService;
import kr.or.ddit.service.mypage.IMyPagePointService;
import kr.or.ddit.service.mypage.IMyPageStudyRoomService;
import kr.or.ddit.service.mypage.MyPageBookServiceImpl;
import kr.or.ddit.service.mypage.MyPageInfoServiceImpl;
import kr.or.ddit.service.mypage.MyPagePointServiceImpl;
import kr.or.ddit.service.mypage.MyPageStudyRoomService;
import kr.or.ddit.service.notice.INoticeService;
import kr.or.ddit.service.notice.NoticeServiceImpl;
import kr.or.ddit.service.questions.IQuestionsService;
import kr.or.ddit.service.questions.QuestionsServiceImpl;
import kr.or.ddit.service.seat.ISeatService;
import kr.or.ddit.service.seat.SeatServiceImpl;
import kr.or.ddit.service.study.IStudyService;
import kr.or.ddit.service.study.StudyServiceImpl;
import kr.or.ddit.service.studyroom.IStudyroomService;
import kr.or.ddit.service.studyroom.StudyRoomServiceImpl;
import kr.or.ddit.service.usedbookboard.IUserBookBoardService;
import kr.or.ddit.service.usedbookboard.UserBookBoardServiceImpl;

public class ServerMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			//여기 다묶을거야~~ rebind할꺼야~~ - 재호
			
			Registry reg = LocateRegistry.createRegistry(8429);
			IMyPageBookService myPageBookService = MyPageBookServiceImpl.getInstance();
			/**
			 * 재혼
			 */
			IWannaBookService wannaBookService = WannaBookServiceImpl.getInstance();
			IEBookService eBookService = EBookServiceImpl.getInstance();
			IAdminService adminService = AdminServiceImpl.getInstance();
			IBookRentalService bookRentalService = BookRentalServiceImpl.getInstance();
			reg.rebind("myPageBookService", myPageBookService);
			reg.rebind("wannaBookService", wannaBookService);
			reg.rebind("eBookService", eBookService);
			reg.rebind("adminService", adminService);
			reg.rebind("bookRentalService", bookRentalService);

			
			/**
			 * 수아
			 */
			IBookService bookService = BookServiceImpl.getInstance();
			reg.rebind("bookService", bookService);
			
			IMyPagePointService myPagePointService = MyPagePointServiceImpl.getInstance();
			reg.bind("myPagePointService", myPagePointService);
			
			IMonthBookService monthService = MonthBookServiceImpl.getInstance();
			reg.rebind("monthService", monthService);
			
			/**
			 * 영현
			 */
			IJoinService joinService = JoinServiceImpl.getInstance();
			reg.rebind("joinService", joinService);
			
			ILoginService loginService = LoginServiceImpl.getInstance();
			reg.rebind("loginService", loginService); // controller에 lookUp에 일치시킬때 앞 매개변수가 client에 연결시켜주는 것
			
			IStudyroomService studyRoomService = StudyRoomServiceImpl.getInstance();
			reg.rebind("studyRoomService", studyRoomService);
			
			IMyPageStudyRoomService myPageStudyRoomService = MyPageStudyRoomService.getInstance();
			reg.rebind("myPageStudyRoomService", myPageStudyRoomService);
			
			ISeatService SeatService = SeatServiceImpl.getInstance();
			reg.rebind("SeatService", SeatService);
			
			
			
			/**
			 * 윤희
			 */
			INoticeService noticeService = NoticeServiceImpl.getInstance();
			reg.rebind("noticeService", noticeService);
			
			IMyPageInfoService MyPageInfoService = MyPageInfoServiceImpl.getInstance();
			reg.rebind("MyPageInfoService", MyPageInfoService);

			
			
			/**
			 * 동미
			 */
			IUserBookBoardService userBookBoardService = UserBookBoardServiceImpl.getInstance();
			reg.rebind("userBookBoardService", userBookBoardService);
			
			IDealService dealService = DealServiceImpl.getInstance();
			reg.rebind("dealService", dealService);
			/** 
			 * 성하
			 */
			ICommunityService communityService = CommunityServiceImpl.getInstance();
			reg.rebind("communityService", communityService);		
			IStudyService studyService = StudyServiceImpl.getInstance();
			reg.rebind("studyService", studyService);		
			IQuestionsService questionsService = QuestionsServiceImpl.getInstance();
			reg.rebind("questionsService", questionsService);
			
			
			
		/*	 에러났을때 test용 -윤희
			BoardVO vo = new BoardVO();
			vo.setBoardTitle("제목");
			vo.setBoardContent("내용");
			FilesVO vo1 = new FilesVO();
			vo1.setFileNum(2);
			vo1.setFileName("파일이름");      */
			
			//noticeService.insertNotice(vo, vo1);
			
			



			/*// 에러났을때 test용 - 윤희
			 * BoardVO vo = new BoardVO(); 
			 * noticeService.updateNotice(vo);
			 */
			 
			
			
			 
			 	 
			
		} catch (Exception e) {
			System.out.println("에러다!");
			e.printStackTrace();
		}
		System.out.println("띠용...♥");

	}
}
