package ddit.project03.sec01.service;

import java.util.Scanner;

import ddit.project03.sec01.dao.MemberDao;
import ddit.project03.sec01.util.LoginUserNo;
import ddit.project03.sec01.util.ScanUtil;
import ddit.project03.sec01.util.SpaceUtil;
import ddit.project03.sec01.util.View;
import ddit.project03.sec01.vo.MemberVO;

public class MemberService {

   static Scanner sc= new Scanner(System.in);
   private static MemberService instance = null;
   
   private MemberService() {
      
   }
   
   public static MemberService getInstance() {
      if(instance==null)
         instance = new MemberService();
      return instance;
   }

   static MemberDao memDao= MemberDao.getInstance();
   
   public static int home() {
	  System.out.println();
	  System.out.println("DDDDDDDDDDDDD     DDDDDDDDDDDDD     IIIIIIIIITTTTTTTTTTTTTTTTTTTTTTT");
	  System.out.println("D::::::::::::DDD  D::::::::::::DDD  I::::::::T:::::::::::::::::::::T");
	  System.out.println("D:::::::::::::::DDD:::::::::::::::DDI::::::::T:::::::::::::::::::::T");
	  System.out.println("DDD:::::DDDDD:::::DDD:::::DDDDD:::::II::::::IT:::::TT:::::::TT:::::T");
	  System.out.println("  D:::::D    D:::::DD:::::D    D:::::DI::::I TTTTTT  T:::::T  TTTTTT" );
	  System.out.println("  D:::::D     D:::::D:::::D     D:::::I::::I         T:::::T        ");
	  System.out.println("  D:::::D     D:::::D:::::D     D:::::I::::I         T:::::T        ");
	  System.out.println("  D:::::D     D:::::D:::::D     D:::::I::::I         T:::::T        ");
	  System.out.println("  D:::::D     D:::::D:::::D     D:::::I::::I         T:::::T        ");
	  System.out.println("  D:::::D     D:::::D:::::D     D:::::I::::I         T:::::T        ");
	  System.out.println("  D:::::D     D:::::D:::::D     D:::::I::::I         T:::::T        ");
	  System.out.println("DDD:::::DDDDD:::::DDD:::::DDDDD:::::II::::::II     TT:::::::TT      ");
	  System.out.println("D:::::::::::::::DDD:::::::::::::::DDI::::::::I     T:::::::::T      ");
	  System.out.println("D::::::::::::DDD  D::::::::::::DDD  I::::::::I     T:::::::::T      ");
	  System.out.println("DDDDDDDDDDDDD     DDDDDDDDDDDDD     IIIIIIIIII     TTTTTTTTTTT      ");
      System.out.println();
      System.out.println(" ───────────────────────────────────────────────────────────────────");
	  System.out.println(SpaceUtil.format("           DDIT  도서관                 ",70,0));
      System.out.println(" ───────────────────────────────────────────────────────────────────");
      System.out.println(SpaceUtil.format("1. 로그인  ",63,0));
      System.out.println(SpaceUtil.format("2. 비밀번호 찾기  ",70,0));
      System.out.println(SpaceUtil.format("9. 종료  ",61,0));

      System.out.println();
      System.out.print(" >> 번호 입력 : ");
      
      switch (ScanUtil.nextInt()) {
      case 1: return View.MEMBER_LOGIN;
      case 2: return View.MEMBER_FIND_PW;
      case 9: return View.HOME_END;
      default:
         System.out.println(" 번호를 잘못 입력하셨습니다.");
         return View.HOME;
      }
   }   

   // 로그인
   public static int login() {
      System.out.println();
      System.out.println(" ───────────────────────────────────────────────────────────────────");
	  System.out.println(SpaceUtil.format(" 로그인 ",110,0));
      System.out.println(" ───────────────────────────────────────────────────────────────────");
      System.out.print(" >> 아이디 : ");
      String mem_no = sc.nextLine();
      System.out.print(" >> 비밀번호 : ");
      String mem_passwd = sc.nextLine();
      MemberVO memVO = new MemberVO(); // 객체생성
      memVO.setMem_no(mem_no);           //값 넣어주기
      memVO.setMem_passwd(mem_passwd);
      memVO= memDao.login(memVO);
      
       if (mem_no.equals(memVO.getMem_no()) && mem_passwd.equals(memVO.getMem_passwd())) {
           System.out.println();
    	   System.out.println(" >> "+memVO.getMem_name()+"님이 로그인하셨습니다.");

           // 로그인 시 세션에 로그인된 회원 ID를 설정
           String loggedInUserNo = memVO.getMem_no();
           LoginUserNo.getInstance().setLoggedInUserNo(loggedInUserNo);
           
             if(memVO.getMem_name().equals("관리자")) {
                return View.ADMIN_HOME;
             }else 
                return View.MEM_HOME;
          } else{
             System.out.println("입력하신 정보와 일치하는 아이디/비밀번호가 없습니다.");
            return View.HOME;
          }
      }


