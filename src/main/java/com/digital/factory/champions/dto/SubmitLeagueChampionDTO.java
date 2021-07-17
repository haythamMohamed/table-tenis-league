package com.digital.factory.champions.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SubmitLeagueChampionDTO {

	private Integer leagueId ;
	private Integer participantId ;
	private Date crowningDate ;
}
