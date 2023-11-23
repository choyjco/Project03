package ddit.project03.sec01.service;

import ddit.project03.sec01.dao.BorrowBookDao;
import ddit.project03.sec01.util.LoginUserNo;
import ddit.project03.sec01.util.ScanUtil;
import ddit.project03.sec01.util.SpaceUtil;
import ddit.project03.sec01.util.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BorrowBookService {
	public static BorrowBookService instance = null;

	private BorrowBookService() {
	}

	public static BorrowBookService getInstance() {
		if (instance == null)
			instance = new BorrowBookService();
		return instance;
	}

	static BorrowBookDao bbDAO = BorrowBookDao.getInstance();
	static String str = "";

	public static int bookbrrowmanager() {
	    System.out.println();
	      System.out.println(" ───────────────────────────────────────────────────────────────────");
	      System.out.println(SpaceUtil.format("도서 대여",110,0));
	      System.out.println(" ───────────────────────────────────────────────────────────────────\n");
	      System.out.println(SpaceUtil.format("1. 도서 대여하기  ",68,0));
	      System.out.println(SpaceUtil.format(" 2. 홈으로  ",60,0));
		System.out.println();
		System.out.print(" >> 번호 입력 : ");

		switch (ScanUtil.nextInt()) {
		case 1:
			return View.BOOK_BORROW_M;
		case 2:
			return View.MEM_HOME;
		default:
			System.out.println(" >> 번호를 잘못 입력하셨습니다.");
			return View.BOOK_BORROW;
		}
	}

	 public static int borrowBook() {
	        String bookNo = "";


	        while (true) {
	            System.out.println();
	              System.out.println(" ----------  대출할 도서번호 입력  ----------- ");
	              System.out.print(" >> ");
	              bookNo = ScanUtil.nextLine();
	              System.out.println();
	        break;
	        
	        }
	        List<Map<String, Object>> possibleno = bbDAO.possibleNo(bookNo);
	        if(possibleno.isEmpty()) {
	             System.out.println();
	             System.out.println(" * 이미 대출 중이거나 없는 도서번호입니다.");
	             System.out.println(" * 도서번호  후 다시 시도해주세요.");
	        } else {
	        	  System.out.println(SpaceUtil.format("************************     도서정보     *****************************",70,0));

	             for (int i = 0; i < possibleno.size(); i++) {
	                Map<String, Object> ps = possibleno.get(i);
	                System.out.println(" 도서번호: " + ps.get("BOOK_NO"));
	                System.out.println(" 도서명: " + ps.get("BOOK_NAME"));
	                System.out.println(" 저자: " + ps.get("BOOK_WRITER"));
	                System.out.println(" 출판사: " + ps.get("BOOK_COM"));
	                System.out.println(" 출판년도: " + ps.get("BOOK_YEAR"));
	                System.out.println(" 대출가능여부: " + ps.get("BORR_STATUS"));
	             }
	             
	             System.out.println();
	             System.out.println(" ───────────────────────────────────────────────────");
	             System.out.println(" >> 조회된 도서를 대출하시겠습니까? (Y/N)");
	             System.out.print(" >> ");
	             if (ScanUtil.nextLine().equalsIgnoreCase("y")) {
	                //bookNo : 1000018 -> list2 : 1(빌렸으니까 N이 됨)
	                int list2 = bbDAO.prepareStatementToN(bookNo);
	                   List<Object> param = new ArrayList<>();
	                   String memNo = LoginUserNo.getInstance().getLoggedInUserNo();
	                   param.add(memNo);
	                   param.add(bookNo);
	                int list3 = bbDAO.borrowBook(param);
	            	System.out.println(SpaceUtil.format("*************************     도서 대출 정보     ****************************",70,0));	      
	                   //BORROW[도서 대출 정보]
	                   //BORR_NO, BORR_DATE, RET_DUE_DATE, RET_DATE, MEM_NO, BOOK_NO
	                   
	                Map<String,Object> lastList = bbDAO.lastStatusList();
	                   System.out.println(" 대출번호: " + lastList.get("BORR_NO"));
	                   System.out.println(" 대출일: " + lastList.get("BORR_DATE"));
	                   System.out.println(" 반납예정일: " + lastList.get("RET_DUE_DATE"));
	                   System.out.println(" 반납상태: " + lastList.get("BORR_STATUS"));
	                   System.out.println(" 도서번호: " + lastList.get("BOOK_NO"));
	                
	                   str = "!!!!!대출이 완료 되었습니다!!!!!";
	                   str = SpaceUtil.format(str, 53, 0);
	                   System.out.println(str);
	                   System.out.println();
	                   return View.MEM_HOME;
	        } else
	           System.out.println(" * 도서 대출 작업을 취소합니다.");
	             System.out.println(" * 이전 화면으로 돌아갑니다.");
	        	   System.out.print(" >> 확인 : enter");
	          ScanUtil.nextLine();
	          return View.BOOK_BORROW;
	        }
	         return View.BOOK_BORROW;
	        }

	public static int returnBookmanager() {
		System.out.println();
		 System.out.println(" ───────────────────────────────────────────────────────────────────");
	      System.out.println(SpaceUtil.format("도서 반납",110,0));
	      System.out.println(" ───────────────────────────────────────────────────────────────────\n");
	      System.out.println(SpaceUtil.format("1. 도서 반납하기  ",68,0));
	      System.out.println(SpaceUtil.format(" 2. 홈으로  ",60,0));
		System.out.println();
		System.out.print(" >> 번호 입력 : ");

		switch (ScanUtil.nextInt()) {
		case 1:
			return View.BOOK_RETURN_M;
		case 2:
			return View.MEM_HOME;
		default:
			System.out.println(" >> 번호를 잘못 입력하셨습니다.");
			return View.BOOK_RETURN;
		}
	}

	 public static int returnBook() {
		    String bookNo;
		    while(true) {
		      System.out.println();
		      System.out.println(" ----------  반납할 도서 정보 조회  ----------- ");
		      System.out.print(" 도서 번호 >> ");
		      bookNo = ScanUtil.nextLine();
		      System.out.println();
		    
		    List<Map<String, Object>> borrowtoreturn = bbDAO.possibleNoY(bookNo);
		    
		    if(borrowtoreturn.isEmpty()) {
		        System.out.println("* 이미 반납상태이거나 존재하지 않는 도서번호입니다.");
		        System.out.println("도서번호 확인 후 다시 시도해주세요.");
		       return View.BOOK_RETURN;
		    } else {

	            System.out.println(SpaceUtil.format("***************************    도서정보     *****************************",70,0));		
		  
		         for (int i = 0; i < borrowtoreturn.size(); i++) {
		            Map<String, Object> map2 = borrowtoreturn.get(i);
		            System.out.println(" 도서번호: " + map2.get("BOOK_NO"));
		            System.out.println(" 도서명: " + map2.get("BOOK_NAME"));
		            System.out.println(" 저자: " + map2.get("BOOK_WRITER"));
		            System.out.println(" 출판사: " + map2.get("BOOK_COM"));
		            System.out.println(" 출판년도: " + map2.get("BOOK_YEAR"));
		            System.out.println(" 대출가능여부: " + map2.get("BORR_STATUS"));
		         }
		         
		         System.out.println();
		         System.out.println(" ───────────────────────────────────────────────────");
		         System.out.println(" * 조회된 도서를 반납하시겠습니까? (Y/N)");
		         System.out.print(" >> ");
		         
		      if (ScanUtil.nextLine().equalsIgnoreCase("y")) {
		      int returnlist1 = bbDAO.prepareStatementToY(bookNo);
		      List<Object> param = new ArrayList<>();
		      param.add(bookNo);
		      int returnlist2 = bbDAO.returnBook(param);
		      System.out.println(SpaceUtil.format("************************    도서 반납 정보  *****************************",70,0));
		      
		   Map<String,Object> lastList = bbDAO.lastStatusList();
		      System.out.println(" 대출번호: " + lastList.get("BORR_NO"));
		      System.out.println(" 대출일: " + lastList.get("BORR_DATE"));
		      System.out.println(" 실반납일: " + lastList.get("RET_DATE"));
		      System.out.println(" 반납상태: " + lastList.get("BORR_STATUS"));
		      System.out.println(" 도서번호: " + lastList.get("BOOK_NO"));
		      System.out.println();
		      System.out.println(" * 반납 처리 완료됐습니다");
		      System.out.print(" * 홈 화면으로 돌아갑니다");
		      System.out.println(" >> 확인: enter ");
		      ScanUtil.nextLine();
		        return View.MEM_HOME;
		      } else {
		       System.out.println(" * 도서 반납 작업을 취소합니다.");
		        System.out.println(" * 이전 화면으로 돌아갑니다.");
		        System.out.println(" >> 확인 : enter");
		      ScanUtil.nextLine();
		      }
		      return View.BOOK_RETURN;
		      }
		    }
		    }
	


//  MYPAGE: 마이 페이지의>>
//  BOOK_BORROW_LIST_S: 나의 대출 조회
  public static int borrowListS() {
	 System.out.println();
		System.out.println(" ───────────────────────────────────────────────────────────────────");
	      System.out.println(SpaceUtil.format("나의 도서 대출 내역",110,0));
	      System.out.println(" ───────────────────────────────────────────────────────────────────\n");
     System.out.println();
     String memNo = LoginUserNo.getInstance().getLoggedInUserNo();
     System.out.println();

     List<Map<String, Object>> borrowlists = bbDAO.borrowListS(memNo);
     
     if(!borrowlists.isEmpty()) {
        for (Map<String, Object> slist : borrowlists) {
     System.out.println(" 회원이름: "+ slist.get("MEM_NAME"));
       System.out.println(" 대여번호: "+ slist.get("BORR_NO"));
       System.out.println(" 도서명: " + slist.get("BOOK_NAME"));
       System.out.println(" 도서번호: " + slist.get("BOOK_NO"));
       System.out.println(" 대여일: " + slist.get("BORR_DATE"));
       System.out.println(" 반납예정일: " + slist.get("RET_DUE_DATE"));   
       System.out.println(" 실반납일: "+ slist.get("RET_DATE"));
       System.out.println(" 반납상태: "+ slist.get("BORR_STATUS"));
       System.out.println();
       System.out.println(" -------------------------------------------------------------------");
       System.out.println();
       }
     } else {
       System.out.println("* 대출 내역이 존재하지 않습니다. ");
  	   System.out.print(" >> 확인 : enter");
       ScanUtil.nextLine();
     }
       return View.MYPAGE;
  }
	//ADMIN_HOME: 관리자용 DDIT 도서관 페이지의>>
		//BOOK_OVERDUE_MANAGE: 대출/연체 관리
	public static int bookBorrowOverListmanager() {
		System.out.println();
		System.out.println(" ───────────────────────────────────────────────────────────────────");
	      System.out.println(SpaceUtil.format("대출/연체 관리",110,0));
	      System.out.println(" ───────────────────────────────────────────────────────────────────\n");
	      System.out.println(SpaceUtil.format("  1. 대출리스트 조회  ",68,0));
	      System.out.println(SpaceUtil.format("  2. 연체리스트 조회  ",68,0));
	      System.out.println(SpaceUtil.format("9. 홈 화면으로  ",67,0));
		System.out.println();
		System.out.print(" >> 번호 입력 : ");

		switch (ScanUtil.nextInt()) {
		case 1:
			return View.BOOK_BORROW_LIST_M;
		case 2:
			return View.BOOK_OVERDUE_LIST;
		case 9:
			return View.ADMIN_HOME;
		default:
			System.out.println(" >> 번호를 잘못 입력하셨습니다.");
			return View.BOOK_OVERDUE_MANAGE;
		}
	}
	//대출리스트조회: 
	public static int bookbrrowlistM() {
		System.out.println();
		System.out.println(" ───────────────────────────────────────────────────────────────────");
	      System.out.println(SpaceUtil.format("대출리스트 조회",110,0));
	      System.out.println(" ───────────────────────────────────────────────────────────────────\n");
	      System.out.println(SpaceUtil.format("  1. 대출번호로 조회  ",68,0));
	      System.out.println(SpaceUtil.format("  2. 대출일로 조회  ",67,0));
	      System.out.println(SpaceUtil.format("  3. 반납상태로 조회  ",68,0));
	      System.out.println(SpaceUtil.format("  4. 회원번호로 조회  ",68,0));
	      System.out.println(SpaceUtil.format("  5. 도서번호로 조회  ",68,0));
	      System.out.println(SpaceUtil.format("9. 이전 페이지로 돌아가기  ",78,0));
		System.out.println();
		System.out.println(" * 검색할 키워드 번호를 입력 하십시오.");
		System.out.print(" >> 번호 입력 : ");
		switch (ScanUtil.nextInt()) {
		case 1:
			return View.BOOK_BORROW_LIST_BORRNO;
		case 2:
			return View.BOOK_BORROW_LIST_BORRDATE;
		case 3:
			return View.BOOK_BORROW_LIST_BROOSTATUS;
		case 4:
			return View.BOOK_BORROW_LIST_MEMNO;
		case 5:
			return View.BOOK_BORROW_LIST_BOOKNO;
		case 9:
			return View.BOOK_OVERDUE_MANAGE;
		default:
			System.out.println(" >> 번호를 잘못 입력하셨습니다.");
			return View.BOOK_BORROW_LIST_M;
		}
	}
	public static int borrowListBorrNo() {
		System.out.println();
		 System.out.println(" ------------------------ 대출리스트 조회 (검색어: 대출번호) ------------------");
		 System.out.println();
		System.out.print(" >> 대출번호를 입력하세요: ");
		String borrNo = ScanUtil.nextLine();
		System.out.println();
		
		List<Map<String, Object>> borrowListBrn = bbDAO.borrowListBorrNo(borrNo);
		if(!borrowListBrn.isEmpty()) {
			for (Map<String, Object> brn : borrowListBrn) {
		    System.out.println();
			System.out.println("회원이름: "+ brn.get("MEM_NAME"));
	        System.out.println("대여번호: "+ brn.get("BORR_NO"));
	        System.out.println("도서명: " + brn.get("BOOK_NAME"));
	        System.out.println("도서번호: " + brn.get("BOOK_NO"));
	        System.out.println("대여일: " + brn.get("BORR_DATE"));
	        System.out.println("반납예정일: " + brn.get("RET_DUE_DATE"));   
	        System.out.println("실반납일: "+ brn.get("RET_DATE"));
	        System.out.println("반납상태: "+ brn.get("BORR_STATUS"));
	        System.out.println();
	        System.out.println(" -------------------------------------------------------------------");
	        }
			} else {
				System.out.println(" >> 도서번호 확인 후 다시 시도해주세요.");
			  	   System.out.print(" >> 확인 : enter");
				ScanUtil.nextLine();
		}
		return View.BOOK_BORROW_LIST_M;
	}
	
	
	public static int borrowListBorrDate() {
		System.out.println();
		System.out.println(" ******* 대출리스트 조회 (검색어: 대출일 YY/DD/MM) ******* ");  
		System.out.println();		
		System.out.print(" >> 대출일을 입력하세요: ");

		String borrDate = ScanUtil.nextLine();
		System.out.println();
		List<Map<String, Object>> borrowListBordt = bbDAO.borrowListBorrDate(borrDate);
		if(!borrowListBordt.isEmpty()) {
		for (Map<String, Object> dt : borrowListBordt) {
			
			System.out.println();
			System.out.println(" 회원이름: "+ dt.get("MEM_NAME"));
	        System.out.println(" 대여번호: "+ dt.get("BORR_NO"));
	        System.out.println(" 도서명: " + dt.get("BOOK_NAME"));
	        System.out.println(" 도서번호: " + dt.get("BOOK_NO"));
	        System.out.println(" 대여일: " + dt.get("BORR_DATE"));
	        System.out.println(" 반납예정일: " + dt.get("RET_DUE_DATE"));   
	        System.out.println(" 실반납일: "+ dt.get("RET_DATE"));
	        System.out.println(" 반납상태: "+ dt.get("BORR_STATUS"));
	        System.out.println();
	        System.out.println(" -------------------------------------------------------------------");

	        }
		} else {
			System.out.println("* 대출일을 확인 하십시오.");
		  	   System.out.print(" >> 확인 : enter");
			ScanUtil.nextLine();
		}
		return View.BOOK_BORROW_LIST_M;
	}
	
	
	
	public static int borrowListBorrStatus() {
		System.out.println();
		System.out.println(" ------------------------ 대출리스트 조회 (검색어: 반납상태) ------------------"); 
		System.out.print(" >> 반납상태를 입력하세요 (Y:대출 가능 / N: 대출 중) : ");
		String borrStatus = ScanUtil.nextLine();
		System.out.println();
		
		List<Map<String, Object>> borrowListbs = bbDAO.borrowListBorrStatus(borrStatus);
		if(!borrowListbs.isEmpty()) {
		for (Map<String, Object> bs : borrowListbs) {
			System.out.println(" 회원이름: "+ bs.get("MEM_NAME"));
	        System.out.println(" 대여번호: "+ bs.get("BORR_NO"));
	        System.out.println(" 도서명: " + bs.get("BOOK_NAME"));
	        System.out.println(" 도서번호: " + bs.get("BOOK_NO"));
	        System.out.println(" 대여일: " + bs.get("BORR_DATE"));
	        System.out.println(" 반납예정일: " + bs.get("RET_DUE_DATE"));   
	        System.out.println(" 실반납일: "+ bs.get("RET_DATE"));
	        System.out.println(" 반납상태: "+ bs.get("BORR_STATUS"));
	        System.out.println();
	        System.out.println(" -------------------------------------------------------------------");
		}
		} else {
			System.out.println("* 반납상태를 확인 하십시오.");
		  	   System.out.print(" >> 확인 : enter");
			ScanUtil.nextLine();
		}
		return View.BOOK_BORROW_LIST_M;
	}
	
	public static int borrowListMemNo() {
		System.out.println();
		System.out.println(" ------------------------ 대출리스트 조회 (검색어: 회원번호) ------------------"); 
		
		System.out.println();	
		System.out.print(" >> 회원번호를 입력하세요: ");
		String memNo = ScanUtil.nextLine();
		System.out.println();		
		
		List<Map<String, Object>> borrowListmn = bbDAO.borrowListMemNo(memNo);
		if(!borrowListmn.isEmpty()) {
		for (Map<String, Object> mn : borrowListmn) {
			System.out.println();
			System.out.println(" 회원이름: "+ mn.get("MEM_NAME"));
	        System.out.println(" 대여번호: "+ mn.get("BORR_NO"));
	        System.out.println(" 도서명: " + mn.get("BOOK_NAME"));
	        System.out.println(" 도서번호: " + mn.get("BOOK_NO"));
	        System.out.println(" 대여일: " + mn.get("BORR_DATE"));
	        System.out.println(" 반납예정일: " + mn.get("RET_DUE_DATE"));   
	        System.out.println(" 실반납일: "+ mn.get("RET_DATE"));
	        System.out.println(" 반납상태: "+ mn.get("BORR_STATUS"));
	        System.out.println();
	        System.out.println(" -------------------------------------------------------------------");
		}
		} else {
			System.out.println("* 회원번호를 확인 하십시오.");
		  	   System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
	}
	return View.BOOK_BORROW_LIST_M;
}

	public static int borrowListBookNo() {
		System.out.println();
		System.out.println(" ------------------------ 대출리스트 조회 (검색어: 도서번호) ------------------"); 
		System.out.println();	
		System.out.print(" >> 도서번호를 입력하세요: ");
		String bookNo = ScanUtil.nextLine();
		System.out.println();	
		
		List<Map<String, Object>> borrowListbn = bbDAO.borrowListBookNo(bookNo);
		if(!borrowListbn.isEmpty()) {
		for (Map<String, Object> bn : borrowListbn) {
			System.out.println();
			System.out.println(" 회원이름: "+ bn.get("MEM_NAME"));
	        System.out.println(" 대여번호: "+ bn.get("BORR_NO"));
	        System.out.println(" 도서명: " + bn.get("BOOK_NAME"));
	        System.out.println(" 도서번호: " + bn.get("BOOK_NO"));
	        System.out.println(" 대여일: " + bn.get("BORR_DATE"));
	        System.out.println(" 반납예정일: " + bn.get("RET_DUE_DATE"));   
	        System.out.println(" 실반납일: "+ bn.get("RET_DATE"));
	        System.out.println(" 반납상태: "+ bn.get("BORR_STATUS"));
	        System.out.println();
	        System.out.println(" -------------------------------------------------------------------");
		}
		} else {
			System.out.println(" >> 도서번호 확인 후 다시 시도해주세요.");
		  	   System.out.print(" >> 확인 : enter");
			ScanUtil.nextLine();
		}
		return View.BOOK_BORROW_LIST_M;
	}
	//연체리스트조회:
	public static int overdueList() {
        System.out.println();
        System.out.println(" ************************* 연체리스트 조회 **************************** ");  
         List<Map<String,Object>> overdueLists = bbDAO.overdueList();
         
         if(overdueLists.isEmpty()) {
            System.out.println(" >> 연체 정보가 없습니다.");
         } else {
           for(Map<String,Object> overdueList : overdueLists) {
              String memName = (String) overdueList.get("MEM_NAME");
              System.out.println();
              System.out.println(" 회원이름: "+ memName);
              System.out.println(" 대여번호: "+ overdueList.get("BORR_NO"));
              System.out.println(" 도서번호: "+overdueList.get("BOOK_NO"));
              System.out.println(" 도서명: " + overdueList.get("BOOK_NAME"));
              System.out.println(" 대여일: " + overdueList.get("BORR_DATE"));
              System.out.println(" 반납예정일: " + overdueList.get("RET_DUE_DATE"));   
              System.out.println();
  	        System.out.println(" -------------------------------------------------------------------");

             }
         }
               System.out.println();
          	   System.out.print(" >> 확인 : enter");
               ScanUtil.nextLine();
               return View.BOOK_OVERDUE_MANAGE;
	
	}
}


	 
	

	
    



