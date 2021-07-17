package com.digital.factory.champions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dto.LeagueDTO;
import com.digital.factory.champions.dto.MatchDTO;
import com.digital.factory.champions.dto.MatchRequestDTO;
import com.digital.factory.champions.dto.MatchResultDTO;
import com.digital.factory.champions.dto.MatchResultDTO.GameResult;
import com.digital.factory.champions.dto.MatchResultDTO.participantGameScore;
import com.digital.factory.champions.dto.ParticipantDTO;
import com.digital.factory.champions.dto.RoundDTO;
import com.digital.factory.champions.dto.SubmitLeagueChampionDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@Commit
@TestMethodOrder(OrderAnnotation.class)
public class IntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Order(1)
	@Test
	public void testSaveLeagueApi () {
		
		LeagueDTO leagueDTO = new LeagueDTO();
		leagueDTO.setName("League of 2021");
		leagueDTO.setStartDate(new Date());
		
		HttpEntity<LeagueDTO> httpEntity = new HttpEntity<LeagueDTO>(leagueDTO);
		
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/league/", HttpMethod.POST, httpEntity, Void.class);
	
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Order(2)
	@Test
	public void testSaveParticipantApi () {
		for (int i = 0 ; i < 13 ; i ++) {			
			ParticipantDTO participantDTO = new ParticipantDTO();
			participantDTO.setDateOfBirth(new Date());
			participantDTO.setEmail("haytham@gmail.com" + i);
			participantDTO.setFullName("haytham Mohamed" + i);
			participantDTO.setLeagueId(1);
			
			HttpEntity<ParticipantDTO> httpEntity = new HttpEntity<ParticipantDTO>(participantDTO);
			ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/participant/", HttpMethod.POST, httpEntity, Void.class);
			
			assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		}
	}
	
	@Order(3)
	@Test
	public void testGetAllParticipants () {
		ResponseEntity<List<ParticipantDTO>> responseEntity = testRestTemplate.exchange("/participant/get-all", HttpMethod.GET, null, new ParameterizedTypeReference<List<ParticipantDTO>>(){});
	
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().size(), 13);
		assertEquals(responseEntity.getBody().get(0).getEmail(), "haytham@gmail.com0");
		assertEquals(responseEntity.getBody().get(0).getFullName(), "haytham Mohamed0");
	}
	
	@Order(4)
	@Test
	public void testFailAddParticipantToLeague () {
		ParticipantDTO participantDTO = new ParticipantDTO();
		participantDTO.setDateOfBirth(new Date());
		participantDTO.setEmail("haytham@gmail.com");
		participantDTO.setFullName("haytham Mohamed" );
		participantDTO.setLeagueId(1);
		
		HttpEntity<ParticipantDTO> httpEntity = new HttpEntity<ParticipantDTO>(participantDTO);
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/participant/", HttpMethod.POST, httpEntity, Void.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Order(5)
	@Test
	public void testGetFirstRoundMatchs () {
		ResponseEntity<List<MatchDTO>> responseEntity = getFirstRoundMatches();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().size(), 3);
	}
	
	private ResponseEntity<List<MatchDTO>> getFirstRoundMatches (){
		return testRestTemplate.exchange("/match/1", HttpMethod.GET, null, new ParameterizedTypeReference<List<MatchDTO>>() {});

	}
	
	@Order(6)
	@Test
	public void testSubmitMatchResult () {
		ResponseEntity<List<MatchDTO>> firstRoundResponse = getFirstRoundMatches();
		MatchDTO firstMatch = firstRoundResponse.getBody().get(0);
		MatchResultDTO matchResultDTO = generateMatchResultDTO(firstMatch);
		ResponseEntity<Void> responseEntity = sumbitMatchResult(matchResultDTO);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	private ResponseEntity<Void> sumbitMatchResult (MatchResultDTO matchResultDTO){
		HttpEntity<MatchResultDTO> httpEntity = new HttpEntity<MatchResultDTO>(matchResultDTO);
		return testRestTemplate.exchange("/match/submit-result", HttpMethod.POST, httpEntity, Void.class);
	}
	
	private MatchResultDTO generateMatchResultDTO(MatchDTO firstMatch) {
		List<GameResult> gameResults = new ArrayList<>();
		
		participantGameScore player1Game1Score = new participantGameScore();
		player1Game1Score.setParticipantId(firstMatch.getParticipantDTOs().get(0).getId());
		player1Game1Score.setScore(11);
		
		participantGameScore player2Game1Score = new participantGameScore();
		player2Game1Score.setParticipantId(firstMatch.getParticipantDTOs().get(1).getId());
		player2Game1Score.setScore(8);
		
		GameResult gameOneResult = new GameResult();
		gameOneResult.setGame(1);
		gameOneResult.setFirstParticipantScore(player1Game1Score);
		gameOneResult.setSecondParticipantScore(player2Game1Score);
		
		gameResults.add(gameOneResult);
		
		participantGameScore player1Game2Score = new participantGameScore();
		player1Game2Score.setParticipantId(firstMatch.getParticipantDTOs().get(0).getId());
		player1Game2Score.setScore(7);
		
		participantGameScore player2Game2Score = new participantGameScore();
		player2Game2Score.setParticipantId(firstMatch.getParticipantDTOs().get(1).getId());
		player2Game2Score.setScore(11);
		
		GameResult gameTwoResult = new GameResult();
		gameTwoResult.setGame(2);
		gameTwoResult.setFirstParticipantScore(player1Game2Score);
		gameTwoResult.setSecondParticipantScore(player2Game2Score);
		
		gameResults.add(gameTwoResult);
		
		participantGameScore player1Game3Score = new participantGameScore();
		player1Game3Score.setParticipantId(firstMatch.getParticipantDTOs().get(0).getId());
		player1Game3Score.setScore(13);
		
		participantGameScore player2Game3Score = new participantGameScore();
		player2Game3Score.setParticipantId(firstMatch.getParticipantDTOs().get(1).getId());
		player2Game3Score.setScore(11);
		
		GameResult gameThreeResult = new GameResult();
		gameThreeResult.setGame(3);
		gameThreeResult.setFirstParticipantScore(player1Game3Score);
		gameThreeResult.setSecondParticipantScore(player2Game3Score);
		
		gameResults.add(gameThreeResult);
		
		participantGameScore player1Game4Score = new participantGameScore();
		player1Game4Score.setParticipantId(firstMatch.getParticipantDTOs().get(0).getId());
		player1Game4Score.setScore(11);
		
		participantGameScore player2Game4Score = new participantGameScore();
		player2Game4Score.setParticipantId(firstMatch.getParticipantDTOs().get(1).getId());
		player2Game4Score.setScore(9);
		
		GameResult gameFourResult = new GameResult();
		gameFourResult.setGame(4);
		gameFourResult.setFirstParticipantScore(player1Game4Score);
		gameFourResult.setSecondParticipantScore(player2Game4Score);
		
		gameResults.add(gameFourResult);
		
		MatchResultDTO matchResultDTO = new MatchResultDTO();
		matchResultDTO.setMatchId(firstMatch.getId());
		matchResultDTO.setGameResults(gameResults);
		
		return matchResultDTO;
	}

	@Order(7)
	@Test
	public void testFailedCloseRoundDueToUnfinishedMatches () {
		ResponseEntity<List<MatchDTO>> firstRoundResponse = getFirstRoundMatches();
		MatchDTO firstMatch = firstRoundResponse.getBody().get(0);
		RoundDTO roundDTO = new RoundDTO();
		roundDTO.setId(firstMatch.getRound().getId());
		
		HttpEntity<RoundDTO> httpEntity = new HttpEntity<RoundDTO>(roundDTO);
		
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/match/close-round", HttpMethod.PUT, httpEntity, Void.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

	}
	
	@Order(8)
	@Test
	public void testAddMatch () {
		RoundDTO round = new RoundDTO();
		round.setId(1);
		
		ParticipantDTO[] participants = new ParticipantDTO[2];
		
		ParticipantDTO participantDTO = new ParticipantDTO();
		participantDTO.setId(10);
		
		ParticipantDTO participantOppoisiteDTO = new ParticipantDTO();
		participantOppoisiteDTO.setId(11);
		
		participants[0] = participantDTO; 
		participants[1] = participantOppoisiteDTO ;
		
		MatchRequestDTO matchRequestDTO = new MatchRequestDTO();
		matchRequestDTO.setParticipantDTOs(participants);
		matchRequestDTO.setRoundDTO(round);
		matchRequestDTO.setTime(new Date());
		
		HttpEntity<MatchRequestDTO> httpEntity = new HttpEntity<MatchRequestDTO>(matchRequestDTO);
		
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/match/add", HttpMethod.POST, httpEntity, Void.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

	}
	
	@Order(9)
	@Test
	public void testSucessCloseRound () {
		ResponseEntity<List<MatchDTO>> firstRoundResponse = getFirstRoundMatches();
		
		MatchDTO secondMatch = firstRoundResponse.getBody().get(1);
		MatchResultDTO secondMatchResultDTO = generateMatchResultDTO(secondMatch);
		
		sumbitMatchResult(secondMatchResultDTO);
		
		MatchDTO thirdMatch = firstRoundResponse.getBody().get(2);
		MatchResultDTO thirdMatchResultDTO = generateMatchResultDTO(thirdMatch);
		sumbitMatchResult(thirdMatchResultDTO);
		
		MatchDTO forthMatch = firstRoundResponse.getBody().get(3);
		MatchResultDTO forthMatchResultDTO = generateMatchResultDTO(forthMatch);
		sumbitMatchResult(forthMatchResultDTO);
		
		RoundDTO roundDTO = new RoundDTO();
		roundDTO.setId(secondMatch.getRound().getId());
		
		HttpEntity<RoundDTO> httpEntity = new HttpEntity<RoundDTO>(roundDTO);
		
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/match/close-round", HttpMethod.PUT, httpEntity, Void.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

	}
	
	@Order(10)
	@Test
	public void testSetLeagueChampion () {
		SubmitLeagueChampionDTO submitLeagueChampionDTO = new SubmitLeagueChampionDTO();
		submitLeagueChampionDTO.setLeagueId(1);
		submitLeagueChampionDTO.setParticipantId(1);
		submitLeagueChampionDTO.setCrowningDate(new Date());
		
		HttpEntity<SubmitLeagueChampionDTO> httpEntity = new HttpEntity<SubmitLeagueChampionDTO>(submitLeagueChampionDTO);
		
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/league/champion", HttpMethod.PUT, httpEntity, Void.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

	}
}
