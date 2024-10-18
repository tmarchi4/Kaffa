package com.kaffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffa.service.CheckCNPJService;

@RestController
@RequestMapping("check")
public class CheckCNPJController {

	@Autowired
	private CheckCNPJService checkCNPJService;

	@PostMapping("cnpj")
	public ResponseEntity<?> checkCNPJ(@RequestBody(required = true) String cnpj) {
		return checkCNPJService.checkCNPJ(cnpj);
	}

}