   // 비밀번호 진짜 찾기
   public static int passwdFind() {
      System.out.println();
      System.out.println(" ───────────────────────────────────────────────────────────────────");
	  System.out.println(SpaceUtil.format(" 비밀번호 찾기 ",110,0));
      System.out.println(" ───────────────────────────────────────────────────────────────────");
      System.out.print(" >> 학번을 입력해주세요(S+입학년도+입학월+PC번호): ");
      String mem_no = sc.nextLine();
      System.out.print(" >> 전화번호를 입력해주세요: ");
      String mem_telno = sc.nextLine();
      MemberVO memVO = new MemberVO();
      memVO.setMem_no(mem_no);
      memVO.setMem_telno(mem_telno);
      memVO=memDao.passwdFind(memVO);
      
      if (mem_no.equals(memVO.getMem_no()) && mem_telno.equals(memVO.getMem_telno())) {
         System.out.println(" >> 현재 비밀번호는 "+ memVO.getMem_passwd()+"입니다.");
         return View.HOME;
       } else{
             System.out.println(" >> 입력하신 정보와 일치하는 아이디/전화번호가 없습니다.");          
             return View.MEMBER_FIND_PW;
      }
   }
   
   // 종료
      public static int homeEnd() {
    	 System.out.println();
         System.out.println(" *******************************************************************");
    	 System.out.println(SpaceUtil.format("프로그램 종료 ",100,0));
         System.out.println(" *******************************************************************");
         System.out.println(SpaceUtil.format(" >> DDIT 도서관을 이용해주셔서 감사합니다.",75,0));
         
         System.out.println("                                                               dddddddd     bbbbbbbb                                                                        ");
         System.out.println("                                                               d::::::d     b::::::b                                                                        ");
         System.out.println("                                                               d::::::d     b::::::b                                                                        ");
         System.out.println("                                                               d:::::d       b:::::b                                                                        ");
         System.out.println("   ggggggggg   ggggg  ooooooooooo     ooooooooooo      ddddddddd:::::d       b:::::bbbbbbbbbyyyyyyy           yyyyyyyeeeeeeeeeeee                           ");
         System.out.println("  g:::::::::ggg::::goo:::::::::::oo oo:::::::::::oo  dd::::::::::::::d       b::::::::::::::by:::::y         y:::::ee::::::::::::ee                         ");
         System.out.println(" g:::::::::::::::::o:::::::::::::::o:::::::::::::::od::::::::::::::::d       b::::::::::::::::y:::::y       y:::::e::::::eeeee:::::ee                       ");
         System.out.println("g::::::ggggg::::::go:::::ooooo:::::o:::::ooooo:::::d:::::::ddddd:::::d       b:::::bbbbb:::::::y:::::y     y:::::e::::::e     e:::::e    ~~~~~~~~~    ~~~~~~");
         System.out.println("g:::::g     g:::::go::::o     o::::o::::o     o::::d::::::d    d:::::d       b:::::b    b::::::by:::::y   y:::::ye:::::::eeeee::::::e  ~~:::::::::~  ~:::::~");
         System.out.println("g:::::g     g:::::go::::o     o::::o::::o     o::::d:::::d     d:::::d       b:::::b     b:::::b y:::::y y:::::y e:::::::::::::::::e  ~:::::~~:::::~~:::::~ ");
         System.out.println("g:::::g     g:::::go::::o     o::::o::::o     o::::d:::::d     d:::::d       b:::::b     b:::::b  y:::::y:::::y  e::::::eeeeeeeeeee  ~:::::~  ~::::::::::~  ");
         System.out.println("g::::::g    g:::::go::::o     o::::o::::o     o::::d:::::d     d:::::d       b:::::b     b:::::b   y:::::::::y   e:::::::e           ~~~~~~    ~~~~~~~~~~   ");
         System.out.println("g:::::::ggggg:::::go:::::ooooo:::::o:::::ooooo:::::d::::::ddddd::::::dd      b:::::bbbbbb::::::b    y:::::::y    e::::::::e                                 ");
         System.out.println(" g::::::::::::::::go:::::::::::::::o:::::::::::::::od:::::::::::::::::d      b::::::::::::::::b      y:::::y      e::::::::eeeeeeee                         ");
         System.out.println("  gg::::::::::::::g oo:::::::::::oo oo:::::::::::oo  d:::::::::ddd::::d      b:::::::::::::::b      y:::::y        ee:::::::::::::e                         ");
         System.out.println("    gggggggg::::::g   ooooooooooo     ooooooooooo     ddddddddd   ddddd      bbbbbbbbbbbbbbbb      y:::::y           eeeeeeeeeeeeee                         ");
         System.out.println("            g:::::g                                                                               y:::::y                                                   ");
         System.out.println("gggggg      g:::::g                                                                              y:::::y                                                    ");
         System.out.println("g:::::gg   gg:::::g                                                                             y:::::y                                                     ");
         System.out.println(" g::::::ggg:::::::g                                                                            y:::::y                                                      ");
         System.out.println("  gg:::::::::::::g                                                                            yyyyyyy                                                       ");
         System.out.println("    ggg::::::ggg                                                                                                                                            ");
         System.out.println("       gggggg                                                                                                                                               ");
         return 0;
         
         
      }

