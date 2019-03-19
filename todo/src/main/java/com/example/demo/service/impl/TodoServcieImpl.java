package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;

@Service
public class TodoServcieImpl implements TodoService {

	@Autowired
    TodoRepository todoRepository;
	
	@Override
	public Page<Todo> selectList(String contents,Pageable pageable) {
		
		Page<Todo> list = todoRepository.findByContentsContaining(contents,pageable);
		
		return list ;
		
	}

	@Override
	public void insertList(Todo todo) {
		
		todoRepository.save(todo);

	}

	@Override
	public void deleteList(Todo todo) {
		
		System.out.println(todo);
		todoRepository.delete(todo);
		
	}

	@Override
	public Todo select(int listnum) {
		
		Todo todo = todoRepository.findByListnum(listnum);
		return todo;
		
	}

	@Override
	public void modify(Todo todo) {
		
		todoRepository.save(todo);
		
	}

	@Override
	public Page<Todo> selectAll(Pageable pageable) {
		
		Page<Todo> list = todoRepository.findAll(pageable);
		return list;
		
	}
	
}
