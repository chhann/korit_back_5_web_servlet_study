package com.study.product.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.study.product.dao.ProductDao;
import com.study.product.dao.ProductDaoVer2;
import com.study.product.dto.InsertProductReqDto;
import com.study.product.dto.InsertProductRespDto;
import com.study.product.dto.SearchProductRespDto;
import com.study.product.vo.ProductVo;

public class ProductService {
	private static ProductService instance;
	private ProductDaoVer2 productDaoVer2;
	
	private ProductService() {
		productDaoVer2 = ProductDaoVer2.getInstance();
	}
	
	public static ProductService getInstance() {
		if(instance == null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public boolean isDuplicatedProductName(String productName) {
		return 	productDaoVer2.findProductByProductName(productName) != null;
	}

	public InsertProductRespDto addProduct(InsertProductReqDto insertProductReqDto) {
		ProductVo productvo = insertProductReqDto.toVo();
		
		int successCount = productDaoVer2.save(productvo);
		
		return productvo.toInsertDto(successCount);
	}
	
	public List<SearchProductRespDto> searchProduct() {
		List<SearchProductRespDto> searchProductRespDtos = new ArrayList<>();
		
		List<ProductVo> productVos = productDaoVer2.getProductList();
		
		for(ProductVo productVo : productVos) {
			searchProductRespDtos.add(productVo.toSearchDto());
		}
		
		return searchProductRespDtos;
		

//		return productDaoVer2.getProductList().stream()
//				.map(vo -> vo.toSearchDto())
//				.collect(Collectors.toList());
		
//		return productDaoVer2.getProductList().stream()
//				.map(ProductVo::toSearchDto)
//				.collect(Collectors.toList());
	}
	
}
