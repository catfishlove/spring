package com.example.boot10.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok의 기능을 이용해서 dto 클래스 만들기
@Builder
//lombok의 어노테이션으로 빌더 패턴을 자동생성.
//빌더 패턴을 사용하면 객체를 생성할 때 매개변수의 순서에 구애받지 않고 객체 초기화 가능
@AllArgsConstructor
//모든 필드를 매개변수로 받는 생성자를 자동으로 생성하게 함. 
//모든 필드에 대해 매개변수를 지정하여 객체를 생성할 수 있게함.
@NoArgsConstructor
//매개변수가 없는 기본 생성자를 자동으로 생성함
//프레임워크에서 객체를 초기화 할 때 사용됨. 
@Data
public class PostDto {
	private int id;
	private String title;
	private String author;
}
