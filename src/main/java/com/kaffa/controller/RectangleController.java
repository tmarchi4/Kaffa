package com.kaffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffa.dto.RectangleDto;
import com.kaffa.service.RectangleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("rectangle")
@Tag(name = "Rectangle", description = "Checar se os retângulos possuem intersecção e sua área")
public class RectangleController {

	@Autowired
	private RectangleService rectangleService;

	@Operation(summary = "Intersecção", description = "Valida se os retângulos informados possuem intersecção")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna true se há intersecção, se não, false", content = @Content(mediaType = "application/text", schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "true"))), })
	@PostMapping("intersect")
	public ResponseEntity<?> intersect(@RequestBody(required = true) RectangleDto dto) {
		return rectangleService.intersect(dto.getRect1(), dto.getRect2());
	}

	@Operation(summary = "Intersecção", description = "Valida se os retângulos informados possuem intersecção")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o valor da área de intersecção se houver", content = @Content(mediaType = "application/text", schema = @Schema(implementation = Integer.class), examples = @ExampleObject(value = "15"))), })
	@PostMapping("intersect/area")
	public ResponseEntity<?> area(@RequestBody(required = true) RectangleDto dto) {
		return rectangleService.area(dto.getRect1(), dto.getRect2());
	}

}
