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
@Table(name = "participant")
@Data
@NoArgsConstructor
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@Column(name = "full_name")
	private String fullName ;
	
	private String email ;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth ;
	
	public Participant (Integer id) {
		this.id = id ;
	}
}