   // 회원 화면
      public static int memHome() {
         System.out.println();
         System.out.println(" ───────────────────────────────────────────────────────────────────");
   	  System.out.println(SpaceUtil.format("           DDIT  도서관                 ",70,0));
      System.out.println(" ───────────────────────────────────────────────────────────────────");
         System.out.println(SpaceUtil.format("  1. 도서 검색  ",65,0));
         System.out.println(SpaceUtil.format("  2. 도서 대여  ",65,0));
         System.out.println(SpaceUtil.format("  3. 도서 반납  ",65,0));
         System.out.println(SpaceUtil.format("    4. 열람실 이용  ",65,0));
         System.out.println(SpaceUtil.format("     5. 희망도서 신청  ",65,0));
         System.out.println(SpaceUtil.format("  6. 마이페이지  ",65,0));
         System.out.println(SpaceUtil.format(" 9. 로그아웃  ",65,0));
         System.out.println();
         System.out.print(" >> 번호 입력 : ");
         
         switch(ScanUtil.nextInt()) {
         case 1: return View.BOOK_SEARCH;
         case 2: return View.BOOK_BORROW;
         case 3: return View.BOOK_RETURN;
         case 4: return View.READING_ROOM;
         case 5: return View.REQUEST_BOOK_MEM;
         case 6: return View.MYPAGE;
         case 9: return View.MEMBER_LOGOUT;
         default:
            System.out.println(" >> 번호를 잘못 입력하셨습니다.");
            return View.MEM_HOME;
         }
      }
      
