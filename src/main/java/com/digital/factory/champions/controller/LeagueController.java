package com.digital.factory.champions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.factory.champions.controller.validation.AddLeagueValidator;
import com.digital.factory.champions.controller.validation.SubmitLeagueChampionValidator;
import com.digital.factory.champions.dto.LeagueDTO;
import com.digital.factory.champions.dto.SubmitLeagueChampionDTO;
import com.digital.factory.champions.service.LeagueService;

@RestController
@RequestMapping("/league")
public class LeagueController {

	@Autowired LeagueService leagueService ;
	@Autowired AddLeagueValidator addLeagueValidator ;
	@Autowired SubmitLeagueChampionValidator submitLeagueChampionValidator ;
	
	
	@PostMapping("/")
	public void addLeague (@RequestBody LeagueDTO leagueDTO , BindingResult bindingResult) throws BindException {
		addLeagueValidator.validate(leagueDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		
		leagueService.addLeague(leagueDTO);
	}
	
	@PutMapping("/champion")
	public void setLeagueChampion (@RequestBody SubmitLeagueChampionDTO submitLeagueChampionDTO, BindingResult bindingResult) throws BindException{
		submitLeagueChampionValidator.validate(submitLeagueChampionDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		
		leagueService.setLeagueChampion(submitLeagueChampionDTO);
	}
}
