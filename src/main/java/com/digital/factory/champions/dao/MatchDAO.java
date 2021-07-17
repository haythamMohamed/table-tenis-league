package com.digital.factory.champions.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.factory.champions.model.League;
import com.digital.factory.champions.model.Match;
import com.digital.factory.champions.model.Round;

public interface MatchDAO extends JpaRepository<Match, Integer>{

	List<Match> findByLeagueAndRound(League league , Round round);
	List<Match> findByRound (Round round);
}
