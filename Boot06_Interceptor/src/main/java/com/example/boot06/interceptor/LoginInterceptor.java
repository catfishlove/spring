package com.example.boot06.interceptor;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/*
 *  - Interceptor 가 동작하는 조건
 *  
 *  1. bean 이 되어야 한다. (spring bean container 에 등록)
 *  2. Interceptor 레지스트리에 등록을 해야한다.
 */

@Component
public class LoginInterceptor implements HandlerInterceptor{
	//인터페이스를 구현하여 spring MVC의 인터셉터 기능을 사용한다. 
	//컨트롤러가 동작하기 이전(pre)에 이 메소드가 호출된다  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			//컨트롤러가 실행되기 전에 호출되는 메소드.
	//HttpServletRequest >> HTTP 요청을 처리하는 객체로 클라이언트의 요청 정보를 읽고 처리할 수 있다. 
	// HttpServletResponse >> HTTP 요청을 처리하는 객체로 클라이언트에게 응답을 보내는 역할을 한다. 
	//HttpSession >> 클라이언트의 세션 정보를 처리하는 객체로 로그인 여부를 확인하는데에 사용한다. 
			throws Exception {
		//세션 객체의 참조값을 얻어와서 
		HttpSession session=request.getSession();
		//현재 요청의 세션 객체를 얻어옴. 
		
		String id=(String)session.getAttribute("id");
		//id 라는 이름으로 저장된 값을 가져온다. 이를 통하여 사용자가 로그인 했는지 확인한다. 
		
		//만일 로그인을 하지 않았다면
		if(id == null) {
			//로그인 페이지로 리다일렉트 이동 시키고 false 를 리턴한다.
			//클라이언트를 로그인 폼으로 되돌리고, 원래 요청한 URL 을 url 파라미터로 함께 전달. 

			//원래 가려던 url 정보 읽어오기
			String url=request.getRequestURI();
			//GET 방식 전송 파라미터를 query 문자열로 읽어오기 ( a=xxx&b=xxx&c=xxx )
			String query=request.getQueryString();
			//특수 문자는 인코딩을 해야한다.
			//URL을 인코딩하여 특수문자 처리. URL의 일부분을 안전하게 전송하기 위한 과정.
			String encodedUrl=null;
			if(query==null) {//전송 파라미터가 없다면 
				encodedUrl=URLEncoder.encode(url);
			}else {
				// 원래 목적지가 /test/xxx.jsp 라고 가정하면 아래와 같은 형식의 문자열을 만든다.
				// "/test/xxx.jsp?a=xxx&b=xxx ..."
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}
			
			//3. 로그인을 하지 않았다면  /user/loginform 페이지로 리다일렉트 이동 시킨다. (HttpServletResponse)
			String cPath=request.getContextPath();
			response.sendRedirect(cPath+"/user/loginform?url="+encodedUrl);
			//클라이언트를 지정된 URL로 리다이렉트 시킨다. 
			return false;
			//로그인이 필요한 페이지로 리다이렉트 되었으니 컨트롤러 실행 X
		}
		
		return true;
		//로그인 되어있으니 정상적으로 컨트롤러 실행. 
	}
}

















