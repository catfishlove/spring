package com.example.boot11.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("fileDto") // Mapper xml 에서 FileDto type 을 간단히 줄여서 쓸수 있도록 별칭 부여하기
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

//파일 업로드 다운로드 삭제등과 같은 파일관련 작업에서 사용되는 데이터 전달 객체
public class FileDto {
	private int num;
	private String writer;
	private String title;
	//원본 파일명
	private String orgFileName;
	//파일 시스템에 저장된 파일명
	private String saveFileName;
	//파일의 크기 
	private long fileSize;
	private String regdate;
	//페이징 처리를 위한 필드
	private int pageNum=1; //페이지 번호 기본값은 1을 가지도록 한다  
	private int startRowNum;
	private int endRowNum;
	//파일 업로드 처리를 하기 위한 필드
	private MultipartFile myFile;
	//검색 키워드 관련
	private String condition=""; //검색 조건이 없는 경우 null 이 출력되는 걸 방지하기 위해 빈 문자열을 기본값으로 설정
	private String keyword=""; //검색 조건이 없는 경우 null 이 출력되는걸 방지하기 위해 빈 문자열을 기본값으로 설정
}

/*
 * @Alias("fileDto"): MyBatis에서 해당 객체를 XML 매퍼 파일에서 사용할 때 간단한 별칭으로 사용할 수 있도록 지정하는 어노테이션입니다.

@Builder, @AllArgsConstructor, @NoArgsConstructor, @Data: Lombok 어노테이션을 사용하여 자동으로 생성자, 빌더, 게터/세터 등을 생성합니다.

private int num;: 파일의 번호를 나타내는 필드입니다.

private String writer;: 파일을 작성한 작성자를 나타내는 필드입니다.

private String title;: 파일의 제목을 나타내는 필드입니다.

private String orgFileName;: 업로드된 원본 파일명을 저장하는 필드입니다.

private String saveFileName;: 파일 시스템에 저장된 실제 파일명을 저장하는 필드입니다.

private long fileSize;: 파일의 크기를 저장하는 필드입니다.

private String regdate;: 파일이 등록된 날짜를 저장하는 필드입니다.

private int pageNum = 1;: 페이징 처리를 위한 페이지 번호를 나타내는 필드입니다. 기본값은 1로 설정되어 있습니다.

private int startRowNum;: 데이터베이스 조회에서 사용될 시작 행 번호를 나타내는 필드입니다.

private int endRowNum;: 데이터베이스 조회에서 사용될 끝 행 번호를 나타내는 필드입니다.

private MultipartFile myFile;: 파일 업로드 처리를 위해 사용되는 Spring의 MultipartFile 객체를 저장하는 필드입니다.

private String condition = "";: 검색 조건을 나타내는 필드입니다. 기본값으로 빈 문자열을 가지고 있습니다.

private String keyword = "";: 검색 키워드를 나타내는 필드입니다. 기본값으로 빈 문자열을 가지고 있습니다.
 * 
 */


