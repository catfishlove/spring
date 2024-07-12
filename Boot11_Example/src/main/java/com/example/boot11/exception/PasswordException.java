package com.example.boot11.exception;

public class PasswordException extends RuntimeException{
	//생성자
	public PasswordException(String message) {
		super(message); //생성자의 전달 되는 예외 메세지를 부모 생성자에게 넘겨준다
		// 부모생성자에 넘긴예외 메세지는 이 객체의 .getMessage() 메소드를 호출하면 리턴
	}

}
