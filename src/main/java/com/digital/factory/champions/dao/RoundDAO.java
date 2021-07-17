package com.digital.factory.champions.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.factory.champions.model.Round;

public interface RoundDAO extends JpaRepository<Round, Integer>{

}
