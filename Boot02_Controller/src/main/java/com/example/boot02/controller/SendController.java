package com.example.boot02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot02.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SendController {
	/*
	 * 컨트롤러 메소드 안에서 HttpServletReauest, HttpServletResPonse, HttpSession 등의 객체가 필요하면 
	 * 매개변수에 선언하면 된다
	 * 
	 * 선언만 하면 spring 프레임 워크가 알아서 해당 객체의 참조값을 매개변수에 전달해준다. 
	 */
	
	@ResponseBody
	@PostMapping("/send")
	public String send(HttpServletRequest request) {
		//요청 파라미터 추출하기
		String msg=request.getParameter("msg");
		//객체에서 "msg"라는 이름의 파라미터 값을 추출합니다. 
		//클라이언트가 POST 방식으로 전송한 데이터 중에서 "msg"에 해당하는 값을 가져옵니다.
		System.out.println("msg:"+msg);
		//콘솔에 추출된 msg 파라미터 값 출력. 
		
		return "/send okay!";
	}
		
		// 전송되는 파라미터명과 동일하게 매개변수를 선언하면 자동으로 추출되어서 매개변수에 전달된다.
		@ResponseBody
		@PostMapping("/send2")
		public String send2(String msg) {
		
			System.out.println("msg:"+msg);
			
			return "/send2 okay!";
	}
		
		// 전송되는 파라미터명과 동일하게 매개변수를 선언하면 자동으로 추출되어서 매개변수에 전달된다.
		@ResponseBody
		@PostMapping("/send3")
		public String send3(@RequestParam(defaultValue = "0") int num, 
				@RequestParam (defaultValue = "아무게")String name)
		// @RequestParam 어노테이션을 사용하여 http 요청의 파라미터를 매개변수로 받음.
		
		{
				  
			System.out.println(String.format("번호:%d 이름:%s", num, name));
			/*
			 * num: 정수 타입의 파라미터로, 기본값(defaultValue)을 0으로 설정합니다.
				name: 문자열 타입의 파라미터로, 기본값(defaultValue)을 "아무게"로 설정합니다.
			 */
			//콘솔에 전달받은 num 과 name 파라미터 값을 포맷하여 출력합니다. 
			return "/send3 okay!";
	}
		/*
		 * 전송되는 파라미터명과 동일한 필드명을 가지고 있는 Dto type 으로 매개 변수를 선언하면 
		 * Dto 객체가 자동으로 생성되고 생성된 객체에 파전송된 파라미터 값이 담은다음 그 참조값을
		 * 매개변수에 전달해준다. 
		 * 
		 */
		@ResponseBody
		@PostMapping("/send4")
		public String send4(MemberDto dto) {
				  
			System.out.println(String.format("번호:%d 이름:%s", dto.getNum(), dto.getName()));
			
			return "/send4 okay!";
	}
}