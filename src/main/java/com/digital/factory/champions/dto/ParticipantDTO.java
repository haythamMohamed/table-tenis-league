package com.digital.factory.champions.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ParticipantDTO {

	private Integer id ;
	private String fullName ;
	private String email ;
	private Date dateOfBirth ;
	
	private Integer leagueId ;
}
