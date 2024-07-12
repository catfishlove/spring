package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.boot11.dto.FileDto;
import com.example.boot11.service.FileService;

@Controller
public class FileController {
	
//	@Autowired private FileService service;

	@GetMapping("/file/list")
	public String update(FileDto dto) {
		
		return "file/list";
	}
}
