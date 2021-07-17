package com.digital.factory.champions.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.factory.champions.model.League;

public interface LeagueDAO extends JpaRepository<League, Integer>{

}
