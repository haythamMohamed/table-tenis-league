package com.digital.factory.champions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dao.MatchParticipantDAO;
import com.digital.factory.champions.model.Match;
import com.digital.factory.champions.model.MatchParticipant;
import com.digital.factory.champions.model.Participant;
import com.digital.factory.champions.service.MatchParticipantService;

@Service
@Transactional
public class MatchParticipantServiceImpl implements MatchParticipantService{

	@Autowired MatchParticipantDAO matchParticipantDAO ;
	
	@Override
	public void save(MatchParticipant matchParticipant) {
		matchParticipantDAO.save(matchParticipant);
	}

	@Override
	public boolean checkIfParticipantOnMatch(Integer matchId, Integer participantId) {
		return getByMatchAndParticipant(matchId , participantId) != null;
	}

	@Override
	public MatchParticipant getByMatchAndParticipant(Integer matchId, Integer participantId) {
		return matchParticipantDAO.findByMatchAndParticipant(new Match(matchId) , new Participant(participantId));
	}

}
