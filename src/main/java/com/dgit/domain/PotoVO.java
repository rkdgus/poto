package com.dgit.domain;

import java.util.Date;

public class PotoVO {
	private int pno;
	private String userid;
	private String title;
	private String fullName;
	private Date regdate;

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "PotoVO [pno=" + pno + ", userid=" + userid + ", title=" + title + ", fullName=" + fullName
				+ ", regdate=" + regdate + "]";
	}

}
