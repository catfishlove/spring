package com.example.boot06.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot06.interceptor.LoginInterceptor;

//설정과 관련된 bean
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInter;
	
	//인터셉터를 레지스트리에 등록할때 호출되는 메소드를 오버라이드 한다. 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//로그인이 인터셉트를 거치지 않을 요청목록
		String[] whiteList= {"/sub/play", "/cafe/list", "/cafe/detail"};
		//동작을 원하는 인터셉터를 registry 객체를 이용해서 등록을 한다. 
		registry.addInterceptor(loginInter)
		.addPathPatterns("/sub/*", "/cafe/*")//인터셉터가 동작할 요청 pattern
		//위 사항을 검사하되, 아래와 같은 사항들은 배제하라는 뜻. 
		.excludePathPatterns(whiteList);//인터셉터가 동ㅈ가하지 않는 (예외) pattern
		//.excludePathPatterns("/sub/play","/cafe/list", "/cafe/detail");
		//로그인을 하지 않아도 사용할 수 있음. 
		
	}
}
