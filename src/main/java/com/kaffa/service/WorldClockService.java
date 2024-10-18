package com.kaffa.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kaffa.dto.ResultDto;
import com.kaffa.model.WorldClockModel;

@Service
public class WorldClockService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private WebClient webClient;

	public ResponseEntity<?> getDateTime() {

		Connection connection = null;
		ResultDto result = new ResultDto();

		try {

			dataSource.getConnection();

			WorldClockModel response = webClient.get().uri("http://worldclockapi.com/api/json/utc/now").retrieve()
					.bodyToMono(WorldClockModel.class).block();

			ZonedDateTime americaTime = ZonedDateTime.parse(response.getDateTime())
					.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));

			response.setDateTimeAmerica(americaTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

			result.setSuccess(true);
			result.setData(response);

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
