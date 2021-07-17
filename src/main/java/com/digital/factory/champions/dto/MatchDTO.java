package com.digital.factory.champions.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.digital.factory.champions.model.Match;
import com.digital.factory.champions.model.MatchParticipant;
import com.digital.factory.champions.model.Round;

import lombok.Data;

@Data
public class MatchDTO {

	private Integer id;
	private Date actionDate;
	private Round round;
	private LeagueDTO leagueDTO;
	private List<ParticipantDTO> participantDTOs ;
	
	
	public static MatchDTO beanToDTO (Match match) {
		MatchDTO matchDTO = new MatchDTO();
		matchDTO.setActionDate(match.getActionDate());
		matchDTO.setId(match.getId());
		matchDTO.setRound(match.getRound());
		matchDTO.setLeagueDTO(new LeagueDTO(match.getLeague().getId()));
		
		List<ParticipantDTO> participants = match.getMatchParticipants().stream().map(matchParticipant -> MatchParticipantToParticipant(matchParticipant)).collect(Collectors.toList());
		matchDTO.setParticipantDTOs(participants);
		
		return matchDTO;
	}
	
	private static ParticipantDTO MatchParticipantToParticipant (MatchParticipant matchParticipant) {
		ParticipantDTO participantDTO = new ParticipantDTO();
		participantDTO.setId(matchParticipant.getParticipant().getId());
		participantDTO.setFullName(matchParticipant.getParticipant().getFullName());

		return participantDTO ;
	}
}
