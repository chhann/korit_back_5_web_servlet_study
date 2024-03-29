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
import com.study.notice_board.dto.InsertBoardReqDto;
import com.study.notice_board.entity.WriterInfo;
import com.study.notice_board.service.BoardService;
import com.study.notice_board.utils.RequestUtil;
import com.study.notice_board.utils.ResponseEntity;


@WebServlet("/write")
public class InsertWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService;
    
    public InsertWrite() {
        super();
        boardService = BoardService.getInstance();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InsertBoardReqDto ReqDto = RequestUtil.convertJsonData(request, InsertBoardReqDto.class);

		WriteDataDao writeDataDao = WriteDataDao.getInstance();
		
		ResponseEntity.ofJson(response, 201, boardService.addBoard(ReqDto));
		
		
	}

}
