package com.digital.factory.champions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "match_participant")
@Data
public class MatchParticipant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Match match ;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Participant participant ;
	
	@Column(name = "is_winner")
	private Boolean isWinner ;
}
