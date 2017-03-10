package dto;

public class User {
	private Long userId;
	private String userName;
	private String password;
	
	public User(){	}
	
	public User(Long userID, String userName, String password) {
		super();
		this.userId = userID;
		this.userName = userName;
		this.password = password;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userID) {
		this.userId = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
