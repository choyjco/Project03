package ddit.project03.sec01.controller;

import java.util.HashMap;
import java.util.Map;

import ddit.project03.sec01.service.BookService;
import ddit.project03.sec01.service.BorrowBookService;
import ddit.project03.sec01.service.MemberService;
import ddit.project03.sec01.service.RequestBookService;
import ddit.project03.sec01.service.ReservationService;
import ddit.project03.sec01.service.SeatService;
import ddit.project03.sec01.util.View;

public class Controller {

	public static Map<String, Object> sessionStorage = new HashMap<String, Object>();

	static MemberService memService = MemberService.getInstance();
	static BorrowBookService borrowbookService = BorrowBookService.getInstance();
	static BookService bookService = BookService.getInstance();
	static ReservationService reservationService = ReservationService.getInstance();
	static SeatService seatService = SeatService.getInstance();

	public static void main(String[] args) {
		serviceToView();

	}

	// 서비스와 뷰 연결
	public static void serviceToView() {
		int view = View.HOME;
		while (true) {
			switch (view) {

			case View.HOME:
				view = MemberService.home();
				break; // 메인 홈
			case View.HOME_END:
				view = MemberService.homeEnd();
				break; // 종료
			case View.MEM_HOME:
				view = MemberService.memHome();
				break; // 회원 홈화면
			case View.ADMIN_HOME:
				view = MemberService.adminHome();
				break; // 관리자 홈화면

			case View.MEMBER_LOGIN:
				view = MemberService.login();
				break; // 로그인
			case View.MEMBER_FIND_PW:
				view = MemberService.passwdFind();
				break; // 패스워드 찾기

			case View.ADMPAGE:
				view = MemberService.admpage();
				break; // 회원 등록
			case View.MEMBER_INSERT:
				view = MemberService.insert();
				break; // 회원 등록
			case View.MEMBER_DELETE:
				view = MemberService.memDelete();
				break; // 회원 삭제

			case View.MYPAGE:
				view = MemberService.mypage();
				break; // 회원 마이페이지
			case View.MYPAGE_UPDATE:
				view = MemberService.memUpdate();
				break; // 개인정보 수정페이지
			case View.MEMBER_UPDATE_PWD:
				view = MemberService.passwdUpdate();
				break; // 패스워드 변경
			case View.MEMBER_UPDATE_TEL:
				view = MemberService.telnoUpdate();
				break; // 전화번호 변경
			case View.MEMBER_LOGOUT:
				view = MemberService.logout();
				break; // 로그아웃

			// 도서
			case View.BOOK_MANAGER:
				view = BookService.bookmanager();
				break; // 도서관리 (관리자)

			case View.BOOK_SEARCH:
				view = BookService.findBook();
				break; // 도서검색
			case View.BOOK_SEARCH_N:
				view = BookService.findBookName();
				break; // 도서명으로 찾기
			case View.BOOK_SEARCH_W:
				view = BookService.findBookWriter();
				break; // 저자명으로 찾기

			case View.BOOK_INSERT:
				view = BookService.AddNewBook();
				break; // 도서등록
			case View.BOOK_UPDATE:
				view = BookService.bookInfoUpdate();
				break; // 도서정보 변경
			case View.BOOK_UPDATE_BOOKNO:
				view = BookService.modifyBookNo();
				break; // 도서번호 변경
			case View.BOOK_UPDATE_BOOKNAME:
				view = BookService.modifyBookName();
				break; // 도서명 변경
			case View.BOOK_UPDATE_WRITER:
				view = BookService.modifyWriter();
				break; // 저자명 변경
			case View.BOOK_UPDATE_COM:
				view = BookService.modifyCom();
				break; // 출판사 변경
			case View.BOOK_UPDATE_YEAR:
				view = BookService.modifyYear();
				break; // 출판년도 변경
			case View.BOOK_UPDATE_BORRSTATUS:
				view = BookService.modifyBorrStatus();
				break; // 도서상태 변경
			case View.BOOK_UPDATE_CLACODE:
				view = BookService.modifyClaCode();
				break; // 도서분류번호 변경

			case View.BOOK_DELETE:
				view = BookService.delete();
				break; // 도서삭제

			// 도서대여
			case View.BOOK_BORROW:
				view = BorrowBookService.bookbrrowmanager();
				break; // 도서대여화면
			case View.BOOK_BORROW_M:
				view = BorrowBookService.borrowBook();
				break; // 도서대여하기

			// 도서반납
			case View.BOOK_RETURN:
				view = BorrowBookService.returnBookmanager();
				break; // 도서반납화면
			case View.BOOK_RETURN_M:
				view = BorrowBookService.returnBook();
				break; // 도서반납하기

			// 도서 대출 리스트 조회
			case View.BOOK_BORROW_LIST_S:
				view = BorrowBookService.borrowListS();
				break; // 나의 도서 대출 리스트 (회원)
			case View.BOOK_OVERDUE_MANAGE:
				view = BorrowBookService.bookBorrowOverListmanager();
				break; // 대출/연체관리 페이지 (관리자)
			case View.BOOK_BORROW_LIST_M:
				view = BorrowBookService.bookbrrowlistM();
				break; // 대출리스트 조회 메뉴

			case View.BOOK_BORROW_LIST_BORRNO:
				view = BorrowBookService.borrowListBorrNo();
				break; // 대출번호로 조회
			case View.BOOK_BORROW_LIST_BORRDATE:
				view = BorrowBookService.borrowListBorrDate();
				break; // 대출일로 조회
			case View.BOOK_BORROW_LIST_BROOSTATUS:
				view = BorrowBookService.borrowListBorrStatus();
				break; // 반납상태로 조회
			case View.BOOK_BORROW_LIST_MEMNO:
				view = BorrowBookService.borrowListMemNo();
				break; // 회원번호로 조회
			case View.BOOK_BORROW_LIST_BOOKNO:
				view = BorrowBookService.borrowListBookNo();
				break; // 도서번호로 조회

			// 연체 리스트 조회
			case View.BOOK_OVERDUE_LIST:
				view = BorrowBookService.overdueList();
				break; // 연체리스트 조회

			// 열람실
			case View.READIND_ROOM_MANAGER:
				view = reservationService.readingRoomManager();
				break; // 열람실 관리 (관리자)
			case View.SEAT_RES_LIST:
				view = reservationService.seatList();
				break; // 열람실 예약 정보 조회 (관리자)
			case View.ADM_SEAT_INQUIRY:
				view = seatService.admcurrSeat();
				break; // 열람실 좌석 조회 (관리자)

			case View.READING_ROOM:
				view = seatService.seatUse();
				break; // 열람실 이용 (회원)
			case View.SEAT_INQUIRY:
				view = seatService.currSeat();
				break; // 좌석 조회 (회원)
			case View.SEAT_RESERVATION:
				view = seatService.rseSeat();
				break; // 좌석 예약 (회원) // 좌석 이용+신청
			case View.SEAT_CANCEL_RES:
				view = seatService.rseCheckout();
				break;// 좌석 취소 (회원)
			case View.MY_SEAT_RESINFO:
				view = reservationService.myResInfo();
				break; // 나의 열람실 예약 정보 조회 (회원)

			// 희망도서
			case View.REQUEST_BOOK_APPLY:
				view = RequestBookService.requestBook();
				break; // 희망도서 신청
			case View.REQUEST_BOOK_PERMISSION:
				view = RequestBookService.requestBook1();
				break; // 희망도서 신청 조회 및 승인
			case View.REQUEST_BOOK_PERMISSION02:
				view = RequestBookService.requestBookPermission();
				break;
			case View.REQUEST_BOOK_MEM:
				view = RequestBookService.requestBookMem();
				break;
			case View.REQUEST_BOOK_MAN:
				view = RequestBookService.requestBookMan();
				break;
			case View.MYPAGE_REQUESTBOOKLIST:
				view = RequestBookService.myRequestBookList();
				break; // 나의 희망도서 내역 조회
			}
		}
	}
}