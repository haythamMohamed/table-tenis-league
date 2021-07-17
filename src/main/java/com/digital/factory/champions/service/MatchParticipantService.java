package com.digital.factory.champions.service;

import com.digital.factory.champions.model.MatchParticipant;

public interface MatchParticipantService {

	void save (MatchParticipant matchParticipant);
	boolean checkIfParticipantOnMatch (Integer matchId , Integer participantId);
	
	MatchParticipant getByMatchAndParticipant (Integer matchId , Integer participantId);
}
