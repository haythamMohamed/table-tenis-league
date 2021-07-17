package com.digital.factory.champions.controller.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.digital.factory.champions.dto.ParticipantDTO;
import com.digital.factory.champions.model.League;
import com.digital.factory.champions.service.LeagueParticipantService;
import com.digital.factory.champions.service.LeagueService;

@Component
public class AddParticipantValidator implements Validator{

	@Autowired LeagueService leagueService;
	@Autowired LeagueParticipantService leagueParticipantService ;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ParticipantDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ParticipantDTO participantDTO = (ParticipantDTO) target ;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "fullName can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "dateOfBirth can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leagueId", "League can't be empty");
		
		if(participantDTO.getLeagueId() != null ) {
			Optional<League> leagueOptional = leagueService.getById(participantDTO.getLeagueId());
			if(!leagueOptional.isPresent()) {
				errors.rejectValue("leagueId", "League not found");
			}else {
				Long count = leagueParticipantService.countRegisteredParticipants(participantDTO.getLeagueId());
				System.out.println(count);
				if(count == 13) {
					errors.rejectValue("leagueId", "League reached maximum participants");
				}
			}
		}
	}

}
