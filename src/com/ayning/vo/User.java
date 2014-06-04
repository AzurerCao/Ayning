package com.ayning.vo;

import java.sql.Timestamp;

public class User {

	private String id;

	private String password;

	private String email;

	private String nickName;

	private String location;

	private char sex;

	private String birthDate;

	private String mobile;

	private String introduction;

	private Timestamp registerTime;

	private Timestamp lastSignTime;

	private boolean activated = false;

	private String company;

	private String job;

	private int reputation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public Timestamp getLastSignTime() {
		return lastSignTime;
	}

	public void setLastSignTime(Timestamp lastSignTime) {
		this.lastSignTime = lastSignTime;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", nickName=" + nickName
				+ ", location=" + location + ", sex=" + sex + ", birthDate="
				+ birthDate + ", mobile=" + mobile + ", introduction="
				+ introduction + ", registerTime=" + registerTime
				+ ", lastSignTime=" + lastSignTime + ", activated=" + activated
				+ ", company=" + company + ", job=" + job + ", reputation="
				+ reputation + "]";
	}

}
