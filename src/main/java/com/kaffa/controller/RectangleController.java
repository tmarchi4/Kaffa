package com.kaffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffa.dto.RectangleDto;
import com.kaffa.service.RectangleService;

@RestController
@RequestMapping("rectangle")
public class RectangleController {

	@Autowired
	private RectangleService rectangleService;

	@PostMapping("intersect")
	public ResponseEntity<?> intersect(@RequestBody(required = true) RectangleDto dto) {
		return rectangleService.intersect(dto.getRect1(), dto.getRect2());
	}

	@PostMapping("intersect/area")
	public ResponseEntity<?> area(@RequestBody(required = true) RectangleDto dto) {
		return rectangleService.area(dto.getRect1(), dto.getRect2());
	}

}
