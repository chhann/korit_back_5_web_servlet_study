package com.study.notice_board.vo;

import com.study.notice_board.dto.InsertBoardRespDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class BoardVo {
	private int id;
	private String title;
	private String writer;
	private String password;
	private String contents;
	private String date;
	
	public InsertBoardRespDto toInsertDto(int successCount) {
		return InsertBoardRespDto.builder()
				.successCount(successCount)
				.id(id)
				.title(title)
				.writer(writer)
				.password(password)
				.contents(contents)
				.date(date)
				.build();
	}
	
}
