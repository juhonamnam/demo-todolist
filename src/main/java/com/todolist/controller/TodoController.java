package com.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.common.CommonResponse;
import com.todolist.common.CommonResponseFail;
import com.todolist.common.CommonResponseSuccess;
import com.todolist.entity.Todo;
import com.todolist.service.TodoService;

@RestController
@RequestMapping("/api/todo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/retrieveTodo")
	public ResponseEntity<CommonResponse<List<Todo>>> retrieveTodo() {
		CommonResponse<List<Todo>> response;
		try {
			List<Todo> todos = todoService.retrieveTodo();
			response = new CommonResponseSuccess<List<Todo>>();
			response.setData(todos);
		} catch (Exception e) {
			response = new CommonResponseFail<List<Todo>>();
			response.setMessage(e.getMessage());
		}
		return new ResponseEntity<CommonResponse<List<Todo>>>(response, HttpStatus.OK);
	}

	@PostMapping("/createTodo")
	public ResponseEntity<CommonResponse<Todo>> createTodo(@RequestBody Todo todo) {
		CommonResponse<Todo> response;
		try {
			Todo createdTodo = todoService.createTodo(todo);
			response = new CommonResponseSuccess<Todo>();
			response.setData(createdTodo);
		} catch (Exception e) {
			response = new CommonResponseFail<Todo>();
			response.setMessage(e.getMessage());
		}
		return new ResponseEntity<CommonResponse<Todo>>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deleteTodo/{id}")
	public ResponseEntity<CommonResponse<Todo>> deleteTodo(@PathVariable("id") int id) {
		CommonResponse<Todo> response;
		try {
			Todo deletedTodo = todoService.deleteTodo(id);
			response = new CommonResponseSuccess<Todo>();
			response.setData(deletedTodo);
		} catch (Exception e) {
			response = new CommonResponseFail<Todo>();
			response.setMessage(e.getMessage());
		}
		return new ResponseEntity<CommonResponse<Todo>>(response, HttpStatus.OK);
	}

}
