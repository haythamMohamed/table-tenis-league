package com.digital.factory.champions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.factory.champions.controller.validation.AddParticipantValidator;
import com.digital.factory.champions.dto.ParticipantDTO;
import com.digital.factory.champions.service.ParticipantService;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

	@Autowired ParticipantService participantService ;
	@Autowired AddParticipantValidator addParticipantValidator;
	
	@PostMapping("/")
	public void addParticipant (@RequestBody ParticipantDTO participantDTO , BindingResult bindingResult)throws BindException {
		addParticipantValidator.validate(participantDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		
		participantService.addParticipant(participantDTO);
	}
	
	@GetMapping("/get-all")
	public List<ParticipantDTO> getAll (){
		return participantService.getAll();
	}
}
