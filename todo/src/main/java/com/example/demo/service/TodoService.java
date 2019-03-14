package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Todo;

public interface TodoService {

	/**
     * 리스트 목록 조회
     * @return
     */
    public List<Todo> selectList(String contents);
    
    /**
     * 리스트 전체 목록 조회
     * @return
     */
    public List<Todo> selectAll();
    
    /**
     * 조회
     * @return listNum
     */
    public Todo select(int listNum);
     
    /**
     * todo 등록
     * @param todo
     */
    public void insertList(Todo todo);
    
    /**
     * todo 수정
     * @param todo
     */
    public void modify(Todo todo);
    
    /**
     * todo 삭제
     * @param todo
     */
    public void deleteList(Todo todo);

}
