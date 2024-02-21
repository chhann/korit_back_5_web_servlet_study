package com.study.notice_board.dto;

import com.study.notice_board.vo.BoardVo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InsertBoardReqDto {
	private String title;
	private String writer;
	private String password;
	private String contents;
	private String date;
	
	public BoardVo toVo() {
		return BoardVo.builder()
				.title(title)
				.writer(writer)
				.password(password)
				.contents(contents)
				.date(date)
				.build();
	}
	
}


