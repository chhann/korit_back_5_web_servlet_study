package com.study.notice_board.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InsertBoardRespDto {
	private int successCount;
	private int id;
	private String title;
	private String writer;
	private String password;
	private String contents;
	private String date;
}
