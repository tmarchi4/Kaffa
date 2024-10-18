package com.kaffa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.kaffa.dto.ToDoListDto;
import com.kaffa.model.ToDoListModel;

@Repository
public class ToDoListRepository {

	public List<ToDoListModel> list(Connection connection) throws SQLException {
		List<ToDoListModel> list = new ArrayList<ToDoListModel>();

		String query = "SELECT id, description, status, created_at, updated_at FROM TASKS";

		PreparedStatement stm = connection.prepareStatement(query);

		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			ToDoListModel toDo = new ToDoListModel();
			toDo.setId(rs.getString("id"));
			toDo.setDescription(rs.getString("description"));
			toDo.setStatus(rs.getString("status"));
			toDo.setCreatedAt(rs.getString("created_at"));
			toDo.setUpdatedAt(rs.getString("updated_at"));
			list.add(toDo);
		}

		rs.close();
		stm.close();

		return list;

	}

	public void createTasks(Connection connection, ToDoListDto dto) throws SQLException {

		String query = "INSERT INTO TASKS (id, description, status) VALUES (?, ?, ?)";

		PreparedStatement stm = connection.prepareStatement(query);
		stm.setString(1, UUID.randomUUID().toString());
		stm.setString(2, dto.getDescription());
		stm.setString(3, "pendente");

		stm.execute();
		stm.close();

	}

	public void updateTasks(Connection connection, ToDoListDto dto) throws SQLException {

		String query = "UPDATE kaffa.TASKS SET description= ?, status = ? WHERE id = ?";

		PreparedStatement stm = connection.prepareStatement(query);
		stm.setString(1, dto.getDescription());
		stm.setString(2, dto.getStatus());
		stm.setString(3, dto.getId());

		stm.execute();
		stm.close();

	}

	public void deleteTasks(Connection connection, String id) throws SQLException {

		String query = "DELETE FROM TASKS WHERE id = ?";

		PreparedStatement stm = connection.prepareStatement(query);
		stm.setString(1, id);

		stm.execute();
		stm.close();

	}

}
