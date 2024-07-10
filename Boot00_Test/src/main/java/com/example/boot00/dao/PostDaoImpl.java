package com.example.boot00.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot00.dto.PostDto;

@Repository
public class PostDaoImpl implements PostDao{

	@Autowired
	private SqlSession session;
	
	public List<PostDto> getList(){
		
		List<PostDto> list=session.selectList("post.getList");
		
		return list;
	}
	
	@Override
	public void delete(int id) {
		session.delete("post.delete", id);
	}
	
	@Override
	public PostDto getData(int id) {
		PostDto dto=session.selectOne("post.getData", id);
		
		return dto;
	}
	
	@Override
	public void update(PostDto dto) {
		session.update("post.update", dto);
	}

	@Override
	public void insert(PostDto dto) {
		// TODO Auto-generated method stub
		
	}
}
