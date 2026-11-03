package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.TodoEntity;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	//@Query(value="select * from Todo t where t.user_id = ?1", nativeQuery = true)
	//1. 네이티브쿼리
	
	//@Query(value="select t from TodoEntity t where t.userId = ?1")
	//2, 네이티브쿼리가 아니라 * 못쓴다.
	//JpaRepository에 의해서 기본적이 ㄴ기능저저아,,
	List<TodoEntity> findByUserId(String userId);
	
}
