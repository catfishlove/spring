package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot11.dto.GalleryDto;
import com.example.boot11.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired private GalleryService service;
	
	@GetMapping("/gallery/delete")
	public String delete(int num) {
		service.deleteOne(num);
		return "redirect:/gallery/list";
	}
	
	@GetMapping("/gallery/detail")
	public String detail(Model model, int num) {
		//num 에는 자세히 보여줄 gallery 의 pk 가 들어 있다. 
		service.selectOne(model, num);
		
		return "gallery/detail";
	}
	
	@PostMapping("/gallery/upload")
	public String upload(GalleryDto dto) {
		
		//String caption 과 MultipartFile image 가 들어 있는 GalleryDto 를 서비스에 전달해서 저장한다 
		service.addToGallery(dto);
		
		return "redirect:/gallery/list";
	}
	
	@GetMapping("/gallery/upload_form")
	public String uploadForm() {
		
		return "gallery/upload_form";
	}
	
	@GetMapping("/gallery/list")
	public String list(Model model,@RequestParam(defaultValue = "1") int pageNum) {
		/*
		 *  서비스에 Model 객체와 pageNum 을 전달해서 
		 *  Model 에 pageNum 에 해당하는 글 목록이 담기도록 한다.
		 *  Model 에 담긴 내용을 view page(Thymeleaf 템플릿페이지) 에서 사용할수 있다
		 */
		service.selectPage(model, pageNum);
		
		return "gallery/list";
	}
}
	
    /* @GetMapping("/gallery/list")
    
    public String selectPage(Model model, int pageNum) {
     
    	service.selectPage(model, pageNum);
        
   
        return "gallery/list";
    }
 	
	
	@ResponseBody //메소드의 반환값을 HTTP 응답 본문으로 직렬화 할 것임을 나타냄. 
	@GetMapping(
	//HTTP GET 및 POST 요청을 컨트롤러의 특정 메소드와 매핑시킨다 
		value="/gallery/images/{imageName}",
		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE}
		
	)
	//value 속성을 이용하면 다른 값 말고도 콤마를 이용하여 다른 것들을 전달할 수 있음
	//@GetMapping ("/gallery/images/{imageName}")
	public byte [] image(@PathVariable ("imageName") String name) throws IOException {
		//imageName 경로 변수에 들어있는 문자열이 매개변수 String name 에 전달된다. 
		//읽어들일 파일의 절대 경로
		String absolutePath=fileLocation + File.separator + name;
		//파일에서 읽어들일 InputStream
		InputStream is=new FileInputStream(absolutePath);
		
		return IOUtils.toByteArray(is);
	}
	
	@GetMapping("/gallery/upload_form")
	public String uploadForm() {
		return "gallery/upload_form";
	}
	
	/*
	 *  <input type="file" name="image">  의 name 속성의 value 가 image 이기 때문에
	 *  
	 *  MultipartFile  image  로 매개 변수를 선언하면 된다. 
	 */
	/*
	@PostMapping("/gallery/upload")
	public String upload(MultipartFile image, Model m) {
		//저장할 파일의 이름 겹치지 않는 유일한 문자열로 얻어내기
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			//업로드된 파일을 이동시킬 목적지 File 객체
			File f=new File(filePath);
			//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
			image.transferTo(f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("saveFileName", saveFileName);
		return "gallery/upload";
	}
	*/
