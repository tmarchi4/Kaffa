package com.kaffa.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kaffa.dto.ResultDto;

@Service
public class CheckCNPJService {

	public ResponseEntity<?> checkCNPJ(String cnpj) {
		System.out.println("[X] Check CNPJ - " + cnpj);

		ResultDto result = new ResultDto();

		try {

			if (!validateCNPJ(cnpj)) {
				result.setMessage("CNPJ " + cnpj + " inválido!"); // Remova as aspas ao redor do CNPJ
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}

			result.setSuccess(true);
			result.setMessage("CNPJ " + cnpj + " válido!");

			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" [x] Error: " + e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	public static boolean validateCNPJ(String cnpj) {

		String newCnpj = cnpj.replaceAll("[^\\d]", ""); // remove as pontuações se tiver

		// Remove todos os pontos e barra - Verifica se o CNPJ tem 14 dígitos
		if (newCnpj.length() != 14) {
			return false;
		}

		// Calcula e verifica os dígitos verificadores
		String cnpjToCalculate = newCnpj.substring(0, 12); // Pega os primeiros 12 dígitos
		cnpjToCalculate += calculateCheckDigit(cnpjToCalculate); // Adiciona o 13º dígito
		cnpjToCalculate += calculateCheckDigit(cnpjToCalculate); // Adiciona o 14º dígito

		return newCnpj.equals(cnpjToCalculate);
	}

	private static int calculateCheckDigit(String cnpj) {
		int peso, number, soma;
		peso = 2; /// peso inicial é 2 segundo a regra
		soma = 0;

		for (int i = cnpj.length() - 1; i >= 0; i--) {
			number = Character.getNumericValue(cnpj.charAt(i));
			soma = soma + (number * peso);
			peso = peso + 1;
			if (peso == 10) // sempre é adicionado um, limitado a 9, se chegar a 10 volta ao 2
				peso = 2;
		}

		int total = soma % 11;
		int result = total < 2 ? 0 : 11 - total; // se for 0 ou 1, é 0, se nao, a subtração

		return result;
	}

}
