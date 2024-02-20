package com.study.notice_board.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.study.notice_board.entity.WriterInfo;

public class RequestUtil {
	
	public static String getJsonData(HttpServletRequest request) throws IOException {
		String requestJsonData = null;
		
		StringBuilder builder = new StringBuilder(); 
		
		String readData = null;
		
		BufferedReader reader = request.getReader();
		
		
		// 가져온 json 문자열 담음
		while((readData = reader.readLine()) != null) {
			builder.append(readData);
		}
		
		requestJsonData = builder.toString();
		
		return requestJsonData;
	}
	
	
	public static <T> T convertJsonData(HttpServletRequest request, Class<T> classOfT) throws IOException {
		String requestJsonData = null;
		
		StringBuilder builder = new StringBuilder(); 
		
		String readData = null;
		
		BufferedReader reader = request.getReader();
		
		
		// 가져온 json 문자열 담음
		while((readData = reader.readLine()) != null) {
			builder.append(readData);
		}
		
		requestJsonData = builder.toString();
		
		Gson gson = new Gson();
		
		//Json -> Entity 객체로
		return gson.fromJson(requestJsonData, classOfT);
		
		
	}
	
}
