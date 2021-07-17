package com.digital.factory.champions.model;

import java.util.Date;

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
@Table(name = "league_winner")
@Data
public class LeagueWinner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Participant participant ;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private League league ;
	
	@Column(name = "crowning_date")
	private Date crowningDate ;
}
