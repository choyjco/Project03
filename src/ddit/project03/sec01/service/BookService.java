package ddit.project03.sec01.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ddit.project03.sec01.dao.BookDao;
import ddit.project03.sec01.util.ScanUtil;
import ddit.project03.sec01.util.SpaceUtil;
import ddit.project03.sec01.util.View;
import ddit.project03.sec01.util.LoginUserNo;

public class BookService {
	public static BookService instance = null;

	private BookService() {
	}

	public static BookService getInstance() {
		if (instance == null)
			instance = new BookService();
		return instance;
	}

	static BookDao bkVO = BookDao.getInstance();
	static String str = "";

	public static int bookmanager() {
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("           DDIT  도서관    <관리자용>             ", 70, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("1. 도서 조회", 60, 0));
		System.out.println(SpaceUtil.format("2. 도서 등록", 60, 0));
		System.out.println(SpaceUtil.format("3. 도서 수정", 60, 0));
		System.out.println(SpaceUtil.format("4. 도서 삭제", 60, 0));
		System.out.println(SpaceUtil.format("5. 희망도서 승인", 65, 0));
		System.out.println(SpaceUtil.format("9. 홈으로", 57, 0));
		System.out.println();
		System.out.print(" >> 번호 입력 : ");

		switch (ScanUtil.nextInt()) {
		case 1:
			return View.BOOK_SEARCH;
		case 2:
			return View.BOOK_INSERT;
		case 3:
			return View.BOOK_UPDATE;
		case 4:
			return View.BOOK_DELETE;
		case 5:
			return View.REQUEST_BOOK_MAN;
		case 9:
			return View.ADMIN_HOME;
		default:
			System.out.println("번호를 잘못 입력하셨습니다.");
			return View.BOOK_MANAGER;
		}
	}

