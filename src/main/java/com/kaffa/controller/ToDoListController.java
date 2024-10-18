package com.kaffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffa.dto.ResultDto;
import com.kaffa.dto.ToDoDescriptionDto;
import com.kaffa.dto.ToDoListDto;
import com.kaffa.service.ToDoListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("tasks")
@Tag(name = "ToDoList", description = "Gerenciar tarefas")
public class ToDoListController {

	@Autowired
	private ToDoListService toDoListService;

	@Operation(summary = "Visualizar as tarefas", description = "Retorna todas as tarefas postadas e seus respectivos andamentos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista das tarefas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResultDto.class), examples = @ExampleObject(value = "{\"success\":true,\"data\":[{\"id\":\"079b7f4c-245f-4063-9f9d-27b0d53632fc\",\"description\":\"Testardelete\",\"status\":\"pendente\",\"createdAt\":\"2024-10-1803:33:07\",\"updatedAt\":\"2024-10-1803:33:07\"}]}"))), })
	@GetMapping
	public ResponseEntity<?> list() {
		return toDoListService.list();
	}

	@Operation(summary = "Postar tarefas", description = "Posta novas tarefas a serem executadas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna que a tarefa foi criada com sucesso", content = @Content(mediaType = "application/text", schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "{\"success\": true,\"message\": \"Tarefa criado com sucesso!\"}"))),
			@ApiResponse(responseCode = "500", description = "Retorna a impossibilidade de criar a tarefa", content = @Content(mediaType = "application/text", schema = @Schema(implementation = String.class))) })
	@PostMapping("new")
	public ResponseEntity<?> createTask(@RequestBody ToDoDescriptionDto dto) {
		return toDoListService.createTask(dto);
	}

	@Operation(summary = "Modificar tarefas", description = "Modifica as tarefas ja criadas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna que a tarefa foi modificada com sucesso", content = @Content(mediaType = "application/text", schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "{\"success\": true,\"message\": \"Tarefa 73ecda1c-d21c-455b-8327-84aa89c19dd7 modificada com sucesso!\"}"))),
			@ApiResponse(responseCode = "500", description = "Retorna a impossibilidade de modificar a tarefa", content = @Content(mediaType = "application/text", schema = @Schema(implementation = String.class))) })
	@PutMapping("update")
	public ResponseEntity<?> updateTask(@RequestBody ToDoListDto dto) {
		return toDoListService.updateTask(dto);
	}

//	@DeleteMapping("delete/{id}")
//	public ResponseEntity<?> deleteTask(@PathVariable String id) {
//		return toDoListService.deleteTask(id);
//	}

}
