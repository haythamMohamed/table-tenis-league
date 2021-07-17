package com.digital.factory.champions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "match_result")
@Data
public class MatchResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id" , name = "match_participant")
	private MatchParticipant matchParticipant ; 
	
	private Integer game ;
	
	private Integer score ;
}
