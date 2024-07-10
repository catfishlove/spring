package com.example.boot05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot05.dao.MemberDao;
import com.example.boot05.dto.MemberDto;

@Controller
public class MemberController {
	//spring bean container 로부터 MemberDao type 주입(DI) 받기
	@Autowired
	//스프링 프레임 워크에서 MemberDao 타입의 빈을 자동으로 주입받음. 
	private MemberDao dao;
	//MemberDao의 인터페이스 구현체를 주입받음 
	
	@PostMapping("/member/update")
	//Post 방식으로 /member/update 요청을 처리하는 메소드 
	public String update(MemberDto dto) {//수정할 회원의 정보가 자동으로 추출되어서 MemberDto 객체에 담겨서 전달된다. 
		dao.update(dto);
		//MemberDto 객체를 전달받아서 DB에서 해당 회원의 정보를 업데이트 
		return "member/update";
		//업데이트 완료 후 update.html 페이지로 이동함
	}
	
	@GetMapping("/member/updateform")
	//Get 방식으로 /member/update 요청을 처리하는 메소드 
	public String updateForm(int num, Model model) { 
		// 수정할 회원의 번호가 자동으로 추출되어서 num 매개 변수에 전달된다.
		//수정할 회원의 번호를 이용해서 회원정보를 얻어온다. 
		MemberDto dto=dao.getData(num);
		//얻어온 회원 정보를 Model 객체에 담는다. 
		model.addAttribute("dto", dto);
		//회원정보 수정폼을 보여주는 페이지로 이동함 
		return "member/updateform";
	}
	
	@GetMapping("/member/delete")
	//Get 방식으로 /member/delete 요청을 처리하는 메솓, 
	public String delete(int num) { 
		// 삭제할 번호가 자동으로 추출되어서 num 매개 변수에 전달된다.
		//MemberDao 객체를 이용해서 삭제하고
		//num을 이용하여 DB에서 해당 회원의 번호를 삭제한다. 
		dao.delete(num);
		// 회원 목록보기로 리다일렉트 이동하라는 응답하기 
		//회원 삭제 이후 회원 목록 페이지로 리다이렉트 한다. 
		return "redirect:/member/list";
		
		
	}
	
	@PostMapping("/member/insert")
	public String insert(MemberDto dto) {
		//요청 파라미터가 추출되어서 MemberDto 객체에 담겨서 전달된다 
		//MemberDao 객체를 이용해서 DB에 저장하고 
		dao.insert(dto);
		//응답하기
		return "member/insert";
	}
	@GetMapping("/member/insertform")
	public String insertForm() {
		
		return "member/insertform";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		//DB에서 회원 목록(List<MemberDto>)을 얻어와서 Model 객체에 담고
		List<MemberDto> list=dao.getList();		
		//DB에서 전체 회원 목록을 조회하여 List 에 저장함
		model.addAttribute("list", list);
		//조회된 회원 목록을 Model 에 추가하여 list.html 페이지에 전달함.
		// 타임리프 view 페이지를 이용해서 응답하기
		return "member/list";
	}
}
