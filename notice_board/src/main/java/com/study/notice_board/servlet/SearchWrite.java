package com.study.notice_board.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.notice_board.dao.WriteDataDao;
import com.study.notice_board.entity.WriterInfo;


@WebServlet("/write/view")
public class SearchWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchWrite() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WriteDataDao writeDataDao =  WriteDataDao.getInstance();
		List<WriterInfo> writeInfos = writeDataDao.getWriteInfoListAll();
		

		
		Gson gson = new Gson();
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("data", writeInfos);
		
		response.setStatus(200);
		response.setContentType("appliction/json");
		response.getWriter().println(gson.toJson(responseMap));
		

	}

}
