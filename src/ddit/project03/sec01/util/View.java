package ddit.project03.sec01.util;

public interface View {

   
   // 홈화면 
   int HOME = 1;   // 로그인 창 홈화면
   int MEM_HOME = 101;   // 로그인 후 회원 홈화면
   int ADMIN_HOME = 102; // 로그인 후 관리자 홈화면
   int HOME_END = 103; // 홈메뉴 종료
   
   // 로그인 
   int MEMBER = 2;
   int MEMBER_LOGIN = 201; // 로그인
   int MEMBER_LOGOUT= 202; // 로그아웃
   int MEMBER_FIND_PW= 203; // 패스워드 찾기
   int MEMBER_DELETE= 204; // 회원 삭제
   int MEMBER_INSERT= 205; // 회원 추가


   // 도서
   int BOOK = 3;
   int BOOK_MANAGER = 301; // 도서관리
   int BOOK_OVERDUE_MANAGE = 302; // 대출연체관리
   int BOOK_SEARCH = 303; // 도서조회
   int BOOK_INSERT = 304; // 도서등록
   int BOOK_UPDATE = 305; // 도서수정
   int BOOK_DELETE = 306; // 도서삭제
   int BOOK_BORROW = 307; // 도서대출
   int BOOK_BORROW_LIST_S = 308; // 나의 대출 리스트 조회(회원용)
   int BOOK_BORROW_LIST_M = 309; // 대출리스트 조회(관리자용)
   int BOOK_OVERDUE_LIST = 310; // 연체리스트 조회
   int REQUEST_BOOK_APPLY = 311; // 희망도서신청(회원)
   int REQUEST_BOOK_PERMISSION = 312; // 희망도서승인(관리자)
   int BOOK_UPDATE_BOOKNO = 313;
   int BOOK_UPDATE_BOOKNAME = 314;
   int BOOK_UPDATE_WRITER = 315;
   int BOOK_UPDATE_COM = 316;
   int BOOK_UPDATE_YEAR = 317;
   int BOOK_UPDATE_BORRSTATUS = 318;
   int BOOK_UPDATE_CLACODE = 319;
   int BOOK_SEARCH_N=320;
   int BOOK_SEARCH_W=321;
   int BOOK_RETURN = 322;
   int BOOK_BORROW_MANAGER = 323;
   int BOOK_BORROW_M = 324; // 도서대출(추가)
   int BOOK_RETURN_M = 325; // 도서반납 (추가)
   int BOOK_BORROW_LIST_BORRNO= 326; // 대출리스트- 대출번호로 조회
   int BOOK_BORROW_LIST_BORRDATE= 327; // 대출일로 조회
   int BOOK_BORROW_LIST_BROOSTATUS=328; // 반납상태로 조회
   int BOOK_BORROW_LIST_MEMNO=329; //회원번호로 조회
   int BOOK_BORROW_LIST_BOOKNO=330; // 도서번호로 조회
   
   int REQUEST_BOOK_MEM=331;
   int REQUEST_BOOK_MAN=332;
   int REQUEST_BOOK_PERMISSION02= 333; // 희망도서 추가

   
   //열람실, 좌석
    int READING_ROOM = 4; //열람실
    int SEAT_INQUIRY = 401; //좌석 조회
    int SEAT_RESERVATION = 402; //좌석 예약
    int SEAT_CANCEL_RES = 403; //좌석 예약 취소
    int MY_SEAT_RESINFO = 404; //나의 좌석 예약 정보
    int SEAT_RES_LIST = 405; //좌석 예약 리스트(관리자용)   
    int READIND_ROOM_MANAGER = 406; //열람실관리
    int ADM_SEAT_INQUIRY = 407; //관리자용 좌석 조회
   
    //마이페이지
    int MYPAGE = 5; // 마이페이지
    int MYPAGE_UPDATE =501;  // 개인정보 수정
    int MEMBER_UPDATE_PWD= 502; // 회원 비밀번호 변경
    int MEMBER_UPDATE_TEL= 503; // 회원 전화번호 변경
    int MYPAGE_REQUESTBOOKLIST= 504; // 나의 희망도서 신청 내역 조회
    
    
    //학생관리
    int ADMPAGE= 6; //학생관리
   
}