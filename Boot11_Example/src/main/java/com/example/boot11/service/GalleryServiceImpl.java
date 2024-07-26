package com.example.boot11.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot11.dto.GalleryDto;
import com.example.boot11.exception.NotOwnerException;
import com.example.boot11.repository.GalleryDao;

@Service
public class GalleryServiceImpl implements GalleryService{
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=8;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	

	@Autowired private GalleryDao dao;
	
	// custom.properties 파일에 설정된 파일을 저장할 위치 읽어오기
	@Value("${file.location}")
	private String fileLocation;
	
	@Override
	public void addToGallery(GalleryDto dto) {
		/*
		 * 컨트롤러에서 전달한 GalleryDto 에는 String caption 과 MultipartFile image 정보만 들어 있다.
		 */
		//1. 업로드된 파일 저장
		//저장할 파일의 이름 겹치지 않는 유일한 문자열로 얻어내기
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기 
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			//업로드된 파일을 이동시킬 목적지 File 객체
			File f=new File(filePath);
			//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
			dto.getImage().transferTo(f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//2. 로그인된 사용자(userName) 읽어오기
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		//3. GalleryDto 에 추가 정보를 담고
		dto.setSaveFileName(saveFileName);
		dto.setWriter(userName);
		//4. DB 에 저장하기
		dao.insert(dto);
		
	}

	@Override
	public void selectOne(Model model, int num) {
		//num 에 해당하는 갤러리 정보를 얻어와서  
		GalleryDto dto=dao.getData(num);
		//Model 객체에 "dto" 라는 키값으로 담는다. 
		model.addAttribute("dto", dto);
	}

	@Override
	public void selectPage(Model model, int pageNum) {
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum-1) * PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;
		
		//startRowNum 과 endRowNum  을 GalleryDto 객체에 담고
		GalleryDto dto = new GalleryDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//GalleryDao 객체를 이용해서 회원 목록을 얻어온다.
		List<GalleryDto> list = dao.getList(dto);
	   
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
	   
		//전체 row 의 갯수
		int totalRow = dao.getCount();
		//전체 페이지의 갯수 구하기
		int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum = totalPageCount; //보정해 준다. 
		}
		
		//view page 에서 필요한 값을 Model 객체에 담기 
		model.addAttribute("list", list);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("pageNum", pageNum);
	}

	@Override
	public void deleteOne(int num) {
		GalleryDto dto=dao.getData(num);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!dto.getWriter().equals(userName)) {
			throw new NotOwnerException("남의 파일 삭제 불가!");
		}
		dao.delete(num);
	}

}


	/* @Override
    public void getList(Model model, GalleryDto dto) {
        // service logic
        List<GalleryDto> galleryList = dao.getList(dto);
        model.addAttribute("galleryList", galleryList);
    }

	@Override
	public void saveGallery(GalleryDto dto) {
		//파일 업로드 처리를 위한 객체의 참조값 얻어오기(업로드된 파일에 대한 정보를 얻어낼 객체)
				MultipartFile myFile=dto.getMyFile();
				//원본 파일명
				String orgFileName=myFile.getOriginalFilename();
				//파일의 크기
				long fileSize=myFile.getSize();
				//저장할 파일명을 하나 얻어낸다. 
				String saveFileName=UUID.randomUUID().toString();
				//저장할 파일의 상세 경로
				String filePath = fileLocation+File.separator+saveFileName;
				try {
					//File 객체 생성 
					File f=new File(filePath);
					//파일을 원하는 곳에 저장하기 
					myFile.transferTo(f);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//DB 에 업로드된 파일에 대한 정보를 저장한다.
				
				//로그인된 userName 이 글 작성자가 된다. 
				String userName=SecurityContextHolder.getContext().getAuthentication().getName();
				dto.setWriter(userName);
				dto.setOrgFileName(orgFileName);
				dto.setSaveFileName(saveFileName);
				dto.setFileSize(fileSize);
				
				dao.insert(dto);
			}

	@Override
	public ResponseEntity<InputStreamResource> getGalleryData(int num) {
		GalleryDto dto=dao.getData(num);
		ResponseEntity<InputStreamResource> responseEn=null;
		try {
			String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
			encodedName=encodedName.replaceAll("\\+"," ");
			HttpHeaders headers=new HttpHeaders();
			//파일을 다운로드 시켜 주겠다는 정보
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
			//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
			//파일의 크기 정보도 담아준다.
			headers.setContentLength(dto.getFileSize());
			//읽어들일 파일의 경로 구성
			String filePath=fileLocation + File.separator + dto.getSaveFileName();
			//파일에서 읽어들일 스트림 객체 생성
			InputStream is=new FileInputStream(filePath);
			//InputStreamResource
			InputStreamResource isr=new InputStreamResource(is);
			//InputStremResource 객체를 얻어내서 지역변수에 담고
			responseEn=ResponseEntity.ok().headers(headers).body(isr);
		}catch(Exception e) {
			//예외를 던지고 ExceptionController 에서 처리 할수 있다.
			throw new RuntimeException("파일 다운로드 중에 예외가 발생했습니다");
		}
		//InputStreamResource 객체를 리턴해준다.
		return responseEn;
	}

	@Override
	public void deleteGallery(int num) {
		GalleryDto dto=dao.getData(num);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!userName.equals(dto.getWriter())) {
			throw new NotOwnerException("남의 파일 지우기 없기!");
		}
		String filePath=fileLocation + File.separator + dto.getSaveFileName();
		File f = new File(filePath);
		f.delete();
		dao.delete(num);
		
	}
	*/