   // 마이페이지
      public static int mypage() {
         System.out.println();
         System.out.println(" ───────────────────────────────────────────────────────────────────");
   	     System.out.println(SpaceUtil.format(" 마이페이지 ",110,0));
         System.out.println(" ───────────────────────────────────────────────────────────────────");
         System.out.println();
         System.out.println(SpaceUtil.format("1. 나의 도서 대출 내역 조회",70,0));
         System.out.println(SpaceUtil.format("2. 나의 열람실 예약 조회 ",68,0));
         System.out.println(SpaceUtil.format("3. 나의 희망도서 신청내역 조회",72,0));
         System.out.println(SpaceUtil.format("4. 개인정보 수정",58,0));
         System.out.println(SpaceUtil.format("9. 홈으로 ",52,0));
         System.out.println();
         System.out.print(" >> 번호 입력 : ");
  
         switch(ScanUtil.nextInt()) {
         case 1: return View.BOOK_BORROW_LIST_S;
         case 2: return View.MY_SEAT_RESINFO;
         case 3: return View.MYPAGE_REQUESTBOOKLIST;
         case 4: return View.MYPAGE_UPDATE;
         case 9: return View.MEM_HOME;
         default:
            System.out.println(" >> 번호를 잘못 입력하셨습니다.");
            return View.MYPAGE;
         }
      }
   // 개인정보 수정 페이지
      public static int memUpdate(){
      System.out.println();
      System.out.println(" ───────────────────────────────────────────────────────────────────");
      System.out.println(SpaceUtil.format(" 개인정보 수정 ",110,0));
      System.out.println(" ───────────────────────────────────────────────────────────────────");

      System.out.println(SpaceUtil.format("1. 비밀번호 변경 ",70,0));
      System.out.println(SpaceUtil.format("2. 전화번호 변경 ",70,0));
      System.out.println(SpaceUtil.format("9. 이전 화면으로 ",70,0));
      System.out.println();
      System.out.print(" >> 번호 입력 : ");
     
      switch(ScanUtil.nextInt()) {
      case 1: return View.MEMBER_UPDATE_PWD;
      case 2: return View.MEMBER_UPDATE_TEL;
      case 9: return View.MYPAGE;
      
      default:
         System.out.println(" >> 번호를 잘못 입력하셨습니다.");
         return View.MYPAGE_UPDATE;
      }
      }
   // 비밀번호 수정
      public static int passwdUpdate() {
         System.out.println();
         System.out.println(" ───────────────────────────────────────────────────────────────────");
         System.out.println(SpaceUtil.format(" 비밀번호 변경 ",110,0));
         System.out.println(" ───────────────────────────────────────────────────────────────────");
         System.out.print(" >> 아이디 입력 : ");
         String mem_no = sc.nextLine();
         System.out.print(" >> 변경할 패스워드를 입력 : ");
         String mem_passwd = sc.nextLine();
         MemberVO memVO = new MemberVO();
         memVO.setMem_no(mem_no);
         memVO.setMem_passwd(mem_passwd);
         
         int res= memDao.passwdUpdate(memVO);
         if (res>0) {
            System.out.println(" >> 회원님의 패스워드가 정상적으로 변경되었습니다.");
            return View.MEM_HOME;
         } else {
            System.out.println(" >> 변경에 실패하였습니다.");
            return View.MEMBER_UPDATE_PWD;
         }   
      }
      // 전화번호 변경
         public static int telnoUpdate() {
            System.out.println();
            System.out.println(" ───────────────────────────────────────────────────────────────────");
            System.out.println(SpaceUtil.format(" 전화번호 변경 ",110,0));
            System.out.println(" ───────────────────────────────────────────────────────────────────");
            System.out.print(" >> 아이디 입력 : ");
            String mem_no = sc.nextLine();
            System.out.print(" >> 변경할 전화번호 입력 : ");
            String mem_telno = sc.nextLine();
            MemberVO memVO = new MemberVO();
            memVO.setMem_no(mem_no);
            memVO.setMem_telno(mem_telno);
     
            int res= memDao.telUpdate(memVO);
            if (res>0) {
               System.out.println(" >> 회원님의 전화번호가 정상적으로 변경되었습니다.");
               return View.MEM_HOME;
               } else {
               System.out.println(" >> 변경에 실패하였습니다.");
               return View.MEMBER_UPDATE_TEL;
               }   
            }
                           
      
   // 관리자 화면
      public static int adminHome() {
    	 System.out.println();
    	 System.out.println(" ───────────────────────────────────────────────────────────────────");
    	 System.out.println(SpaceUtil.format("           DDIT  도서관  <관리자용>                ",75,0));
    	 System.out.println(" ───────────────────────────────────────────────────────────────────");
         System.out.println(SpaceUtil.format(" 1. 도서 관리  ",64,0));
         System.out.println(SpaceUtil.format(" 2. 열람실 관리  ",65,0));
         System.out.println(SpaceUtil.format(" 3. 대출/연체 관리 ",67,0));
         System.out.println(SpaceUtil.format(" 4. 학생 관리",62,0));
         System.out.println(SpaceUtil.format(" 9. 로그아웃 ",62,0));
         System.out.println();
         System.out.print(" >> 번호 입력 : ");
         
         switch(ScanUtil.nextInt()) {
         case 1: return View.BOOK_MANAGER;
         case 2: return View.READIND_ROOM_MANAGER;
         case 3: return View.BOOK_OVERDUE_MANAGE;
         case 4: return View.ADMPAGE;
         case 9: return View.MEMBER_LOGOUT;
         default:
            System.out.println(" >> 번호를 잘못 입력하셨습니다.");
            return View.ADMIN_HOME;
      }
      }
      
