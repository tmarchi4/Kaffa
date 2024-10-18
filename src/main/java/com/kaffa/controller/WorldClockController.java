package com.kaffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffa.dto.ResultDto;
import com.kaffa.service.WorldClockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("worldclock")
@Tag(name = "WorldClock", description = "Checa o horário no formato UTC e América/SP")
public class WorldClockController {

	@Autowired
	private WorldClockService worldClockService;

	@Operation(summary = "Checar os horários", description = "Realiza a consulta na API WorldClock e retorna o horário (Rest e Client)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Horários (UTC e América)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResultDto.class), examples = @ExampleObject(value = "{\"success\": true,\"data\":{\"dateTimeAmerica\": \"2024-10-18 13:57\",\"currentDateTime\": \"2024-10-18T16:57Z\"}}"))), })
	@GetMapping
	public ResponseEntity<?> getDateTime() {
		return worldClockService.getDateTime();
	}

}
