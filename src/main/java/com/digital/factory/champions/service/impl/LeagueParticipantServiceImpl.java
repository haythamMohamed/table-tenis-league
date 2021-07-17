package com.digital.factory.champions.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dao.LeagueParticipantDAO;
import com.digital.factory.champions.model.League;
import com.digital.factory.champions.model.LeagueParticipant;
import com.digital.factory.champions.model.Participant;
import com.digital.factory.champions.service.LeagueParticipantService;

@Service
@Transactional
public class LeagueParticipantServiceImpl implements LeagueParticipantService{

	@Autowired LeagueParticipantDAO leagueParticipantDAO ;
	
	@Override
	public Long countRegisteredParticipants(Integer leagueId) {
		return leagueParticipantDAO.countParticipantByLeague(new League(leagueId));
	}

	@Override
	public void saveLeagueParticipant(Integer participantId , Integer leagueId) {
		LeagueParticipant leagueParticipant = new LeagueParticipant();
		leagueParticipant.setLeague(new League(leagueId));
		leagueParticipant.setParticipant(new Participant(participantId));
		leagueParticipant.setJoinDate(new Date());
		
		leagueParticipantDAO.save(leagueParticipant);
	}

	@Override
	public List<LeagueParticipant> getByLeague(League league) {
		return leagueParticipantDAO.getByLeague(league);
	}

	
}
