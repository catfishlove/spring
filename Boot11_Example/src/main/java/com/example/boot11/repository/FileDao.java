package com.example.boot11.repository;

import com.example.boot11.dto.FileDto;

import java.util.List;

public interface FileDao {

    FileDto getFileByNum(int num);

    List<FileDto> getFileList(int startRowNum, int endRowNum);

    int getFileCount();

    void insertFile(FileDto fileDto);

    void deleteFile(int num);

    void updateFile(FileDto fileDto);
}
