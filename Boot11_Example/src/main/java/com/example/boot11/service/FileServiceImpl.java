package com.example.boot11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.boot11.dto.FileDto;
import com.example.boot11.repository.FileDao;

public class FileServiceImpl implements FileService{

	@Autowired private FileDao dao;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@Override
	public void uploadFile(FileDto fileDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FileDto> getFileList(int startRowNum, int endRowNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDto getFileByNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFile(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFile(FileDto fileDto) {
		// TODO Auto-generated method stub
		
	}

}
