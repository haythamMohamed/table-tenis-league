package com.digital.factory.champions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "round")
@Data
@NoArgsConstructor
public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private Integer rank ;
	
	private boolean closed ;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private League league ;
	
	public Round (Integer id) {
		this.id = id ;
	}
}
