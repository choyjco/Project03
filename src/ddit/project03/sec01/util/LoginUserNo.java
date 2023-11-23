package ddit.project03.sec01.util;

public class LoginUserNo {
	  private static LoginUserNo instance;
	    private String loggedInUserId;

	    private LoginUserNo() {
	        // private 생성자로 외부에서 인스턴스 생성을 막음
	    }

	    public static LoginUserNo getInstance() {
	        if (instance == null) {
	            instance = new LoginUserNo();
	        }
	        return instance;
	    }

	    public void setLoggedInUserNo(String userId) {
	        this.loggedInUserId = userId;
	    }

	    public String getLoggedInUserNo() {
	        return loggedInUserId;
	    }
	}

