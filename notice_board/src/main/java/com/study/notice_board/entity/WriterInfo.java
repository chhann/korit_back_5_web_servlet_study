package com.study.notice_board.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WriterInfo {
	private String title;
	private String writer;
	private String password;
	private String contents;
	private String date;
}
