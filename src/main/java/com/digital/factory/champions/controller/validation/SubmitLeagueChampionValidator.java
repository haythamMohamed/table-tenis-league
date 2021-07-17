package com.digital.factory.champions.controller.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.digital.factory.champions.dto.SubmitLeagueChampionDTO ;
import com.digital.factory.champions.model.League;
import com.digital.factory.champions.model.Participant;
import com.digital.factory.champions.service.LeagueService;
import com.digital.factory.champions.service.ParticipantService;

@Component
public class SubmitLeagueChampionValidator implements Validator{

	@Autowired LeagueService leagueService ;
	@Autowired ParticipantService participantService ;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SubmitLeagueChampionDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SubmitLeagueChampionDTO championDTO = (SubmitLeagueChampionDTO) target ;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leagueId", "leagueId can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "participantId", "participantId can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "crowningDate", "crowningDate can't be empty");
		
		if(championDTO.getLeagueId() != null) {
			Optional<League> optional = leagueService.getById(championDTO.getLeagueId());
			if(!optional.isPresent()) {
				errors.rejectValue("leagueId", "No League found for this id");
			}
		}
		
		if(championDTO.getParticipantId() != null) {
			Optional<Participant> optional = participantService.getById(championDTO.getParticipantId());
			if(!optional.isPresent()) {
				errors.rejectValue("participantId", "No Participant found for this id");
			}
		}
	}

}
