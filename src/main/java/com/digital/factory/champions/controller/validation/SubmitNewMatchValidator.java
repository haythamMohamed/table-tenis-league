package com.digital.factory.champions.controller.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.digital.factory.champions.dto.MatchRequestDTO;
import com.digital.factory.champions.model.Round;
import com.digital.factory.champions.service.RoundService;

@Component
public class SubmitNewMatchValidator implements Validator {

	@Autowired RoundService roundService ;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MatchRequestDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MatchRequestDTO matchRequestDTO = (MatchRequestDTO) target ;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roundDTO", "round can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "time can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "participantDTOs", "participantDTOs can't be empty");
		
		if(matchRequestDTO.getRoundDTO().getId() != null) {
			Optional<Round> roundExists = roundService.getRoundById(matchRequestDTO.getRoundDTO().getId());
			if(roundExists.isPresent()) {
				if(roundExists.get().isClosed()) {
					errors.rejectValue("roundDTO", "this round is closed");
				}
			}else {
				errors.rejectValue("roundDTO", "there is no round with that id");
			}
		}else {
			errors.rejectValue("roundDTO", "there is no round id exist");
		}
	}

}
