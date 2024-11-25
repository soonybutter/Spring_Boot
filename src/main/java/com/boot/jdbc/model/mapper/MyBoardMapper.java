package com.boot.jdbc.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.boot.jdbc.model.dto.MyDto;

@Mapper
public interface MyBoardMapper {
	//mybatis가 요청을 가로챔으로 selectList()가 가진 쿼리문을 실행, 결과를 리턴해줌.
	//mybatis 실행시 실행되는 쿼리문을 명시해준다.

	@Select(" SELECT * FROM MYBOARD ORDER BY MYNO DESC ")
	List<MyDto> selectList();
	
	@Select(" SELECT * FROM MYBOARD WHERE MYNO=#{myno} ")
	MyDto selectOne(int myno);
	
	@Insert(" INSERT INTO MYBOARD VALUES(MYNOSEQ.NEXTVAL, #{myname},#{mytitle},#{content},CURRENT_DATE) ")
	int insert(MyDto dto);
	
	@Update(" UPDATE MYBOARD SET MYTITLE=#{mytitle}, MYCONTENT=#{mycontent} WHERE MYNO=#{myno} ")
	int update(MyDto dto);
	
	@Delete(" DELETE FROM MYBOARD WHERE MYNO=#{myno} ")
	int delete(int myno);

}
