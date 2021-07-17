package com.digital.factory.champions.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MatchRequestDTO {

	private RoundDTO roundDTO ;
	private Date time ;
	private ParticipantDTO[] participantDTOs ;
}
