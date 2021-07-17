package com.digital.factory.champions.dto;

import java.util.List;

import lombok.Data;

@Data
public class MatchResultDTO {

	private Integer matchId ;
	private List<GameResult> gameResults ;
	
	@Data
	public static class GameResult {
		private Integer game ;
		private participantGameScore firstParticipantScore ;
		private participantGameScore secondParticipantScore ;
	}
	
	@Data
	public static class participantGameScore {
		private Integer participantId ;
		private Integer score ;
	}
}
