package com.example.boot00.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot00.dao.PostDao;
import com.example.boot00.dto.PostDto;

@Controller("/post")
public class PostController {
	
	@Autowired
	private PostDao dao;
	
	@PostMapping("/post/update")
	public String update(PostDto dto) {
		dao.update(dto);
		return "member/update";
	}
	
	@GetMapping("/post/updateform")
	public String updateForm(int id, Model model) {
		PostDto dto=dao.getData(id);
		model.addAttribute("dto", dto);
		return "post/update";
		
	}
	
	@PostMapping("/post/insert")
	public String insert(PostDto dto) {
		dao.insert(dto);
		
		return "post/insert";
	}
	
	@GetMapping("/post/insertform")
	public String insertForm() {
	
	return "post/insertform";
	
	}
	
	@GetMapping("/post/list")
	public String list(Model model) {
		List<PostDto> list=dao.getList();
		
		model.addAttribute("list", list);
		
		return "post/list";
	}
	
	
}
