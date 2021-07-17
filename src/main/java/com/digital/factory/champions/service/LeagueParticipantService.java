package com.digital.factory.champions.service;

import java.util.List;

import com.digital.factory.champions.model.League;
import com.digital.factory.champions.model.LeagueParticipant;

public interface LeagueParticipantService {

	Long countRegisteredParticipants (Integer leagueId);
	List<LeagueParticipant> getByLeague (League league);
	void saveLeagueParticipant (Integer participantId , Integer leagueId);
}
