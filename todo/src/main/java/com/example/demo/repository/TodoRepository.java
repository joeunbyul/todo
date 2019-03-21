package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Todo;

public interface TodoRepository extends CrudRepository<Todo, String> {
	
	//@Query("select t from Todo t where t.contents like %?1%") //쿼리에서 테이블 명을 쓸 때에는 해당 테이블과 맵핑된 도메인의 클래스 명을 사용
	Page<Todo> findByContentsContaining(String contents,Pageable page);

	Todo findByListnum(int listNum);

	Page<Todo> findAll(Pageable page);

}

/*
 *  [JPA의 기능]

	1. 메소드의 이름으로 쿼리 생성
	2. 메소드의 이름으로 JPA NamedQuery 호출
	3. @Query 어노테이션을 사용해서 Repository Interface에 쿼리를 직접 정의
 */
