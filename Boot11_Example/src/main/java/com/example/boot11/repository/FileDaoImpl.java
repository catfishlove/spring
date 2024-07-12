package com.example.boot11.repository;

import com.example.boot11.dto.FileDto;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDaoImpl implements FileDao {

    @Autowired
    private SqlSession session;

    @Override
    public FileDto getFileByNum(int num) {
        return session.selectOne("file.getFileByNum", num);
    }

    @Override
    public List<FileDto> getFileList(int startRowNum, int endRowNum) {

        return session.selectList("file.getFileList", new RowBounds(startRowNum - 1, endRowNum - startRowNum + 1));
    }

    @Override
    public int getFileCount() {
        return session.selectOne("file.getFileCount");
    }

    @Override
    public void insertFile(FileDto fileDto) {
        session.insert("file.insertFile", fileDto);
    }

    @Override
    public void deleteFile(int num) {
        session.delete("file.deleteFile", num);
    }

    @Override
    public void updateFile(FileDto fileDto) {
        session.update("file.updateFile", fileDto);
    }
}