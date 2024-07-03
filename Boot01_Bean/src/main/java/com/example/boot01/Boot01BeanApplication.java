package com.example.boot01;

import org.springframework.boot.SpringApplication;

/*
 *  이 클래스가 존재하는 패키지 (com.example,boot01) 또는 하위 패키지를 모두 scan 해서
 *  spring 이 관리할 객체를 생성하도록 한다. (@SpringBootApplication 어노테이션의 기능)
 * 
 */
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.boot01.service.HomeService;

@SpringBootApplication
public class Boot01BeanApplication {

	public static void main(String[] args) {
		 // SpringApplication을 사용하여 
		//Boot01BeanApplication 클래스를 실행하고, 
		//ApplicationContext를 얻는다.
		
		/*
		 * spring bean container 에서 HomeService type 을 찾아서 리턴 받는다. 
		 * new 에서 받아쓰지 않고 객체에서 직접 받아서 쓰는 구조에 주목하기.
		 * 
		 */
		ApplicationContext ctx=SpringApplication.run(Boot01BeanApplication.class, args);
		
		HomeService service=ctx.getBean(HomeService.class);
		//원숭이의 집을 청소하려면?
		service.clean("원숭이");
		//주뎅이의 빨래를 하려면?
		service.wash("주뎅이");
		//바닥에 구멍을 뚫으려면?
		service.hole("바닥");
	}

}
