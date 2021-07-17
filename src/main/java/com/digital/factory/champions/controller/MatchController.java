package com.digital.factory.champions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.factory.champions.controller.validation.CloseRoundValidation;
import com.digital.factory.champions.controller.validation.SubmitMatchValidator;
import com.digital.factory.champions.controller.validation.SubmitNewMatchValidator;
import com.digital.factory.champions.dto.MatchDTO;
import com.digital.factory.champions.dto.MatchRequestDTO;
import com.digital.factory.champions.dto.MatchResultDTO;
import com.digital.factory.champions.dto.RoundDTO;
import com.digital.factory.champions.service.MatchService;
import com.digital.factory.champions.service.RoundService;

@RestController
@RequestMapping("/match")
public class MatchController {

	@Autowired MatchService matchService ;
	@Autowired SubmitMatchValidator submitMatchValidator;
	@Autowired CloseRoundValidation closeRoundValidation ;
	@Autowired RoundService roundService ;
	@Autowired SubmitNewMatchValidator submitNewMatchValidator ;
	
	@GetMapping("/{leagueId}")
	public List<MatchDTO> getFirstRoundMatches (@PathVariable(name = "leagueId") Integer leagueId ){
		return matchService.getMatchsPerLeagueAndFirstRound(leagueId);
	}
	
	@PostMapping("/submit-result")
	public void sumbitMatchResult (@RequestBody MatchResultDTO matchResultDTO , BindingResult bindingResult) throws BindException {
		submitMatchValidator.validate(matchResultDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		
		matchService.submitResult(matchResultDTO);
	}
	
	@PutMapping("/close-round")
	public void closeRound (@RequestBody RoundDTO roundDTO , BindingResult bindingResult) throws BindException {
		closeRoundValidation.validate(roundDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		
		roundService.closeRound(roundDTO);
	}
	
	@PostMapping("/add")
	public void addNewMatch (@RequestBody MatchRequestDTO matchRequestDTO, BindingResult bindingResult) throws BindException {
		submitNewMatchValidator.validate(matchRequestDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		matchService.submitNewMatch(matchRequestDTO);
	}
}
