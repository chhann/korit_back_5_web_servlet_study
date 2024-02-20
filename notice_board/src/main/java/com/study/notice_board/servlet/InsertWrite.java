package com.study.notice_board.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.notice_board.dao.WriteDataDao;
import com.study.notice_board.entity.WriterInfo;
import com.study.notice_board.utils.RequestUtil;


@WebServlet("/write")
public class InsertWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertWrite() {
        super();

    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WriterInfo writerInfo = RequestUtil.convertJsonData(request, WriterInfo.class);
		
		
		
		Gson gson = new Gson();
		
		//Json -> Entity 객체로
//		WriterInfo writerInfo = gson.fromJson(requestJsonData, WriterInfo.class);
		
		WriteDataDao writeDataDao = WriteDataDao.getInstance();
		
		int successCount = writeDataDao.saveWriterInfo(writerInfo);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답데이터");
		responseMap.put("successCount", successCount);
		
		response.setContentType("application/json");
		
		
		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
		
		
	}

}
