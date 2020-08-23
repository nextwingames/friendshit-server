package org.nextwin.dto;

public class MemberDto {
	
	private String id;
	private String nickname;
	private String pw;
	private String mail;
	private int totalGame;
	private int gold;
	private int silver;
	private int bronze;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public int getTotalGame() {
		return totalGame;
	}
	
	public void setTotalGame(int totalGame) {
		this.totalGame = totalGame;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public int getSilver() {
		return silver;
	}
	
	public void setSilver(int silver) {
		this.silver = silver;
	}
	
	public int getBronze() {
		return bronze;
	}
	
	public void setBronze(int bronze) {
		this.bronze = bronze;
	}
	
}
