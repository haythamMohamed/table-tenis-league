package com.digital.factory.champions.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dao.ParticipantDAO;
import com.digital.factory.champions.dto.ParticipantDTO;
import com.digital.factory.champions.dto.mappers.ParticipantMapper;
import com.digital.factory.champions.model.Participant;
import com.digital.factory.champions.service.LeagueParticipantService;
import com.digital.factory.champions.service.MatchService;
import com.digital.factory.champions.service.ParticipantService;

@Service
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired ParticipantDAO participantDAO ;
	@Autowired LeagueParticipantService leagueParticipantService ;
	@Autowired MatchService matchService;
	
	@Override
	public void addParticipant(ParticipantDTO participantDTO) {
		Participant participant = ParticipantMapper.INSTANCE.DTOToBean(participantDTO);
		participant = participantDAO.save(participant);
		
		leagueParticipantService.saveLeagueParticipant(participant.getId() , participantDTO.getLeagueId());
	
		Long participantCount = leagueParticipantService.countRegisteredParticipants(participantDTO.getLeagueId());
		
		if(participantCount == 13) { // league ready to start
			matchService.createRound(participantDTO.getLeagueId() , 1); // create first round
		}
		
	}

	@Override
	public List<ParticipantDTO> getAll() {
		List<Participant> participants =  participantDAO.findAll();
		
		return ParticipantMapper.INSTANCE.beansToDTOs(participants);
	}

	@Override
	public Optional<Participant> getById(Integer id) {
		return participantDAO.findById(id);
	}

}
