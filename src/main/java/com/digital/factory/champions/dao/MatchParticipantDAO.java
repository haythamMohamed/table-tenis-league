package com.digital.factory.champions.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.factory.champions.model.Match;
import com.digital.factory.champions.model.MatchParticipant;
import com.digital.factory.champions.model.Participant;

public interface MatchParticipantDAO extends JpaRepository<MatchParticipant, Integer>{

	MatchParticipant findByMatchAndParticipant(Match match , Participant participant);
}
