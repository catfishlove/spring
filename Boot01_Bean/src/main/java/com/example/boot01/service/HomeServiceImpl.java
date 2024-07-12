package com.example.boot01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //스프링에게 이 클래스가 컴포넌트임을 알림
//스프링은 이 클래스를 검색하여 bean 으로 등록한다. 
/*
 * 기타 스프링 관련 어노테이션들의 기본 어노테이션: 
 * @Component 어노테이션은 스프링에서 다른 
 * 어노테이션들(@Service, @Repository, @Controller 등)의 기반이 되는 어노테이션입니다. 
 * 이들 어노테이션들은 모두 @Component를 확장하여 구체화된 목적을 가지고 있습니다.
 * 
 * spring이 관리하는 객체로 만드는 방법
 * 
 *  1. component scan 이 일어나는 위치에 클래스가 존재해야 한다.
 *  2. 적절한 어노테이션이 클래스에 붙어있어야한다. 
 */
public class HomeServiceImpl implements HomeService {
	/*
	 * 필요한 type 을 필드로 선언하고 @Autowired 어노테이션을 붙여 놓으면 해당 객체가 자동 주입된다.
	 */
	@Autowired
	//필드 주입 방식. 스프링 컨테이너가 해당 필트데 Drill 타입의 빈을 자동으로 주입.
	private Drill drill;
	/*
	 * Drill 타입의 필드를 선언한다. 이 필드는 @Autowired가 붙어있어
	 * Spring 이 알맞은 Drill 빈을 찾아서 주입.
	 * 
	 */
	
	@Override //HomeService 의 메소드를 구현하는 어노테이션.
	//Java 에서 상속받은 메소드를 재정의한다는 것을 명시함. 
	public void clean(String name) {
	//HomeService 인터페이스의 clean 메소드를 구현한 메소드. 
		//매개변수로 전달된 이름을 사용하여 집을 청소하는 기능 구현.
		System.out.println(name+"의 집을 청소해요!");
		
	}

	@Override
	public void wash(String name) {
		System.out.println(name+"의 빨래를 빨아요~");
		
	}

	@Override
	public void hole(String name) {
		System.out.println(name+"에 구멍을 뚫어요");
		drill.hole();//Drill 객체의 hole 메소드를 호출 
		
	}

}
