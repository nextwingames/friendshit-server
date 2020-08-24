package org.nextwin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.nextwin.dto.MemberDto;
import org.nextwin.friendshit.service.RegisterService;

public class MemberDao {
	
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://127.0.0.1:3306/friendshit";
	private String user = "nextwin";
	private String password = "soccar";
	
	/**
	 * 회원 등록
	 * @param dto
	 */
	public int register(MemberDto dto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int ri = 0;
		
		System.out.println("ID: " + dto.getId() + ", Nickname: " + dto.getNickname() + " ---- ");
		try {
			connection = getConnection();
			String sql = "insert into member (id, nickname, pw, mail) values (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, dto.getId());
			preparedStatement.setString(2, dto.getNickname());
			preparedStatement.setString(3, dto.getPw());
			preparedStatement.setString(4, dto.getMail());
			
			ri = preparedStatement.executeUpdate();
			System.out.println("Success to register");
		} catch (Exception e) {
			System.out.println("Failed to register");
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
