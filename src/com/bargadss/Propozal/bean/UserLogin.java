package com.bargadss.Propozal.bean;

public class UserLogin {

	private String userName, password;

	public UserLogin() {
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

	// *************************************************************************//
	@Override
	public String toString() {
		return "UserLogin [userName=" + userName + ", password=" + password
				+ "]";
	}

	// *************************************************************************//

}
