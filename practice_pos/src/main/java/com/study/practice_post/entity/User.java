package com.study.practice_post.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
	private String name;
	private int age;
}
