package com.example.boot08;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Boot08SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot08SecurityApplication.class, args);
		//운영 체제의 실행환경
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd /c start chrome. exe https://localhost:8888/boot08");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
