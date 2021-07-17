package com.digital.factory.champions.dto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.digital.factory.champions.dto.ParticipantDTO;
import com.digital.factory.champions.model.Participant;

@Mapper
public interface ParticipantMapper {

	ParticipantMapper INSTANCE = Mappers.getMapper(ParticipantMapper.class);
	
	@Mapping(target =  "leagueId" , ignore = true)
	ParticipantDTO beanToDTO (Participant participant);
	
	Participant DTOToBean (ParticipantDTO participantDTO);
	
	@Mapping(target =  "leagueId" , ignore = true)
	List<ParticipantDTO> beansToDTOs (List<Participant> participants); 
}
