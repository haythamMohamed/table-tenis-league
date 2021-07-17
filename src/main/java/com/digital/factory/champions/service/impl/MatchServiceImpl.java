package com.digital.factory.champions.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.factory.champions.dao.MatchDAO;
import com.digital.factory.champions.dto.MatchDTO;
import com.digital.factory.champions.dto.MatchRequestDTO;
import com.digital.factory.champions.dto.MatchResultDTO;
import com.digital.factory.champions.model.League;
import com.digital.factory.champions.model.LeagueParticipant;
import com.digital.factory.champions.model.Match;
import com.digital.factory.champions.model.MatchParticipant;
import com.digital.factory.champions.model.MatchResult;
import com.digital.factory.champions.model.Participant;
import com.digital.factory.champions.model.Round;
import com.digital.factory.champions.service.LeagueParticipantService;
import com.digital.factory.champions.service.MatchParticipantService;
import com.digital.factory.champions.service.MatchResultService;
import com.digital.factory.champions.service.MatchService;
import com.digital.factory.champions.service.RoundService;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

	@Autowired MatchDAO matchDAO;
	@Autowired LeagueParticipantService leagueParticipantService;
	@Autowired MatchParticipantService matchParticipantService ;
	@Autowired MatchResultService matchResultService;
	@Autowired RoundService roundService ;
	
	@Override
	public void createRound(Integer leagueId, Integer roundNumber) {
		Round round = new Round();
		round.setClosed(false);
		round.setRank(roundNumber);
		round.setLeague(new League(leagueId));
		roundService.save(round);
		
		List<LeagueParticipant> leagueParticipants = leagueParticipantService.getByLeague(new League(leagueId));
		List<Match> savedMatchs = new ArrayList<Match>();

		for (int i = 0; i < 3; i++) {
			Match match = new Match();
			match.setActionDate(new Date());
			match.setLeague(new League(leagueId));
			match.setRound(round);

			savedMatchs.add(matchDAO.save(match));
		}

		int lastMatchIdUsed = 0 ;
		for (int i = 0; i < 6; i++) {
			if(i > 1 && i % 2 == 0) {
				lastMatchIdUsed ++ ;
			}
			MatchParticipant matchParticipant = new MatchParticipant();
			matchParticipant.setMatch(savedMatchs.get(lastMatchIdUsed));
			matchParticipant.setParticipant(leagueParticipants.get(i).getParticipant());
			
			matchParticipantService.save(matchParticipant);
		}

	}

	@Override
	public List<MatchDTO> getMatchsPerLeagueAndFirstRound(Integer leagueId) {
		List<Match> matchs = matchDAO.findByLeagueAndRound(new League(leagueId), roundService.getRoundById(1).get());
		if(matchs != null && !matchs.isEmpty()) {
			return matchs.stream().map(MatchDTO::beanToDTO).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public void submitResult(MatchResultDTO matchResultDTO) {
		Match match = getById(matchResultDTO.getMatchId()).get();
		match.setFinished(true);
		matchDAO.save(match);
		
		int[] gamesScore = new int[] {0,0};
		matchResultDTO.getGameResults().stream().forEach(GameResult -> {
			MatchResult playerOnematchResult = new MatchResult();
			playerOnematchResult.setGame(GameResult.getGame());
			playerOnematchResult.setMatchParticipant(matchParticipantService.getByMatchAndParticipant(matchResultDTO.getMatchId(), GameResult.getFirstParticipantScore().getParticipantId()));
			playerOnematchResult.setScore(GameResult.getFirstParticipantScore().getScore());
			matchResultService.save(playerOnematchResult);
			
			MatchResult playerTwomatchResult = new MatchResult();
			playerTwomatchResult.setGame(GameResult.getGame());
			playerTwomatchResult.setMatchParticipant(matchParticipantService.getByMatchAndParticipant(matchResultDTO.getMatchId(), GameResult.getSecondParticipantScore().getParticipantId()));
			playerTwomatchResult.setScore(GameResult.getSecondParticipantScore().getScore());
			matchResultService.save(playerTwomatchResult);
			
			if(GameResult.getFirstParticipantScore().getScore() > GameResult.getSecondParticipantScore().getScore()) {
				gamesScore[0] ++ ;
			}else {
				gamesScore[1] ++ ;
			}
		});
		
		if(gamesScore[0] > gamesScore[1]) {
			MatchParticipant matchParticipant=  matchParticipantService.getByMatchAndParticipant(matchResultDTO.getMatchId(), matchResultDTO.getGameResults().get(0).getFirstParticipantScore().getParticipantId());
			matchParticipant.setIsWinner(true);
			
			matchParticipantService.save(matchParticipant);
		}else {
			
			MatchParticipant matchParticipant=  matchParticipantService.getByMatchAndParticipant(matchResultDTO.getMatchId(), matchResultDTO.getGameResults().get(0).getSecondParticipantScore().getParticipantId());
			matchParticipant.setIsWinner(true);			
			
			matchParticipantService.save(matchParticipant);
		}
	}

	@Override
	public Optional<Match> getById(Integer matchId) {
		return matchDAO.findById(matchId);
	}

	@Override
	public List<Match> getByRound(Integer roundId) {
		return matchDAO.findByRound(new Round(roundId));
	}

	@Override
	public void submitNewMatch(MatchRequestDTO matchRequestDTO) {
		Round round = roundService.getRoundById(matchRequestDTO.getRoundDTO().getId()).get();
		
		Match match = new Match();
		match.setActionDate(matchRequestDTO.getTime());
		match.setFinished(false);
		match.setLeague(round.getLeague());
		match.setRound(round);
		
		matchDAO.save(match);
		
		for (int i = 0 ; i < matchRequestDTO.getParticipantDTOs().length ; i ++) {
			MatchParticipant matchParticipant = new MatchParticipant();
			matchParticipant.setMatch(match);
			matchParticipant.setParticipant(new Participant(matchRequestDTO.getParticipantDTOs()[i].getId()));
			
			matchParticipantService.save(matchParticipant);
		}
	}
}
