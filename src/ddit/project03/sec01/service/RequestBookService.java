package ddit.project03.sec01.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ddit.project03.sec01.dao.RequestBookDAO;
import ddit.project03.sec01.util.JDBCUtil;
import ddit.project03.sec01.util.LoginUserNo;
import ddit.project03.sec01.util.ScanUtil;
import ddit.project03.sec01.util.SpaceUtil;
import ddit.project03.sec01.util.View;

public class RequestBookService {
	private static RequestBookService instance = null;

	private RequestBookService() {
	}

	public static RequestBookService getInstance() {
		if (instance == null)
			instance = new RequestBookService();
		return instance;
	}

	static RequestBookDAO requestBookDAO = RequestBookDAO.getInstance();
	static JDBCUtil jdbc = JDBCUtil.getInstance();
	String str = "";

	//추가
	public static int requestBookMem() {
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("    1. 희망도서 신청 ", 60, 0));
		System.out.println(SpaceUtil.format("    9. 이전페이지로 돌아가기  ", 69, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.print(" >> 번호 입력 : ");
		
		switch(ScanUtil.nextInt()) {
		case 1: return View.REQUEST_BOOK_APPLY;
        case 9: return View.MEM_HOME;
        default:
           System.out.println(" >> 번호를 잘못 입력하셨습니다.");
           return View.REQUEST_BOOK_MEM;
		}
	}
	// 회원용 - 희망도서 신청
	public static int requestBook() {
		System.out.println(" ───────────────────────────────────────────────────────────────────");
	   	System.out.println(SpaceUtil.format("           희망도서 신청               ",115,0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(" >> 희망도서를 신청하는 공간입니다~ ^_^");
		System.out.println(" >> 희망하는 도서, 작가 등 정보를 자유롭게 입력해주세요.");
		System.out.println(" >> (해당 게시글은 관리자만 확인할 수 있으며, 심의를 거친 후 승인 여부를 등록할 예정입니다.)");
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.print(" >> 내용 입력 : ");
		String req_con = ScanUtil.nextLine();

		System.out.println(" -------------------------------------------------------------------");
		System.out.println();
		System.out.println(" >> 등록하시겠습니까?");
		System.out.print(" >> 등록하려면 Y를, 등록하지 않으려면 N을 입력해주세요 : ");
	

		String register = ScanUtil.nextLine();
		if (register.equalsIgnoreCase("Y")) {
		    List<Object> param = new ArrayList<>();
		    String mem_no = LoginUserNo.getInstance().getLoggedInUserNo();

		    param.add(req_con);
		    param.add(mem_no);
		    int isSuccess = requestBookDAO.requestBookInsert(param);
		    if (isSuccess > 0) {
		    	System.out.println();
				System.out.println(" -------------------------------------------------------------------");
				System.out.println();
				System.out.println(" >> 희망도서 등록이 완료되었습니다!");
				System.out.println(" >> 메뉴로 다시 돌아갑니다.");
				System.out.println();
				System.out.println(" -------------------------------------------------------------------");
			} else {
				System.out.println(" >> 희망도서 등록에 실패하였습니다.");
				
			}
		} else if (register.equalsIgnoreCase("N")) {
			System.out.println(" -------------------------------------------------------------------");
			System.out.println(" >> 희망도서 등록이 취소되었습니다!");
			System.out.println(" >> 메뉴로 다시 돌아갑니다.");
			System.out.println(" -------------------------------------------------------------------");
		} else {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(" >> 잘못 입력하셨습니다.");
			System.out.println(" >> 메뉴로 다시 돌아갑니다.");
			System.out.println(" ───────────────────────────────────────────────────────────────────");
		}
		return View.MEM_HOME;
	}		

	
	public static int requestBookMan() {
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("            1. 희망도서 조회 및 승인 ", 60, 0));
		System.out.println(SpaceUtil.format("            9. 이전페이지로 돌아가기  ", 60, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 번호 입력 : ");
		
		switch(ScanUtil.nextInt()) {
		case 1: return View.REQUEST_BOOK_PERMISSION;
		case 9: return View.BOOK_MANAGER;
		default:
			System.out.println(" >> 번호를 잘못 입력하셨습니다.");
		return View.REQUEST_BOOK_MAN;
		}
	}
	
	public static int requestBook1() {
	    String req_con = "";

	    List<Map<String, Object>> requestBookList = requestBookDAO.inquiryNo02(req_con);

	    if (requestBookList.isEmpty()) {
	        System.out.println(" >> 신청 내역이 없습니다.");
	    } else {
	    	System.out.println(SpaceUtil.format(" ******* 희망도서 신청 리스트   ******* ", 120, 0));
			System.out.println(SpaceUtil.format(
					"─────────────────────────────────────────────────────────────────────────────────────────────────────────── ",
					80, 0));

			for (Map<String, Object> requestBook : requestBookList) {
				System.out.print(SpaceUtil.format("신청번호| " + requestBook.get("REQ_NO"), 30, 0));
				System.out.print(SpaceUtil.format("\t승인상태| " + requestBook.get("REQ_STATUS"), 50, -1));
				System.out.print(SpaceUtil.format("\t 내용| " + requestBook.get("REQ_CON"), 40, -1));
				System.out.println(SpaceUtil.format(
						"\n─────────────────────────────────────────────────────────────────────────────────────────────────────────── ",
						80, 0));

				System.out.println();
			}
		}
	    System.out.println(SpaceUtil.format("    1. 희망도서 승인하기 ", 75, 0));
		System.out.println(SpaceUtil.format("        9. 홈으로 돌아가기  ", 70, 0));
		System.out.println();
		System.out.print(" >> 번호 입력: ");
	    switch(ScanUtil.nextInt()) {
	    case 1:
	    	return View.REQUEST_BOOK_PERMISSION02;
	    case 2:
	    	return View.ADMIN_HOME;
	    default:
			System.out.println(" >> 번호를 잘못 입력하셨습니다.");
			return View.REQUEST_BOOK_PERMISSION;
	    }
	}
	
	// 관리자용 - 희망도서 신청 조회, 승인
	public static int requestBookPermission() {
		String req_no = "";
		String req_con = "";
		boolean result = true;

		while (true) {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(SpaceUtil.format("희망도서 신청 조회", 120, 0));
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println("조회할 게시물 번호를 입력해주세요^_^");
			System.out.println("영어는 대문자로 입력해주세요 (ex. H000001)");
			System.out.print(" >> 번호 입력 : ");
			req_no = ScanUtil.nextLine();

			Map<String, Object> selectNo = requestBookDAO.inquiryNo(req_no);
			if (selectNo == null) {

				System.out.println(" ───────────────────────────────────────────────────────────────────");
				System.out.println(" >> 데이터가 없거나 잘못 입력하셨습니다.");
				result = false;
				System.out.println(" >> 이전 메뉴로 돌아갑니다.");
				System.out.println(" ───────────────────────────────────────────────────────────────────");
				return View.REQUEST_BOOK_MAN;// 관리자 메뉴로 다시 돌아가게

				

			} else {

				System.out.println(" ───────────────────────────────────────────────────────────────────");				System.out.println(req_no + "의 조회 내용입니다.");
				System.out.println("학생 번호 : " + selectNo.get("MEM_NO"));
				System.out.println("작성 시간 : " + selectNo.get("REQ_DATE"));
				System.out.println("작성 내용 : " + selectNo.get("REQ_CON"));
				result = true;

				while (true) {
					String req_status = "";
					System.out.println(" ───────────────────────────────────────────────────────────────────");					System.out.println("승인하시겠습니까? (승인 : 승인함, 반려 : 승인 안함)");
					System.out.print(" >> 입력 : ");
					req_status = ScanUtil.nextLine();

					if (req_status.equals("승인")) {
						int result1 = requestBookDAO.updateState(req_status, req_no);
						if (result1 > 0) {
							System.out.println(" >> 승인이 완료되었습니다.");
							System.out.println(" >> 이전 메뉴로 돌아갑니다.");
							System.out.println(" ───────────────────────────────────────────────────────────────────");							return View.REQUEST_BOOK_MAN;
						}
					} else if (req_status.equals("반려")) {
						int result2 = requestBookDAO.updateState(req_status, req_no);
						if (result2 > 0) {
							System.out.println(" >> 승인이 반려되었습니다.");
							System.out.println(" >> 이전 메뉴로 돌아갑니다.");
							System.out.println(" ───────────────────────────────────────────────────────────────────");							
							return View.REQUEST_BOOK_MAN;
						}
					} else {
						System.out.println(" >> 잘못 입력하셨습니다.");
						System.out.println(" >> 이전 메뉴로 돌아갑니다.");
						System.out.println(" ───────────────────────────────────────────────────────────────────");	
						return View.REQUEST_BOOK_MAN;

					}

				}

			}

		}
	}
	
	
//	str = " 해당 정보로 조회된 아이디는 [" + selectMem.get("MEM_ID") + "] 입니다.";
//    str = SpaceUtil.format(str, 53, 0);
//   System.out.println(str);

	public static int myRequestBookList() {
		   String reqCon= "";
	   List<Map<String,Object>> myRequestBookList = requestBookDAO.myRequestBookList(reqCon);
	   
	   if(myRequestBookList.isEmpty()) {
		   System.out.println(" >> 희망도서 신청 내역이 없습니다.");
	   } else {
		   System.out.println();

		   System.out.println(SpaceUtil.format(" ******* 나의 희망도서 신청 내역   ******* ", 100, 0));
			System.out.println(SpaceUtil.format(" ───────────────────────────────────────────────────────────────────────────────────────────── ",80, 0));


		    for(Map<String,Object> myRequestBook: myRequestBookList) {
		    	System.out.print(SpaceUtil.format(" 신청번호| " + myRequestBook.get("REQ_NO"), 40, 0));
				System.out.print(SpaceUtil.format(" 승인상태| " + myRequestBook.get("REQ_STATUS"), 40, 0));
				System.out.print(SpaceUtil.format("\t 내용| " + myRequestBook.get("REQ_CON"), 40, 0));
				System.out.print(SpaceUtil.format("\n ───────────────────────────────────────────────────────────────────────────────────────────── ",80, 0));
				System.out.println();
		    }
	   }
	   System.out.println();
	   System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
       return View.MYPAGE;
  }
	}

