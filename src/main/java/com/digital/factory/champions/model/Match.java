package com.digital.factory.champions.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "match")
@Data
@NoArgsConstructor
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@Column(name = "action_date")
	private Date actionDate ;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private League league ;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Round round ;
	
	@OneToMany(fetch = FetchType.EAGER , mappedBy = "match")
	private List<MatchParticipant> matchParticipants ;
	
	private boolean finished ;
	
	public Match (Integer id) {
		this.id = id ;
	}
}
