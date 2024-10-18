package com.kaffa.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kaffa.dto.ResultDto;
import com.kaffa.dto.ToDoListDto;
import com.kaffa.model.ToDoListModel;
import com.kaffa.repository.ToDoListRepository;
import com.kaffa.util.Util;

@Service
public class ToDoListService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ToDoListRepository toDoListRepository;

	public ResponseEntity<?> list() {
		System.out.println("[X] Get All List");

		Connection connection = null;
		ResultDto result = new ResultDto();

		try {

			connection = dataSource.getConnection();

			List<ToDoListModel> list = toDoListRepository.list(connection);

			if (Util.empty(list)) {
				result.setMessage("Não há tarefas a serem exibidas!");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
			}

			result.setSuccess(true);
			result.setData(list);

			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" [x] Error: " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	public ResponseEntity<?> createTask(ToDoListDto dto) {
		System.out.println("[X] Create Task");

		Connection connection = null;
		ResultDto result = new ResultDto();

		try {

			connection = dataSource.getConnection();

			toDoListRepository.createTasks(connection, dto);

			result.setSuccess(true);
			result.setMessage("Tarefa criado com sucesso!");

			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" [x] Error: " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	public ResponseEntity<?> updateTask(ToDoListDto dto) {
		System.out.println("[X] Update Task " + dto.getId());

		Connection connection = null;
		ResultDto result = new ResultDto();

		try {

			connection = dataSource.getConnection();

			toDoListRepository.updateTasks(connection, dto);

			result.setSuccess(true);
			result.setMessage("Tarefa " + dto.getId() + " modificada com sucesso!");

			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" [x] Error: " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	public ResponseEntity<?> deleteTask(String id) {
		System.out.println("[X] Delete Task " + id);

		Connection connection = null;
		ResultDto result = new ResultDto();

		try {

			connection = dataSource.getConnection();

			toDoListRepository.deleteTasks(connection, id);

			result.setSuccess(true);
			result.setMessage("Tarefa " + id + " deletada com sucesso!");

			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" [x] Error: " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

}
