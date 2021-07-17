package com.digital.factory.champions.service;

import java.util.Optional;

import com.digital.factory.champions.dto.LeagueDTO;
import com.digital.factory.champions.dto.SubmitLeagueChampionDTO;
import com.digital.factory.champions.model.League;

public interface LeagueService {
	
	void addLeague (LeagueDTO leagueDTO);
	Optional<League> getById (Integer leagueId);
	
	void setLeagueChampion (SubmitLeagueChampionDTO leagueChampionDTO);
}
