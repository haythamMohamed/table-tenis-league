package com.digital.factory.champions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dao.MatchResultDAO;
import com.digital.factory.champions.model.MatchResult;
import com.digital.factory.champions.service.MatchResultService;

@Service
@Transactional
public class MatchResultServiceImpl implements MatchResultService{

	@Autowired MatchResultDAO matchResultDAO;
	
	@Override
	public void save(MatchResult matchResult) {
		matchResultDAO.save(matchResult);
	}

}
