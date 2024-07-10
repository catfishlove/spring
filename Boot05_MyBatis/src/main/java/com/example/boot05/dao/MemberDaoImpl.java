package com.example.boot05.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot05.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<MemberDto> getList() {
		/*
		 * 	SqlSession 객체를 이용해서 회원목록을 얻어온다. 
		 */
		List<MemberDto> list=session.selectList("member.getList");
		//select 하는 수에 따라 List 인지 One인지 갈린다. 
		//회원 목록 리턴해주기 
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		/*
		 * SqlSession 객체를 이용해서 MemberDto 객체에 담긴 정보를 DB에 저장하기 
		 */
		session.insert("member.insert", dto);
		
	}

	@Override
	public void delete(int num) {
		/*
		 * SqlSession 객체를 이용해서 회원번호로 해당 정보를 삭제하기 
		 */
		session.delete("member.delete", num);
		
	}


	@Override
	public MemberDto getData(int num) {
		//수정하고 되돌려주는 메소드 하나만 고르는 상황이니 SelectOne을 사용한다.
		//select resultType = "여기에 선언한 type" 이 된다.
		/*
		 * 회원 번호를 이용해서 회원 한명의 정보 얻어내기 
		 */
		MemberDto dto=session.selectOne("member.getData", num);
		
		
		return dto; 
	}

	@Override
	public void update(MemberDto dto) {
		/*
		 * mapper 의 namespace => member
		 * sql의 id => update
		 * parameterType => MemberDto
		 * 
		 */
		session.update("member.update", dto);
		
	}
	
	

}
