package com.digital.factory.champions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dao.LeagueWinnerDAO;
import com.digital.factory.champions.model.LeagueWinner;
import com.digital.factory.champions.service.LeagueWinnerService;

@Service
@Transactional
public class LeagueWinnerServiceImpl implements LeagueWinnerService{

	@Autowired LeagueWinnerDAO leagueWinnerDAO;
	
	@Override
	public void save(LeagueWinner leagueWinner) {
		leagueWinnerDAO.save(leagueWinner);
	}

}
