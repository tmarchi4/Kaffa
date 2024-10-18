package com.kaffa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoListModel {

	private String id;
	private String description;
	private String status;
	private String createdAt;
	private String updatedAt;

}
