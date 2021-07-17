package com.digital.factory.champions.service;

import java.util.List;
import java.util.Optional;

import com.digital.factory.champions.dto.MatchDTO;
import com.digital.factory.champions.dto.MatchRequestDTO;
import com.digital.factory.champions.dto.MatchResultDTO;
import com.digital.factory.champions.model.Match;

public interface MatchService {

	Optional<Match> getById (Integer matchId);
	
	void createRound (Integer leagueId , Integer round);
	List<MatchDTO> getMatchsPerLeagueAndFirstRound (Integer leagueId);
	
	void submitResult (MatchResultDTO matchResultDTO);
	
	List<Match> getByRound (Integer roundId);
	
	void submitNewMatch (MatchRequestDTO matchRequestDTO);
 }
