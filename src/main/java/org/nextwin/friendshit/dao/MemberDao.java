package org.nextwin.friendshit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.nextwin.friendshit.dto.MemberDto;
import org.nextwin.friendshit.service.LoginService;
import org.nextwin.friendshit.service.RegisterService;
import org.nextwin.util.Logger;

public class MemberDao {
	
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://127.0.0.1:3306/friendshit";
	private String user = "nextwin";
	private String password = "soccar";
	
	private final String TAG_REGISTER = "REGISTER";
	private final String TAG_LOGIN = "LOGIN";
	
	/**
	 * 회원 등록
	 * @param dto
	 * @return Success code
	 */
	public int register(MemberDto dto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int ri = 0;
		
		Logger.log(TAG_REGISTER, "ID: " + dto.getId() + ", Nickname: " + dto.getNickname());
		try {
			connection = getConnection();
			String sql = "insert into member (id, nickname, pw, mail) values (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, dto.getId());
			preparedStatement.setString(2, dto.getNickname());
			preparedStatement.setString(3, dto.getPw());
			preparedStatement.setString(4, dto.getMail());
			
			ri = preparedStatement.executeUpdate();
			Logger.log(TAG_REGISTER, "Success to register");
		} catch (Exception e) {
			Logger.log(TAG_REGISTER, "Failed to register");
			if(e.toString().contains("PRIMARY"))
				ri = RegisterService.REGISTER_FAIL_ID;
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
				if(connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int login(MemberDto dto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int ri = 0;
		
		Logger.log(TAG_LOGIN, "ID: " + dto.getId());
		try {
			connection = getConnection();
			String sql = "select * from member where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, dto.getId());
			
			resultSet = preparedStatement.executeQuery();
			// id 있음
			if(resultSet.next()) {
				if(resultSet.getString("pw").equals(dto.getPw())) { 
					Logger.log(TAG_LOGIN, "Success to login");
					ri = LoginService.LOGIN_SUCCESS;
					
					dto.setNickname(resultSet.getString("nickname"));
					dto.setTotalGame(resultSet.getInt("totalGame"));
					dto.setGold(resultSet.getInt("gold"));
					dto.setSilver(resultSet.getInt("silver"));
					dto.setBronze(resultSet.getInt("bronze"));
				}
				else {
					Logger.log(TAG_LOGIN, "Failed to login : Invalid Password");
					ri = LoginService.LOGIN_FAIL_INVALID_PW;
				}
			}
			else 
				Logger.log(TAG_LOGIN, "Failed to login : Invalid ID");		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null)
					resultSet.close();
				if(preparedStatement != null)
					preparedStatement.close();
				if(connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ri;
	}
	
	private Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
