package com.example.boot11.service;

import java.util.List;

import com.example.boot11.dto.FileDto;

public interface FileService {

    public void uploadFile(FileDto fileDto);
    List<FileDto> getFileList(int startRowNum, int endRowNum);
    FileDto getFileByNum(int num);
    public void deleteFile(int num);
    public void updateFile(FileDto fileDto);
}