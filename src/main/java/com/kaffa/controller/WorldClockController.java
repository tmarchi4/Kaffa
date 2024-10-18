package com.kaffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffa.service.WorldClockService;

@RestController
@RequestMapping("worldclock")
public class WorldClockController {

	@Autowired
	private WorldClockService worldClockService;

	@GetMapping
	public ResponseEntity<?> getDateTime() {
		return worldClockService.getDateTime();
	}

}
