package com.example.boot11.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.example.boot11.dto.GalleryDto;

public interface GalleryService {
	//갤러리에 사진 추가
	public void addToGallery(GalleryDto dto);
	//model 객체에 갤러리 정보 하나 담기
	public void selectOne(Model model, int num);
	//pagenum에 해당하는 갤러리 목록을 model 객체에 담기
	public void selectPage(Model model, int pageNum);
	//num 에 해당하는 갤러리 정보 삭제
	public void deleteOne(int num);

}
