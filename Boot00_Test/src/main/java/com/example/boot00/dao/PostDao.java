package com.example.boot00.dao;

import java.util.List;

import com.example.boot00.dto.PostDto;

public interface PostDao {
	
    public List<PostDto> getList();
   
    public void insert(PostDto dto);

    void delete(int id);
   
    public PostDto getData(int id);

    public void update(PostDto dto);
}

/*package com.example.boot05.dao;

import java.util.List;

import com.example.boot05.dto.MemberDto;

public interface MemberDao {
	public List<MemberDto> getList();
	public void insert(MemberDto dto);
	public void delete(int num);
	public MemberDto getData(int num); 
	public void update(MemberDto dto);
	//retrun 을 해야해서 void 값이 없는듯
	 * }
*/
