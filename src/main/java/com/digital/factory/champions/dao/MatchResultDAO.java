package com.digital.factory.champions.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.factory.champions.model.MatchResult;

public interface MatchResultDAO extends JpaRepository<MatchResult, Integer>{

}
