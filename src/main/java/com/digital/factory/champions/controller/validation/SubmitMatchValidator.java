package com.digital.factory.champions.controller.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.digital.factory.champions.dto.MatchResultDTO;
import com.digital.factory.champions.model.Match;
import com.digital.factory.champions.service.MatchParticipantService;
import com.digital.factory.champions.service.MatchService;

@Component
public class SubmitMatchValidator implements Validator {

	@Autowired MatchService matchService;
	@Autowired MatchParticipantService matchParticipantService;

	@Override
	public boolean supports(Class<?> clazz) {
		return MatchResultDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MatchResultDTO matchResultDTO = (MatchResultDTO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matchId", "matchId can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gameResults", "gameResults can't be empty");
		
		// validate the match
		if (matchResultDTO.getMatchId() != null) {
			Optional<Match> matchExist = matchService.getById(matchResultDTO.getMatchId());

			if (!matchExist.isPresent()) {
				errors.rejectValue("matchId", "no match with this id found");
			} else {
				if (matchExist.get().isFinished()) {
					errors.rejectValue("matchId", "this match already finished");
				}
			}
			
			// validate the score ( i've validated that every match is best of 5 matches )
			if(!(matchResultDTO.getGameResults() != null && matchResultDTO.getGameResults().size() > 3 && matchResultDTO.getGameResults().size() < 6)) {
				errors.rejectValue("gameResults", "invalid results");
			}
		}
	}

}
