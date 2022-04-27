package com.exam.dto;

import java.sql.Timestamp;

public class BoardDto {
	private int BOARD_ID, KIND_ID, ID;
	private String TITLE, USER_ID, CONTENT, KIND;
	private Timestamp DATE;
	
	public int getBOARD_ID() {
		return BOARD_ID;
	}
	public void setBOARD_ID(int BOARD_ID) {
		this.BOARD_ID = BOARD_ID;
	}
	public int getKIND_ID() {
		return KIND_ID;
	}
	public void setKIND_ID(int KIND_ID) {
		this.KIND_ID = KIND_ID;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String TITLE) {
		this.TITLE = TITLE;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String USER_ID) {
		this.USER_ID = USER_ID;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String CONTENT) {
		this.CONTENT = CONTENT;
	}
	public Timestamp getDATE() {
		return DATE;
	}
	public void setDATE(Timestamp DATE) {
		this.DATE = DATE;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getKIND() {
		return KIND;
	}
	public void setKIND(String KIND) {
		this.KIND = KIND;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardDto [KIND_ID=").append(KIND_ID)
				.append(", BOARD_ID=").append(BOARD_ID)
				.append(", TITLE=").append(TITLE)
				.append(", USER_ID=").append(USER_ID)
				.append(", CONTENT=").append(CONTENT)
				.append(", DATE=").append(DATE)
				.append(", ID=").append(ID)
				.append(", KIND=").append(KIND).append("]");
		return builder.toString();
	}
}
