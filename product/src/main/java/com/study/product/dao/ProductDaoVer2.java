package com.study.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.product.config.DBConnectionMgr;
import com.study.product.vo.ProductVo;

public class ProductDaoVer2 {
	private static ProductDaoVer2 instance;
	private DBConnectionMgr pool;
	
	private ProductDaoVer2() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static ProductDaoVer2 getInstance() {
		if(instance == null) {
			instance = new ProductDaoVer2();
		}
		return instance;
	}
	
	public List<ProductVo> getProductList() {
		List<ProductVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from Product_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVo productvo = ProductVo.builder()
						.productId(rs.getInt(1))
						.productName(rs.getString(2))
						.productPrice(rs.getInt(3))
						.prodcutSize(rs.getString(4))
						.build();
				list.add(productvo);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		
		
		return list;
	}
	
	
	public ProductVo findProductByProductName(String productName) {
		ProductVo productVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from Product_tb where product_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productVo = ProductVo.builder()
						.productId(rs.getInt(1))
						.productName(rs.getString(2))
						.productPrice(rs.getInt(3))
						.prodcutSize(rs.getString(4))
						.build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return productVo;
	}
	
	
	public int save(ProductVo productVo) {
		int successCount = 0;
		// 이쉐키들이 먼지 물어보기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 물어보기 !!
			con = pool.getConnection();
			String sql  = "insert into Product_tb values(0, ?, ?, ?)";
			pstmt= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,  productVo.getProductName());
			pstmt.setInt(2,  productVo.getProductPrice());
			pstmt.setString(3,  productVo.getProdcutSize());
			
			//executeUpdate() 출력 성공 건수
			successCount = pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productVo.setProductId(rs.getInt(1));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return successCount;
	}
	
	
	
}
