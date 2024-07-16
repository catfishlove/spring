package com.example.boot11.repository;

import java.util.List;

import com.example.boot11.dto.FileDto;

public interface FileDao {
	public void insert(FileDto dto); //파일정보 저장
	public FileDto getData(int num); //파일하나의 정보 리턴하기
	public void delete(int num); //파일 하나의 정보 삭제하기
	public List<FileDto> getList(FileDto dto); //pageNum 에 해당하는 파일 목록 리턴하기 
	public int getCount(FileDto dto); //검색조건에 맞는 글의 갯수를 리턴하는 메소드
}

/*
public void insert(FileDto dto);: 파일 정보를 저장하는 메소드입니다. FileDto 객체를 전달받아 데이터베이스에 파일 정보를 저장하는 역할을 합니다.

public FileDto getData(int num);: 파일 번호에 해당하는 파일 정보를 가져오는 메소드입니다. 파일 번호를 매개변수로 받아서 그에 해당하는 파일 정보를 FileDto 객체로 리턴합니다.

public void delete(int num);: 파일 번호에 해당하는 파일 정보를 삭제하는 메소드입니다. 파일 번호를 매개변수로 받아서 그에 해당하는 파일을 데이터베이스에서 삭제합니다.

public List<FileDto> getList(FileDto dto);: 검색 조건과 페이지 번호에 해당하는 파일 목록을 가져오는 메소드입니다. FileDto 객체를 매개변수로 받아서 그 안에 설정된 검색 조건과 페이지 번호에 맞는 파일 목록을 List<FileDto> 형태로 리턴합니다.

public int getCount(FileDto dto);: 검색 조건에 맞는 파일의 총 개수를 가져오는 메소드입니다. FileDto 객체를 매개변수로 받아서 그 안에 설정된 검색 조건에 맞는 파일의 총 개수를 정수 형태로 리턴합니다.

*/