package com.digital.factory.champions.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dao.RoundDAO;
import com.digital.factory.champions.dto.RoundDTO;
import com.digital.factory.champions.model.Round;
import com.digital.factory.champions.service.RoundService;

@Service
@Transactional
public class RoundServiceImpl implements RoundService{

	@Autowired RoundDAO roundDAO ;
	
	@Override
	public void save(Round round) {
		roundDAO.save(round);		
	}

	@Override
	public Optional<Round> getRoundById(Integer id) {
		return roundDAO.findById(id);
	}

	@Override
	public void closeRound(RoundDTO roundDTO) {
		Round round = getRoundById(roundDTO.getId()).get();
		round.setClosed(true);
		
		roundDAO.save(round);
	}

}
