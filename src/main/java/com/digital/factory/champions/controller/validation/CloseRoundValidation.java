package com.digital.factory.champions.controller.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.digital.factory.champions.dto.RoundDTO;
import com.digital.factory.champions.model.Match;
import com.digital.factory.champions.service.MatchService;

@Component
public class CloseRoundValidation implements Validator{

	@Autowired MatchService matchService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RoundDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RoundDTO roundDTO = (RoundDTO) target ;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "round id can't be empty");
		
		if(roundDTO.getId() != null) {
			List<Match> matches = matchService.getByRound(roundDTO.getId());
			long unfinishedMatches = matches.stream().filter(match -> !match.isFinished()).count();
			if(unfinishedMatches > 0) {
				errors.rejectValue("id", "There are unfinished matches on that round");
			}
		}else {
			errors.rejectValue("id", "there is no round with that id");
		}
		
	}

}
