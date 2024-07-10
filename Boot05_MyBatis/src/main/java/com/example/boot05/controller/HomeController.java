package com.example.boot05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	//최상위 경로 요청이 왔을때
	@GetMapping("/")
	public String home() {
		
		// templates/home.html thymeleaf 페이지를 응답하기
		return "home";
	}
	
	/*
	 * 주어진 코드는 Spring MVC 프레임워크에서 컨트롤러 역할을 하는 클래스입니다. 각 부분을 설명해보겠습니다:

@Controller: 이 어노테이션은 Spring MVC에서 컨트롤러임을 나타내는 것입니다. 스프링에게 이 클래스가 웹 요청을 처리하는 컨트롤러임을 알려줍니다.

@GetMapping("/"): 이 어노테이션은 HTTP GET 메소드를 처리하는 핸들러 메소드를 지정합니다. "/" 경로로 들어오는 GET 요청을 처리하는 메소드입니다.

public String home() { ... }: home() 메소드는 @GetMapping("/") 어노테이션에 의해 매핑되어 "/" 경로로 들어오는 GET 요청을 처리합니다. 메소드는 다음을 수행합니다:

반환 타입은 String으로 지정되어 있습니다. 이는 보통 뷰의 이름을 반환한다는 의미입니다.
메소드 내에서는 "home"을 반환하고 있습니다. 이는 Thymeleaf 템플릿 엔진을 사용하여 렌더링될 home.html 페이지의 이름을 나타냅니다.
return "home";: 이 코드는 "home" 문자열을 반환합니다. Spring MVC는 이 문자열을 기반으로 실제 렌더링할 뷰의 이름을 결정하게 됩니다. 일반적으로 Spring의 ViewResolver는 이 뷰 이름을 템플릿 파일의 경로로 해석하고, 해당 템플릿을 클라이언트(브라우저)에게 응답으로 보냅니다.
	 * 
	 */
}
