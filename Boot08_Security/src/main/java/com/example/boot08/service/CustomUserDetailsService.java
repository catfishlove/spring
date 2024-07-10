package com.example.boot08.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.boot08.dto.UserDto;

// bean 으로 만들기 위한 어노테이션 
@Service
//이 클래스가 spring 의 bean 으로 등록됨을 나타냄. Spring이 이 클래스를 컴포넌트 스캔을 통해 찾아
//bean 으로 만들어 관리.
public class CustomUserDetailsService implements UserDetailsService{
	
	//(UserDetailsService) << Spring Security에서 제공하는 인터페이스. 
	//사용자의 로그인 처리시 호출하는 메소드 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loadUserByUsername << spring security 가 로그인 요청을 처리할때 호출되는 메소드.
		//사용자가 입력한 유저 이름이 전달됨. 
		//원래는 DB 에서 dao 를 이용해 username 에 해당하는 사용자정보(UserDto) 를 얻어와야 한다.
		//지금은 sample 데이터를 만들어서 사용하기
		
		// 실제 DB 에는 ROLE_XXX 형식으로 저장이 되어 있어야한다 
		String role="";
		if(username.equals("kimgura")) {
			role="ROLE_USER";
		}else if(username.equals("batman")) {
			role="ROLE_STAFF";
		}else if(username.equals("superman")){
			role="ROLE_ADMIN";
		}
		//각 사용자에 따라서 역할(role)을 설정함. 각각의 사용자에 대해 권한을 부여함 (USER,STAFF,ADMIN) << 역할
		//비밀번호를 암호화 하기 위한 객체 
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		//BCryptPasswordEncoder를 사용하여 비밀번호를 암호화 
		//DB 에는 암호화된 비밀번호가 저장되어 있다고 가정하자
		String encodedPwd=encoder.encode("1234");
		
		//Sample UserDto 객체 만들기(원래는 DB 에서 읽어온 데이터)
		//UserDto 객체를 생성하여 사용자의 정보를 담기. 
		UserDto dto=UserDto.builder()
					.userName(username)
					.password(encodedPwd)
					.email("aaa@naver.com")
					.role(role)
					.build();
		
		//사용자의 역할을 기반으로 GrantedAuthority 객체 생성.
		//권한 목록을 List 에 담아서  (지금은 1개 이지만)
		//이 정보를 기반으로 사용자의 접근 권한을 판단.
		List<GrantedAuthority> authList=new ArrayList<>();
		authList.add(new SimpleGrantedAuthority(dto.getRole()));
		
		//UserDetails 객체를 생성해서 
		//UserDetails는 spring security 에서 사용자의 상세정보를 나타내는 인터페이스
		//user 클래스를 사용하여 UserDetails 객체를 생성. 
		UserDetails ud=new User(dto.getUserName(), dto.getPassword(), authList);
		//리턴해준다.
		return ud;
	}

}























