package com.bargadss.Propozal.Admin.bean;

public class AdminLogin {

	private long serialNo;
	private String userName, password;

	public AdminLogin() {
	}

	public long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
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

	@Override
	public String toString() {
		return "AdminLogin [serialNo=" + serialNo + ", userName=" + userName
				+ ", password=" + password + "]";
	}

}
