package com.digital.factory.champions.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.digital.factory.champions.dto.LeagueDTO;

@Component
public class AddLeagueValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return LeagueDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "startDate Can't be empty");

		LeagueDTO dto = (LeagueDTO) target ;
		if(dto.getId() != null) {
			errors.reject("id", "you can't update the league through this api");
		}
		
		if(dto.getEndDate() != null) {
			errors.reject("endDate", "endDate can't be set at the creation of league");
		}
	}

}