	public static int AddNewBook() {
//		if(Controller.isMember()) return View.MEM_HOME;
		String bookNo = "";
		String bookName = "";
		String writer = "";
		String com = "";
		int year = 0;
		String borrstatus = "";
		int clacode = 0;
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("도서 등록", 125, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println();
		while (true) {
			System.out.println(" * 도서번호 입력 [ 도서분류코드 필수 ]");
			System.out.print(" >> 번호 입력 : ");
			bookNo = ScanUtil.nextLine();
			System.out.println();
			if (normalizationBookNo(bookNo))
				break;
		}
		while (true) {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(" * 도서명  입력");
			System.out.print(" >> 도서명 입력 : ");
			bookName = ScanUtil.nextLine();
			System.out.println();
			if (normalizationBookName(bookName))
				break;
		}
		while (true) {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(" * 저자 입력 [ 한글ㆍ영문 입력 ]");
			System.out.print(" >> 저자 입력 : ");
			writer = ScanUtil.nextLine();
			System.out.println();
			if (normalizationWriter(writer))
				break;
		}
		while (true) {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(" * 출판사 입력 [ 한글ㆍ영문 입력 ]");
			System.out.print(" >> 출판사 입력 : ");
			com = ScanUtil.nextLine();
			System.out.println();
			if (normalizationCom(com))
				break;
		}
		while (true) {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(" * 출판년도 입력 [ ex) 2023 ]");
			System.out.print(" >> 출판년도 입력 : ");
			year = ScanUtil.nextInt();
			System.out.println();
			break;
		}
		while (true) {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(" * 대출가능여부 입력 [ ex) Y/N ]");
			System.out.print(" >> 대출가능여부 입력 : ");
			borrstatus = ScanUtil.nextLine();
			System.out.println();
			if (normalizationBorrStatus(borrstatus))
				break;
		}
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(" * 도서분류코드 입력 ");
		System.out.print(" >> 도서분류코드 입력 : ");
		clacode = ScanUtil.nextInt();
		System.out.println();
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(" * 입력하신 정보로 등록 하시겠습니까? (Y/N)");
		System.out.print(" >> 입력 : ");
		if (ScanUtil.nextLine().equalsIgnoreCase("y")) {
			Map<String, Object> result = BookDao.isOverapNO(bookNo);
			if (result != null) {
				System.out.println();
				System.out.println("이미 등록된 번호 입니다!");
			} else {
				List<Object> param = new ArrayList<>();
				param.add(bookNo);
				param.add(bookName);
				param.add(writer);
				param.add(com);
				param.add(year);
				param.add(borrstatus);
				param.add(clacode);
				int isSuccess = BookDao.AddNewBook(param);
				if (isSuccess > 0) {
					str = "[" + bookName + "] 도서가 정상적으로 등록되었습니다!";
					System.out.println(str);
					System.out.println();
					return View.BOOK_MANAGER;
				} else
					System.out.println("도서 등록에 실패하였습니다!");
				return View.BOOK_MANAGER;
			}
		} else
			System.out.println(" * 도서등록을 취소합니다.");
		System.out.println(" 확인 >>  enter");
		ScanUtil.nextLine();
		return View.BOOK_MANAGER;
	}

	private static boolean normalizationBookNo(String bookNo) {
		boolean result = true;
		if (!bookNo.matches("^[0-9]*$")) {
			System.out.println(" * 숫자만 입력가능합니다.");
			result = false;
		} else if (!bookNo.matches("^[0-9a-zA-Z가-힣]*$")) {
			System.out.println(" * 올바른 도서번호 형식이 아닙니다.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" * 다시 입력하십시오.");
		System.out.println();
		return result;
	}

	private static boolean normalizationBookName(String bookName) {
		boolean result = true;
//			if (!bookName.matches("^[a-zA-Z가-힣]*$")) {
//				System.out.println(" * 문자만 입력가능합니다.");
//				result = false;
//			} else 
		if (bookName.length() < 2) {
			System.out.println(" * 올바른 입력이 아닙니다.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" * 다시 입력하세요.");
		System.out.println();
		return result;
	}

	private static boolean normalizationWriter(String writer) {
		boolean result = true;
		if (!writer.matches("^[a-zA-Z가-힣]*$")) {
			System.out.println(" * 문자만 입력가능합니다.");
			result = false;
		} else if (writer.length() < 2) {
			System.out.println(" * 올바른 입력이 아닙니다.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" * 다시 입력하세요.");
		System.out.println();
		return result;
	}

	private static boolean normalizationCom(String com) {
		boolean result = true;
		if (!com.matches("^[a-zA-Z가-힣]*$")) {
			System.out.println(" * 문자만 입력가능합니다.");
			result = false;
		} else if (com.length() < 2) {
			System.out.println(" * 올바른 입력이 아닙니다.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" * 다시 입력하세요.");
		System.out.println();
		return result;
	}

	private static boolean normalizationYear(String year) {
		boolean result = true;
		if (!(year.matches("^[0-9]*")) || (year.matches("^[가-힣]*$")) || (year.matches("^[a-zA-Z]*$"))) {
			System.out.println(" * 숫자만 입력가능합니다.");
			result = false;
		} else if (!year.matches("[0-9]{4}")) {
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" * 다시 입력하십시오.");
		System.out.println();
		return result;
	}

	private static boolean normalizationBorrStatus(String borrstatus) {
		boolean result = true;
		if (!borrstatus.matches("^[YN]*$")) {
			System.out.println(" * 문자 Y/N만 입력가능합니다.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" * 다시 입력하세요.");
		System.out.println();
		return result;
	}

	private boolean normalizationClaCode(String clacode) {
		boolean result = true;
		if (!(clacode.matches("^[0-9]*")) || (clacode.matches("^[가-힣]*$")) || (clacode.matches("^[a-zA-Z]*$"))) {
			System.out.println(" * 숫자만 입력가능합니다.");
			result = false;
		} else if (!clacode.matches("100,200,300,400,500,600,700,800,900")) {
			System.out.println(" * 올바른 출판년도 형식이 아닙니다.");
			result = false;
		} else
			return result;
		System.out.println();
		System.out.println(" * 다시 입력하십시오.");
		System.out.println();
		return result;
	}

	public static int delete() {
		boolean result = false;
		System.out.println();
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("도서 삭제", 125, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 도서 번호 입력 : ");
		String bookNo = ScanUtil.nextLine();

		List<Map<String, Object>> list = bkVO.bookList(bookNo);
		if (list.isEmpty()) {
			System.out.println("* 존재하지 않는 도서번호입니다.");
			System.out.println("도서번호 확인 후 다시 시도해주세요.");
			ScanUtil.nextLine();
			result = false;
			return View.BOOK_MANAGER;
		} else {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(SpaceUtil.format("도서 정보", 125, 0));
			System.out.println(" ───────────────────────────────────────────────────────────────────");
	
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				System.out.println("도서번호: " + map.get("BOOK_NO"));
				System.out.println("도서명: " + map.get("BOOK_NAME"));
				System.out.println("저자: " + map.get("BOOK_WRITER"));
				System.out.println("출판사: " + map.get("BOOK_COM"));
				System.out.println("출판년도: " + map.get("BOOK_YEAR"));
				System.out.println("대출가능여부: " + map.get("BORR_STATUS"));
				System.out.println("도서분류코드: " + map.get("CLA_CODE"));
				result = true;
			}

			System.out.println();
			System.out.println(" * 정말 삭제하시겠습니까? (Y/N)");
			System.out.print(" >> 입력 : ");
			String userInput = ScanUtil.nextLine();

			if (userInput.equalsIgnoreCase("y")) {
				int result2 = bkVO.delete(bookNo);

				if (result2 > 0) {
					System.out.println(" * 삭제 되었습니다.");
					System.out.println();
					return View.BOOK_MANAGER;
				} else {
					System.out.println(" * 도서 삭제 실패");
					System.out.println(" * 이전 메뉴로 돌아갑니다.");
					return View.BOOK_MANAGER;
				}
			} else {
				System.out.println(" * 도서 삭제 작업을 취소합니다.");
				System.out.println(" * 이전 메뉴로 돌아갑니다.");
				return View.BOOK_MANAGER;
			}
		}
	}

	public static int bookInfoUpdate() {
		boolean result = false;
		// if (Controller.isMember()) return View.MEM_HOME;

		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("도서 정보 변경", 120, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 도서 번호 입력 : ");
		String bookNo = ScanUtil.nextLine();

		List<Map<String, Object>> list = bkVO.bookList(bookNo);
		if (list.isEmpty()) {
			System.out.println("* 존재하지 않는 도서번호입니다.");
			System.out.println("도서번호 확인 후 다시 시도해주세요.");
			System.out.println(" >> 확인 : enter");
			ScanUtil.nextLine();
			result = false;
			return View.BOOK_MANAGER;
		} else {
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(SpaceUtil.format("도서 정보", 125, 0));
			System.out.println(" ───────────────────────────────────────────────────────────────────");

			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				System.out.println("도서번호: " + map.get("BOOK_NO"));
				System.out.println("도서명: " + map.get("BOOK_NAME"));
				System.out.println("저자: " + map.get("BOOK_WRITER"));
				System.out.println("출판사: " + map.get("BOOK_COM"));
				System.out.println("출판년도: " + map.get("BOOK_YEAR"));
				System.out.println("대출가능여부: " + map.get("BORR_STATUS"));
				System.out.println("도서분류코드: " + map.get("CLA_CODE"));

				result = true;
			}

			System.out.println();
			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println("  1.도서번호 2.도서명 3.저자 4.출판사 5.출판년도 6.대출가능여부 7.도서분류코드 0.돌아가기");
			System.out.print(" >> 번호 입력 : ");

			switch (ScanUtil.nextInt()) {
			case 1:
				return View.BOOK_UPDATE_BOOKNO;
			case 2:
				return View.BOOK_UPDATE_BOOKNAME;
			case 3:
				return View.BOOK_UPDATE_WRITER;
			case 4:
				return View.BOOK_UPDATE_COM;
			case 5:
				return View.BOOK_UPDATE_YEAR;
			case 6:
				return View.BOOK_UPDATE_BORRSTATUS;
			case 7:
				return View.BOOK_UPDATE_CLACODE;
			case 0:
				return View.BOOK_MANAGER;
			default:
				System.out.println(" * 잘못된 접근입니다.");
				return View.BOOK_UPDATE;
			}
		}
	}

	public static int modifyBookNo() {
		System.out.println(" * 새로운 도서번호를 입력해주세요.");
		System.out.print(" >> 새로운 도서번호 입력 : ");
		String inputbookNo = ScanUtil.nextLine();
		System.out.println(" * 비꾸기전 도서번호를 다시 입력해주세요.");
		System.out.print(" >> 바꾸기전 도서번호 입력 : ");
		String bookNo = ScanUtil.nextLine();
		int list = bkVO.updateBookNo(inputbookNo, bookNo);

		if (list > 0) {
			System.out.println(" * 도서번호 변경이 완료됐습니다.");
		} else {
			System.out.println(" >> 변경 실패 ");
		}
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
		return View.BOOK_MANAGER;
	}

	public static int modifyBookName() {
		System.out.println(" * 새로운 도서명을 입력해주세요.");
		System.out.print(" >> 도서명 입력 : ");
		String inputbookName = ScanUtil.nextLine();
		System.out.println(" * 도서번호를 다시 입력해주세요.");
		System.out.print(" >> 도서번호 입력 : ");
		String bookNo = ScanUtil.nextLine();
		int list = bkVO.updateBookName(inputbookName, bookNo);

		if (list > 0) {
			System.out.println(" * 도서명 변경이 완료됐습니다.");
		} else {
			System.out.println(" >> 변경 실패 ");
		}
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
		return View.BOOK_MANAGER;
	}

	public static int modifyWriter() {
		System.out.println(" * 새로운 저자를 입력해주세요.");
		System.out.print(" >> 저자 입력 : ");
		String inputWriter = ScanUtil.nextLine();
		System.out.println(" * 도서번호를 다시 입력해주세요.");
		System.out.print(" >> 도서번호 입력 : ");
		String bookNo = ScanUtil.nextLine();
		int list = bkVO.updateWriter(inputWriter, bookNo);

		if (list > 0) {
			System.out.println(" * 저자 변경이 완료됐습니다.");
		} else {
			System.out.println(" >> 변경 실패 ");
		}
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
		return View.BOOK_MANAGER;
	}

	public static int modifyCom() {
		System.out.println(" * 새로운 출판사를 입력해주세요.");
		System.out.print(" >> 출판사 입력 : ");
		String inputcom = ScanUtil.nextLine();
		System.out.println(" * 도서번호를 다시 입력해주세요.");
		System.out.print(" >> 도서번호 입력 : ");
		String bookNo = ScanUtil.nextLine();
		int list = bkVO.updateCom(inputcom, bookNo);

		if (list > 0) {
			System.out.println(" * 출판사 변경이 완료됐습니다.");
		} else {
			System.out.println(" >> 변경 실패 ");
		}
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
		return View.BOOK_MANAGER;
	}

	public static int modifyYear() {
		System.out.println(" * 새로운 출판년도를 입력해주세요.");
		System.out.print(" >> 출판년도 입력 : ");
		int inputYear = ScanUtil.nextInt();
		System.out.println(" * 도서번호를 다시 입력해주세요.");
		System.out.print(" >> 도서번호 입력 : ");
		String bookNo = ScanUtil.nextLine();
		int list = bkVO.updateYear(inputYear, bookNo);

		if (list > 0) {
			System.out.println(" * 출판년도 변경이 완료됐습니다.");
		} else {
			System.out.println(" >> 변경 실패 ");
		}
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
		return View.BOOK_MANAGER;
	}

	public static int modifyBorrStatus() {
		System.out.println(" * 새로운 대출가능여부를 입력해주세요.");
		System.out.print(" >> 대출가능여부(Y/N) : ");
		String inputBorrStatus = ScanUtil.nextLine();
		System.out.println(" * 도서번호를 다시 입력해주세요.");
		System.out.print(" >> 도서번호 입력 : ");
		String bookNo = ScanUtil.nextLine();
		int list = bkVO.updateBorrStatus(inputBorrStatus, bookNo);

		if (list > 0) {
			System.out.println(" * 대출가능여부 변경이 완료됐습니다.");
		} else {
			System.out.println(" >> 변경 실패 ");
		}
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
		return View.BOOK_MANAGER;
	}

	public static int modifyClaCode() {
		System.out.println(" * 새로운 도서분류코드를 입력해주세요.");
		System.out.print(" >> 도서분류코드 입력 : ");
		int inputClaCode = ScanUtil.nextInt();
		System.out.println(" * 도서번호를 다시 입력해주세요.");
		System.out.print(" >> 도서번호 입력 : ");
		String bookNo = ScanUtil.nextLine();
		int list = bkVO.updateClaCode(inputClaCode, bookNo);

		if (list > 0) {
			System.out.println(" * 도서분류코드 변경이 완료됐습니다.");
		} else {
			System.out.println(" >> 변경 실패 ");
		}
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
		return View.BOOK_MANAGER;
	}

	public static int findBook() {
		boolean result = true;

		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("도서 검색", 125, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.println(SpaceUtil.format("1. 도서명으로 찾기  ",66,0));
		System.out.println(SpaceUtil.format("2. 저자로 찾기",60,0));
		System.out.println(SpaceUtil.format("9. 이전메뉴로 돌아가기",68,0));
		System.out.println();
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 번호 입력 : ");
		switch (ScanUtil.nextInt()) {
		case 1:
			return View.BOOK_SEARCH_N;
		case 2:
			return View.BOOK_SEARCH_W;
		case 9:
			LoginUserNo loginUser = LoginUserNo.getInstance();
			String userNo = loginUser.getLoggedInUserNo();
			// System.out.println(userNo);
			if (userNo.equals("MANAGER")) {
				return View.BOOK_MANAGER;
			} else {
				return View.MEM_HOME;
			}
		default:
			System.out.println(" * 잘못된 접근입니다.");
			return View.BOOK_SEARCH;
		}
	}

	public static int findBookName() {
		boolean result = true;
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("도서명으로 검색하기", 120, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(" * 도서명을 입력하세요\n");
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.print(" >> 도서명 입력 : ");
		String bookName = ScanUtil.nextLine();

		List<Map<String, Object>> list = bkVO.findBookName(bookName);

		if (list != null) {

			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(SpaceUtil.format("도서 정보", 125, 0));
			System.out.println(" ───────────────────────────────────────────────────────────────────");
		
			for (Map<String, Object> map : list) {
				System.out.println("도서번호: " + map.get("BOOK_NO"));
				System.out.println("도서명: " + map.get("BOOK_NAME"));
				System.out.println("저자: " + map.get("BOOK_WRITER"));
				System.out.println("출판사: " + map.get("BOOK_COM"));
				System.out.println("출판년도: " + map.get("BOOK_YEAR"));
				System.out.println("대출가능여부: " + map.get("BORR_STATUS"));
				System.out.println();
				System.out.println(" ───────────────────────────────────────────────────────────────────");
				result = true;
			}
		} else {
			System.out.println("* 도서명을 확인 하십시오.");
			System.out.print(" >> 다시 입력 : enter");
			ScanUtil.nextLine();
			result = false;

		}
		return View.BOOK_SEARCH;

	}

	public static int findBookWriter() {
		boolean result = true;
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(SpaceUtil.format("저자로 검색하기", 120, 0));
		System.out.println(" ───────────────────────────────────────────────────────────────────");
		System.out.println(" * 저자를 입력하세요\n");
		System.out.println(" ───────────────────────────────────────────────────");
		System.out.print(" >> 저자 입력 : ");
		String bookWriter = ScanUtil.nextLine();

		List<Map<String, Object>> list = bkVO.findBookWriter(bookWriter);

		if (list != null) {

			System.out.println(" ───────────────────────────────────────────────────────────────────");
			System.out.println(SpaceUtil.format("도서 정보", 125, 0));
			System.out.println(" ───────────────────────────────────────────────────────────────────");
		

			for (Map<String, Object> map : list) {
				System.out.println("도서번호: " + map.get("BOOK_NO"));
				System.out.println("도서명: " + map.get("BOOK_NAME"));
				System.out.println("저자: " + map.get("BOOK_WRITER"));
				System.out.println("출판사: " + map.get("BOOK_COM"));
				System.out.println("출판년도: " + map.get("BOOK_YEAR"));
				System.out.println("대출가능여부: " + map.get("BORR_STATUS"));
				System.out.println();
				System.out.println(" ───────────────────────────────────────────────────────────────────");
				result = true;
			}
		} else {
			System.out.println("* 저자를 확인 하십시오.");
			System.out.print(" >> 다시 입력 : enter");
			ScanUtil.nextLine();
			result = false;

		}
		return View.BOOK_SEARCH;

	}
}
