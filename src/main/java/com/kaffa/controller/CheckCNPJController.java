package com.kaffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffa.dto.ResultDto;
import com.kaffa.service.CheckCNPJService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("check")
@Tag(name = "CheckCNPJ", description = "Checar CNPJ válido")
public class CheckCNPJController {

	@Autowired
	private CheckCNPJService checkCNPJService;

	@Operation(summary = "Validar o CNPJ informado", description = "Valida o CNPJ de acordo com as regras atuais da Receita Federal")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "CNPJ válido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResultDto.class), examples = @ExampleObject(value = "{\"success\": true,\"message\": \"CNPJ 39.555.869/0001-38 válido!\"}"))),
			@ApiResponse(responseCode = "400", description = "CNPJ inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResultDto.class), examples = @ExampleObject(value = "{\"success\": false,\"message\": \"CNPJ 39.555.869/0001-37 inválido!\"}"))), })
	@PostMapping("cnpj")
	public ResponseEntity<?> checkCNPJ(@RequestBody(required = true) String cnpj) {
		return checkCNPJService.checkCNPJ(cnpj);
	}

}
