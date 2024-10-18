package com.kaffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffa.dto.ToDoListDto;
import com.kaffa.service.ToDoListService;

@RestController
@RequestMapping("tasks")
public class ToDoListController {

	@Autowired
	private ToDoListService toDoListService;

	@GetMapping
	public ResponseEntity<?> list() {
		return toDoListService.list();
	}

	@PostMapping("new")
	public ResponseEntity<?> createTask(@RequestBody ToDoListDto dto) {
		return toDoListService.createTask(dto);
	}

	@PutMapping("update")
	public ResponseEntity<?> updateTask(@RequestBody ToDoListDto dto) {
		return toDoListService.updateTask(dto);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable String id) {
		return toDoListService.deleteTask(id);
	}

}
