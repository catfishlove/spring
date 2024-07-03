package com.example.boot02.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot02.dto.MemberDto;
/*
 * 클라이언트위 요청을 처리할 컨트롤러를 정의하고 bean 으로 만들기
 */
@Controller
public class HomeController {
	
	/*
	 * @ResponsBody: 이 어노테이션은 해당 메서드가 반환하는 데이터가 
	 * HTTP 응답의 본문으로 사용됨을 나타냅니다. 이 경우 메서드가 반환하는 문자열이
	 * 응답의 본문으로 전송됩니다. 
	 * 
	 * @GetMapping: 이 어노테이션은 GET 요청을 처리하는 핸들러 메소드.
	 * 클라이언트가 /fortune 경로로 GET 요청을 보내면 메소드가 실행되어 처리한다. 
	 * 
	 */
	
	@ResponseBody
	@GetMapping("/members")//members의 메소드를 보여주는 코드 
	public List<MemberDto> members(){
		
		List<MemberDto> members=new ArrayList<>();
		//members라는 이름의 ArrayList 객체를 생성. 
		//이 리스트는 MemberDto 객체들을 담을 예정.
		members.add(new MemberDto(1, "김구라", "노량진"));
		//MemberDto 클래스의 인스턴스를 생성하는 코드. 
		members.add(new MemberDto(2, "해골", "행신동"));
		members.add(new MemberDto(3, "원숭이", "동물원"));
		//List<MemberDto> 객체를 리턴하면 [{}, {}, {},...] 형식의 JSON 문자열이 응답된다.
		return members;
		//members 리스트 객체를 메소드의 반환값으로 지정 
		
		/*
		 * JSON(JavaScript Object Notation)은 데이터를 효율적으로 표현하고 전송하기 위한 
		 * 경량의 데이터 교환 형식입니다. 
		 * JSON은 사람과 기계 모두 이해하기 쉬운 텍스트 기반의 데이터 포맷으로, 
		 * 다음과 같은 특징을 가집니다:

			가독성: 텍스트 기반이기 때문에 사람이 쉽게 읽고 쓸 수 있습니다.

			간결성: 데이터를 효율적으로 표현할 수 있어서 데이터 크기를 작게 유지할 수 있습니다.

			독립성: 언어나 플랫폼에 구애받지 않고 사용할 수 있습니다. 
			주로 JavaScript 언어와 연관되어 있지만, 다양한 프로그래밍 언어에서 지원하고 있습니다.
		 * 
		 */
	}
	
	
	@ResponseBody
	@GetMapping("/friends")
	public List<String> friends(){
		List<String>names=new ArrayList<>();
		//String은 리스트에 포함될 요소의 타입.
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		return names;
		
	}
	
	/*
	 * @ResponseBody 어노테이션이 붙어있는 메소드에서 Dto객체를 리턴하면
	 * Dto에 담긴 정보가 JSON 문자열로 변환되어서 클라이언트에게 응답된다.
	 * 
	 */
	
	@ResponseBody
	@GetMapping("/member2")
	public MemberDto member2() {
		MemberDto dto=new MemberDto();
		dto.setNum(1);
		// dto 객체의 setNum 메소드를 호출하여 멤버의 번호를 1번으로 설정한다. 
		dto.setName("김구라");
		// dto 객체의 setNum 메소드를 호출하여 멤버의 이름을 김구라로 설정한다. 
		dto.setAddr("노량진");
		// dto 객체의 setNum 메소드를 호출하여 멤버의 주소를 노량진으로 설정한다. 
		return dto;
		//dto 객체를 메소드의 반환값으로 지정함. 
	}
	
	/*
	 * @ResponseBody 어노테이션이 붙어있는 메소드에서 Map객체를 리턴하면
	 * Map에 담긴 정보가 JSON 문자열로 변환되어서 클라이언트에게 응답된다.
	 * 
	 */

	
	@ResponseBody
	@GetMapping("/member")
	public Map<String, Object> member() {
	    // DB에서 읽어온 회원 한 명의 정보라고 가정하자
	    Map<String, Object> map = new HashMap<>();
	    map.put("num", 1); // 회원 번호
	    map.put("name", "김구라"); // 회원 이름
	    map.put("isMan", true); // 회원 성별
	    
	    return map; 
	    // Map 객체를 반환함. 이 값은 JSON 형태로 변환되어 클라이언트에게 전송됨
	}
	
	@ResponseBody
	@GetMapping("/hello") //클라이언트가 "/hello" 경로로 요청을 하면 이 메소드가 실행된다. 
	public String hello() {
		
		// String type 을 리턴하면서 메소드에 @ResponseBody 어노테이션을 붙여놓으면 
		// 여기서 리턴한 문자열이 클라이언트의 웹브라우저에 그대로 출력된다. 
		return "Nice to meet you!";
	}
	
	@ResponseBody 
	// 이 메서드가 반환하는 값은 HTTP 응답의 본문(body)으로 사용됨을 나타냄
	@GetMapping("/fortune") 
	// GET 방식으로 /fortune 경로에 접근할 때 이 메서드가 실행됨
	
	public String fortune() {
	    return "내일은 비가 내려요."; 
	    // "내일은 비가 내려요." 문자열을 반환함
	}
	    /*
	     * public String fortune(): String 을 반환함.
	     * 반환된 문자열 "내일은 비가 내려요" 는 HTTP응답의 본문으로 사용됨. 
	     * 
	     */
	
}
