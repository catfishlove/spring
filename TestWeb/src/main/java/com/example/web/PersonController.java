package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//클라이언트의 요청을 처리할 컨트롤러 만들기
@Controller // 컨트롤러 역할을 할 수 있는 bean으로 만들어준다.
public class PersonController {

	//특정 경로 요청을 처리할때 메소드 만들기
	@ResponseBody //리턴하는 데이터를 클라이언트에게 출력하도록 전달하는 어노테이션
	@GetMapping ("/person") //Get 방식 /person 요층이 들어왔을 때 이 메소드가 호출되도록
		public String person() {
			return "오늘의 임무는 김구라입니다";
	}
}
