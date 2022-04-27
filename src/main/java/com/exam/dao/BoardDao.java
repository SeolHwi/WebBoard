package com.exam.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.exam.dto.BoardDto;

public class BoardDao {
	private static BoardDao boardDao = new BoardDao();
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int result = 0;
	
	private BoardDao() {
		super();
	}
	
	public static synchronized BoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDao();
		}
		return boardDao;
	}
	
	public Connection getConnect() {
		String url = "jdbc:mariadb://localhost:3306/boarddb";
		String id = "root", pw = "root";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// boardID값을 1씩 증가
	public int nextval() {
		con = getConnect();
		StringBuffer query = new StringBuffer();
		query.append("SELECT MAX(BOARD_ID) ").append("FROM BOARD");
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("MAX(BOARD_ID)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}
	
	public int write(BoardDto boardDto) {
		con = getConnect();
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO BOARD(KIND_ID, BOARD_ID, TITLE, USER_ID, DATE, CONTENT) VALUES (?, ?, ?, ?, NOW(), ?)");

		try {
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, boardDto.getKIND_ID());
			pstmt.setInt(2, boardDto.getBOARD_ID());
			pstmt.setString(3, boardDto.getTITLE());
			pstmt.setString(4, boardDto.getUSER_ID());
			pstmt.setString(5, boardDto.getCONTENT());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, null);
		}
		return result;
	}
	
	public List<BoardDto> selectKind() {
		List<BoardDto> list = new ArrayList<>();
		con = this.getConnect();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID, KIND FROM KINDS");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setID(rs.getInt("ID"));
				boardDto.setKIND(rs.getString("KIND"));
				list.add(boardDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(con, pstmt, rs);
		}
		return list;
	}
	
	public List<BoardDto> selectList() {
		List<BoardDto> list = new ArrayList<>();
		con = this.getConnect();
		StringBuffer sql = new StringBuffer();
		sql.append("select KIND, BOARD_ID, TITLE, USER_ID, `DATE`, CONTENT from BOARD left join KINDS on KIND_ID=ID ORDER BY BOARD_ID DESC");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setKIND(rs.getString("KIND"));
				boardDto.setBOARD_ID(rs.getInt("BOARD_ID"));
				boardDto.setTITLE(rs.getString("TITLE"));
				boardDto.setUSER_ID(rs.getString("USER_ID"));
				boardDto.setDATE(rs.getTimestamp("DATE"));
				boardDto.setCONTENT(rs.getString("CONTENT"));
				list.add(boardDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(con, pstmt, rs);
		}
		return list;
	}
	
	public BoardDto selectByID(String BOARD_ID) {
		BoardDto boardDto = new BoardDto();
		con = getConnect();
		String sql = "SELECT KINDS.KIND, BOARD.BOARD_ID, BOARD.TITLE, BOARD.USER_ID, BOARD.`DATE`, BOARD.CONTENT FROM BOARD LEFT JOIN KINDS ON KIND_ID=ID WHERE BOARD_ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, BOARD_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardDto.setKIND(rs.getString("KIND"));
				boardDto.setBOARD_ID(rs.getInt("BOARD_ID"));
				boardDto.setTITLE(rs.getString("TITLE"));
				boardDto.setUSER_ID(rs.getString("USER_ID"));
				boardDto.setDATE(rs.getTimestamp("DATE"));
				boardDto.setCONTENT(rs.getString("CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return boardDto;
	}
	
	public int update(BoardDto boardDto) {
		con = getConnect();
		StringBuffer query = new StringBuffer();
		query.append("UPDATE BOARD SET USER_ID = ?, TITLE = ?, CONTENT = ? WHERE BOARD_ID = ?");
		
		try {
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, boardDto.getUSER_ID());
			pstmt.setString(2, boardDto.getTITLE());
			pstmt.setString(3, boardDto.getCONTENT());
			pstmt.setInt(4, boardDto.getBOARD_ID());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, null);
		}
		return result;
	}
	
	public int del(int BOARD_ID) {
		con = getConnect();
		String sql = "DELETE FROM BOARD WHERE BOARD_ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, BOARD_ID);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, null);
		}
		return result;
	}
	
}
