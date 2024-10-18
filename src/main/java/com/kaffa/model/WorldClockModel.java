package com.kaffa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorldClockModel {

	private String dateTimeAmerica;
	@JsonProperty("currentDateTime")
	private String dateTime;

}
