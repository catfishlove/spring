package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot11.dto.FileDto;
import com.example.boot11.service.FileService;


//각 메소드는 주로 매핑과 연결 되어있어 클라이언트의 요청에 따라 적절한 서비스 메소드를 호출, 결과를 다시 클라이언트에게 응답
//파일의 업로드, 다운로드, 삭제 등의 파일 관련 기능을 처리하고 
//각 요청에 대한 처리를 담당하는 FileService 와 협력하여 작업을 수행함. 
@Controller
public class FileController {
	
	@Autowired FileService service;// 의존성 주입
	
	@GetMapping("/file/delete")
	public String delete(int num) {//삭제할 파일번호가 전달된다.
		//주어진 파일의 번호를 사용하여 파일을 삭제 
		service.deleteFile(num);
		//삭제 후 파일 목록 페이지로 리다이렉트 함. 
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(int num){ //다운로드할 파일번호가 전달된다.
		//다운로드 할 파일 번호를 전달받아 서비스 계층을  통해 파일 데이터를 응답받음. 
		//ResponseEntity 리턴을 통해 파일 데이터를 응답함
		return service.getFileData(num);
	}
	
	@PostMapping("/file/upload")
	public String upload(FileDto dto) {
		//업로드 된 파일 정보를 담은 FileDto를 전달받아 서비스 계층을 통해 파일을 저장한다. 
		service.saveFile(dto);
		//파일 업로드 완료 후 업로드 페이지로 이동.
		return "file/upload";
	}
	
	@GetMapping("/file/upload_form")
	public String uploadForm() {
		//파일 업로드 폼을 보여주는 페이지로 이동. 
		return "file/upload_form";
	}
	/*
	 * FileDto 에는 pageNum, condition, keyword 값이 담길수도 있다. 
	 * (GET 방식 파라미터 값이 넘어오면 담긴다)
	 * 
	 */
	@GetMapping("/file/list")
	public String list(Model model, FileDto dto) {
		//파일 목록을 조회하는 페이지로 이동. 페이지 번호와 검색조건을 담은 FileDto를 받아
		//서비스 계층을 통해 파일 목록을 조회하고 Model 에 담아서 전달한다.
		//FileDto에는 페이지 번호, 검색 조건, 키워드가 담겨올 수 있다. 
		//서비스 객체에 Model 의 참조값을 전달해서 파일 목록이 Model 객체에 담기도록 한다.
		service.getList(model, dto);
		// template 페이지에서 파일 목록 응답하기 
		//파일 목록을 보여주는 페이지로 이동.
		return "file/list";
	}
}