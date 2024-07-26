package com.example.boot11.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.example.boot11.dto.FileDto;

public interface FileService {
	public void getList(Model model, FileDto dto); //파일 목록을 Model 객체에 담아주는 메소드 
	public void saveFile(FileDto dto); //파일 정보를 저장하는 메소드 
	//다운로드 해줄 파일 하나의 정보 얻어오기 
	public ResponseEntity<InputStreamResource> getFileData(int num);
	//파일 삭제
	public void deleteFile(int num);
}

/*
 * getList(Model model, FileDto dto); >> 파일 목록을 가져와서 Model 객체에 저장한다.
 * model:Spring의 model 객체로 컨트롤러와 뷰 사이에서 테이터를 전달하는 역할
 * dto: 파일 목록을 가져오기위한 기준이나 매개변수가 담긴 FileDto 객체이다. 
 * 
 * model 객체에 파일 관련 데이터를 채워넣어, 보통 뷰에서 렌더링 할 수 있도록 한다. 
 * 
 * saveFile(FileDto dto); >> 파일의 정보를 저장함.
 * dto: 저장할 파일의 세부 정보가 담긴 FileDto 객체.
 * dto에 담긴 파일 데이터를 시스템이나 저장소에 저장함. 
 * 
 * getFileData(int num) >> 특정 파일의 데이터를 다운로드 하기위해 가져옴.
 * 매개변수:num >> 파일을 식별하는 번호나 ID 등의 식별자 
 * 반환값: 다운로드 할 파일의 데이터를 스트림으로 제공하는 ResponseEntity 객체
 * 동작: num으로 식별된 파일을 가져와서 다운로드 할 수 있도록 데이터를 준비함. 
 * 
 * deleteFile(int num): 
 * 목적: 파일삭제
 * 동작: num으로 식별된 파일을 시스템이나 저장소에서 삭제한다. 
 */
