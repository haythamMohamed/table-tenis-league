package com.digital.factory.champions.dto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.digital.factory.champions.dto.LeagueDTO;
import com.digital.factory.champions.model.League;

@Mapper
public interface LeagueMapper {

	LeagueMapper INSTANCE = Mappers.getMapper(LeagueMapper.class);
	
	LeagueDTO beanToDTO (League league);
	League DTOToBean (LeagueDTO leagueDTO);
	
	List<LeagueDTO> beansToDTOs (List<League> leagues); 
}
