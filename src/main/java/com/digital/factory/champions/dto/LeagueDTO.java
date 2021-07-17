package com.digital.factory.champions.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeagueDTO {

	private Integer id ;
	private String name ;
	private Date startDate ;
	private Date endDate ;
	
	public LeagueDTO (Integer id) {
		this.id = id ;
	}
}
