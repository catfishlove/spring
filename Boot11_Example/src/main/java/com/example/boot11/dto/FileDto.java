package com.example.boot11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {
	
	private int num;            // 파일 번호
	private String writer;      // 로그인된 사용자의 아이디 (작성자)
	private String title;       // 파일 제목
	private String orgFileName; // 원본 파일명 (업로드된 파일의 실제 이름)
	private String saveFileName;// 저장된 파일명 (서버에 저장된 파일 이름)
	private long fileSize;      // 파일 크기
	private String regdate;     // 등록일자
	
}
