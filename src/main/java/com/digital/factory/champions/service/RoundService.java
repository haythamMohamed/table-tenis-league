package com.digital.factory.champions.service;

import java.util.Optional;

import com.digital.factory.champions.dto.RoundDTO;
import com.digital.factory.champions.model.Round;

public interface RoundService {

	void save (Round round);
	Optional<Round> getRoundById (Integer id);
	
	void closeRound (RoundDTO roundDTO);
}
