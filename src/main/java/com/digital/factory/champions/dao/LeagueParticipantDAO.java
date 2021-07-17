package com.digital.factory.champions.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digital.factory.champions.model.League;
import com.digital.factory.champions.model.LeagueParticipant;

public interface LeagueParticipantDAO extends JpaRepository<LeagueParticipant, Integer>{

	@Query(name = "SELECT COUNT(lp) FROM LeagueParticipant lp WHERE lp.league =:league")
	Long countParticipantByLeague (League league);
	
	List<LeagueParticipant> getByLeague (League league);
}
