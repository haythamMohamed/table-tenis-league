package com.digital.factory.champions.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.factory.champions.model.Participant;

public interface ParticipantDAO extends JpaRepository<Participant, Integer> {

}
