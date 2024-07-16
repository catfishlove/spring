package com.example.boot11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration //설정 클래스라고 알려준다
@EnableWebSecurity //Security 를 설정하기 위한 어노테이션
public class SecurityConfig {
	/*
	 *  매개변수에 전달되는 HttpSecurity 객체를 이용해서 우리의 프로젝트 상황에 맞는 설정을 기반으로 
	 *  만들어진 SecurityFilterChain 객체를 리턴해주어야 한다.
	 *  또한 SecurityFilterChain 객체도 스프링이 관리하는 Bean 이 되어야 한다  
	 */
	@Bean //메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	//securityFilterChain: HTTP 요청에 대한 보안 설정을 적용하는 필터체인. 
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		String[] whiteList= {"/", "/user/loginform", "/user/login_fail", "/user/expired",
					"/user/signup_form", "/user/signup", "/error", "/upload/images/*","/file/list","/file/download"};
	//사용자 인증 없이 접근할 수 없는 부분을 포함하는 코드 	
		
		httpSecurity
		.csrf(csrf->csrf.disable()) //CSRF 보호 기능을 비활성화 함.
		.authorizeHttpRequests(config ->
			config //HTTP 요청에 대한 인가 설정을 한다. 
				.requestMatchers(whiteList).permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
				.anyRequest().authenticated()
		)
		/*
		 *  .requestMatchers(whiteList).permitAll()

whiteList 배열에 포함된 URL 패턴들에 대해 인증 없이 접근을 허용하는 설정입니다.
즉, "/", "/user/loginform", "/user/login_fail" 등 whiteList 배열에 포함된 경로들은 모든 사용자가 로그인 없이도 접근할 수 있습니다.
.requestMatchers("/admin/**").hasRole("ADMIN")

/admin/ 경로 및 하위 경로에 대해서는 ADMIN 역할을 가진 사용자만 접근할 수 있도록 설정합니다.
예를 들어, /admin/dashboard, /admin/users 등의 경로는 ADMIN 역할을 가진 사용자만 접근할 수 있습니다.
.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")

/staff/ 경로 및 하위 경로에 대해서는 ADMIN 또는 STAFF 역할을 가진 사용자가 접근할 수 있도록 설정합니다.
즉, /staff/schedule, /staff/tasks 등의 경로는 ADMIN 또는 STAFF 역할을 가진 사용자만 접근할 수 있습니다.
.anyRequest().authenticated()

나머지 모든 요청에 대해서는 인증된 사용자만 접근할 수 있도록 설정합니다.
이 설정은 위에서 명시한 URL 패턴들 이외의 모든 요청에 적용됩니다.
		 * 
		 */
		.formLogin(config -> //폼 기반 로그인 설정을 함.
			config
				//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정 
				.loginPage("/user/required_loginform")
				//로그인 처리를 할때 요청될 url 설정 ( spring security 가 login 처리를 대신 해준다)
				.loginProcessingUrl("/user/login")
				//로그인 처리를 대신 하려면 어떤 파라미터명으로 username 과 password 가 넘어오는지 알려주기 
				.usernameParameter("userName")
				.passwordParameter("password")
				.successHandler(new AuthSuccessHandler()) //로그인 성공 핸들러 등록
				.failureForwardUrl("/user/login_fail")
				.permitAll() //위에 명시한 모든 요청경로를 로그인 없이 요청할수 있도록 설정 
		)
		.logout(config ->
			config
				.logoutUrl("/user/logout")//Spring Security 가 자동으로 로그아웃 처리 해줄 경로 설정
				.logoutSuccessUrl("/")//로그 아웃 이후에 리다일렉트 시킬 경로 설정
				.permitAll()
		)
		.exceptionHandling(config ->
			//403 forbidden 인 경우 forward 이동 시킬 경로 설정
			config.accessDeniedPage("/user/denied")
		)
		.sessionManagement(config -> 
			config
				.maximumSessions(1)//최대 허용 세션 갯수
				.expiredUrl("/user/expired")//허용 세션 갯수가 넘어서 로그인 해제된 경우 리다일렉트 이동시킬 경로
				//사이트에서 로그인을 할 수 있는 세션을 설정함. 
		);
		
		return httpSecurity.build();
	}
	
	//비밀번호를 암호화 해주는 객체를 bean으로 만든다.
	@Bean
	PasswordEncoder passwordEncoder() {
		//여기서 리턴해주는 객체도 bean 으로 된다.
		return new BCryptPasswordEncoder();
	}

}
