package com.study.notice_board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.notice_board.config.DB;
import com.study.notice_board.entity.WriterInfo;
import com.study.notice_board.vo.BoardVo;

public class WriteDataDao {
	private static WriteDataDao instance;
	
	private WriteDataDao() {}
	
	public static WriteDataDao getInstance() {
		if(instance == null) {
			instance = new WriteDataDao();
		}
		return instance;
	}
	
	public int saveWriterInfo(BoardVo boardVo) {
		Connection con  = null;
		PreparedStatement pstmt = null;
		int successConut = 0;
		
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(DB.URL, DB.USERNAME, DB.PASSWORD);
			String sql = "insert into write_tb(write_title, write_writer, write_password, write_contents, write_date) values(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getWriter());
			pstmt.setString(3, boardVo.getPassword());
			pstmt.setString(4, boardVo.getContents());
			pstmt.setString(5, boardVo.getDate());
			
			successConut = pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				boardVo.setId(rs.getInt(1));
			}
			
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
