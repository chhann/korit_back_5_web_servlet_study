package com.study.notice_board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.notice_board.config.DB;
import com.study.notice_board.entity.WriterInfo;

public class WriteDataDao {
	private static WriteDataDao instance;
	
	private WriteDataDao() {}
	
	public static WriteDataDao getInstance() {
		if(instance == null) {
			instance = new WriteDataDao();
		}
		return instance;
	}
	
	public int saveWriterInfo(WriterInfo writerInfo) {
		Connection con  = null;
		PreparedStatement pstmt = null;
		int successConut = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(DB.URL, DB.USERNAME, DB.PASSWORD);
			String sql = "insert into write_tb(write_title, write_writer, write_password, write_contents, write_date) values(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writerInfo.getTitle());
			pstmt.setString(2,  writerInfo.getWriter());
			pstmt.setString(3,  writerInfo.getPassword());
			pstmt.setString(4,  writerInfo.getContents());
			pstmt.setString(5,  writerInfo.getDate());
			successConut = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return successConut;
		
	}
	
	public List<WriterInfo> getWriteInfoListAll() {
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WriterInfo> writerInfos = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(DB.URL, DB.USERNAME, DB.PASSWORD);
			String sql = "select * from write_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WriterInfo writerInfo = WriterInfo.builder()					
						.title(rs.getString(2))
				        .writer(rs.getString(3))
				        .password(rs.getString(4))
				        .contents(rs.getString(5))
				        .date(rs.getString(6))
				        .build();
				writerInfos.add(writerInfo);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return writerInfos;
		
		
		
		
		
		
		
		
		
	}
	
	
}
