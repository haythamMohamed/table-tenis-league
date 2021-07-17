package com.digital.factory.champions.service;

import java.util.List;
import java.util.Optional;

import com.digital.factory.champions.dto.ParticipantDTO;
import com.digital.factory.champions.model.Participant;

public interface ParticipantService {

	void addParticipant (ParticipantDTO participantDTO);
	List<ParticipantDTO> getAll ();
	
	Optional<Participant> getById (Integer id);
}
