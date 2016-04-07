package com.andy.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String uname;
	private String pwd;
	private String nickName;
	private LocalDateTime ctime;
	private Date fightTime;
	
	public User() {
	}

	public User(String id,String uname, String pwd) {
		setId(id);
		setUname(uname);
		setPwd(pwd);
		setNickName("321321");
		setCtime(LocalDateTime.now());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public LocalDateTime getCtime() {
		return ctime;
	}

	public void setCtime(LocalDateTime ctime) {
		this.ctime = ctime;
	}

	public Date getFightTime() {
		return fightTime;
	}

	public void setFightTime(Date fightTime) {
		this.fightTime = fightTime;
	}

}
