package com.digital.factory.champions.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "league")
@Data
@NoArgsConstructor
public class League {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id ;
	private String name ;
	
	@Column(name = "start_date")
	private Date startDate ;
	
	@Column(name = "end_date")
	private Date endDate ;
	
	public League(Integer id) {
		this.id = id ;
	}
}
