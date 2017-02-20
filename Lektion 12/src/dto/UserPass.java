package dto;

public class UserPass {
	private String username;
	private String password;

	public String getuserName() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserPass [username=" + username + ", password=" + password + "]";
	}
	

}
