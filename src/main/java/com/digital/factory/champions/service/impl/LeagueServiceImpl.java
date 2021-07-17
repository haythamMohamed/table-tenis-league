package com.digital.factory.champions.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dao.LeagueDAO;
import com.digital.factory.champions.dto.LeagueDTO;
import com.digital.factory.champions.dto.SubmitLeagueChampionDTO;
import com.digital.factory.champions.dto.mappers.LeagueMapper;
import com.digital.factory.champions.model.League;
import com.digital.factory.champions.model.LeagueWinner;
import com.digital.factory.champions.model.Participant;
import com.digital.factory.champions.service.LeagueService;
import com.digital.factory.champions.service.LeagueWinnerService;

@Service
@Transactional
public class LeagueServiceImpl implements LeagueService {

	@Autowired LeagueDAO leagueDAO;
	@Autowired LeagueWinnerService leagueWinnerService;
	
	@Override
	public void addLeague(LeagueDTO leagueDTO) {
		leagueDAO.save(LeagueMapper.INSTANCE.DTOToBean(leagueDTO));
	}

	@Override
	public Optional<League> getById(Integer leagueId) {
		return leagueDAO.findById(leagueId);
	}

	@Override
	public void setLeagueChampion(SubmitLeagueChampionDTO leagueChampionDTO) {
		League league = getById(leagueChampionDTO.getLeagueId()).get();
		
		LeagueWinner leagueWinner = new LeagueWinner();
		leagueWinner.setCrowningDate(leagueChampionDTO.getCrowningDate());
		leagueWinner.setLeague(league);
		leagueWinner.setParticipant(new Participant(leagueChampionDTO.getParticipantId()));
		leagueWinnerService.save(leagueWinner);
		
		league.setEndDate(leagueChampionDTO.getCrowningDate());
		leagueDAO.save(league);
	}

}