      public static int admpage() {
         System.out.println();
         System.out.println(" ───────────────────────────────────────────────────────────────────");
      	  System.out.println(SpaceUtil.format("           학생 관리                 ",115,0));
         System.out.println(" ───────────────────────────────────────────────────────────────────");
         System.out.println(SpaceUtil.format("1. 회원 등록 ",66,0));
         System.out.println(SpaceUtil.format("2. 회원 삭제 ",66,0));
         System.out.println(SpaceUtil.format("9. 홈으로 ",62,0));
         System.out.println();
         System.out.print(" >> 번호 입력: ");
         switch(ScanUtil.nextInt()) {
         case 1: return View.MEMBER_INSERT;
         case 2: return View.MEMBER_DELETE;
         case 9: return View.ADMIN_HOME;
         default:
            System.out.println(" >> 번호를 잘못 입력하셨습니다.");
            return View.ADMPAGE;
         }
      }
      
   // 회원 등록
      public static int insert() {
      System.out.println();
      System.out.println(" ───────────────────────────────────────────────────────────────────");
   	  System.out.println(SpaceUtil.format("           회원 등록               ",115,0));
      System.out.println(" ───────────────────────────────────────────────────────────────────");
      System.out.print(" >> 학생 번호 : ");
      String mem_no = sc.nextLine();
      System.out.print(" >> 초기 비밀번호  설정: ");
      String mem_passwd= sc.nextLine();
      System.out.print(" >> 학생 이름 : ");
      String mem_name = sc.nextLine();
      System.out.print(" >> 핸드폰 번호 : ");
      String mem_telno = sc.nextLine();
      System.out.print(" >> 회원 구분(학생 S,관리자 M): ");
      String mem_cla = sc.nextLine();
      MemberVO memVO = new MemberVO();
      memVO.setMem_no(mem_no);
      memVO.setMem_passwd(mem_passwd);
      memVO.setMem_name(mem_name);
      memVO.setMem_telno(mem_telno);
      memVO.setMem_cla(mem_cla);
      int res= memDao.insert(memVO); 
	      if(res>0) {
	         System.out.println(" >> 자료가 정상적으로 저장되었습니다.");
	      } else{
	         System.out.println(" >> 자료 저장에 실패했습니다.");
	      }
      return View.ADMIN_HOME;
  }
      
      // 로그아웃
         public static int logout() {
            System.out.println();
            System.out.print(" >> 로그아웃 하시겠습니까? (Y/N) : ");
            String rs= sc.nextLine();
         
            if(rs.equalsIgnoreCase("y")) {
               return View.HOME;
               
            } else if (rs.equalsIgnoreCase("n")) {
            	
            	String memNo = LoginUserNo.getInstance().getLoggedInUserNo();
            	if(memNo.equals("MANAGER")) {
            		return View.ADMIN_HOME;
            	}else {
            		return View.MEM_HOME;
            	}
            } else 
               System.out.println(" >> 잘못 입력하셨습니다.");
               return View.MEM_HOME;
         }

      // 회원 삭제
      public static int memDelete() {
         System.out.println();
         System.out.println(" ───────────────────────────────────────────────────────────────────");
         System.out.println(SpaceUtil.format(" 회원 삭제 ",110,0));
         System.out.println(" ───────────────────────────────────────────────────────────────────");
         System.out.print(" >> 학생 번호 : ");
         String mem_no = sc.nextLine();
         MemberVO memVO = new MemberVO();
         memVO.setMem_no(mem_no);
         int res= memDao.delete(memVO);
         if (res>0) {
            System.out.println(" >>"+memVO.getMem_name()+"님이 정상적으로 삭제되었습니다.");
            return View.ADMIN_HOME;
         }else {
            System.out.println(" >> 회원 삭제에 실패하였습니다.");
            return View.MEMBER_DELETE;
            }
         }
      }

