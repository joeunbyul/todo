package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;

@Service
public class TodoServcieImpl implements TodoService {

	@Autowired
    TodoRepository todoRepository;
	
	List<Todo> list = new ArrayList<Todo>();
	
	@Override
	public List<Todo> selectList(String contents) {
		// TODO Auto-generated method stub
		list = todoRepository.findByContents(contents);
        
		return list ;
	}

	@Override
	public void insertList(Todo todo) {
		// TODO Auto-generated method stub
		todoRepository.save(todo);

	}

	@Override
	public void deleteList(Todo todo) {
		// TODO Auto-generated method stub
		System.out.println(todo);
		todoRepository.delete(todo);
	}

	@Override
	public Todo select(int listnum) {
		// TODO Auto-generated method stub
		Todo todo = todoRepository.findByListnum(listnum);
		return todo;
	}

	@Override
	public void modify(Todo todo) {
		// TODO Auto-generated method stub
		todoRepository.save(todo);
	}

	@Override
	public List<Todo> selectAll() {
		// TODO Auto-generated method stub
		list = todoRepository.findAllByOrderByCdateDesc();
		return list;
	}
	
}
