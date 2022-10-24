package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.dto.TeamsDto;
import br.com.ribeiroribas.worldcupqatar.model.MatchQualifier;
import br.com.ribeiroribas.worldcupqatar.model.Punctuation;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import br.com.ribeiroribas.worldcupqatar.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private QualifierMatchesService qualifierMatchesService;
    @Autowired
    private WorldCupMatchesService worldCupMatchesService;
    @Autowired
    private PunctuationService punctuationService;

    public Team getByName(String teamName) {

        Team team = new Team();
        team.setName(teamName);

        setMatchesAndPointsQualifier(team);

        setCupMatchesAndPoints(team);

        return team;
    }

    private void setCupMatchesAndPoints(Team team) {
        List<QatarCupMatch> cupMatches = worldCupMatchesService.getCupMatchesGroupStageByName(team.getName());
        team.setCupMatches(cupMatches);
        Punctuation punctuation = punctuationService.getPunctuation(new ArrayList<>(cupMatches), team.getName());
        team.setCupPunctuation(punctuation);
    }

    private void setMatchesAndPointsQualifier(Team team) {
        List<MatchQualifier> matchesQualifier = qualifierMatchesService.getMatchesQualifier(team.getName());
        team.setQualifierMatches(matchesQualifier);
        Punctuation punctuation = punctuationService.getPunctuation(new ArrayList<>(matchesQualifier), team.getName());
        team.setQualifierPunctuation(punctuation);
    }

    public List<TeamsDto> getCupMatchesByRound(String round) {
        List<QatarCupMatch> cupMatches = worldCupMatchesService.getCupMatchesByRound(round);
        List<TeamsDto> teamsDto = new ArrayList<>();
        cupMatches.forEach(match -> setTeamDto(teamsDto, match));
        return teamsDto;
    }

    private void setTeamDto(List<TeamsDto> teamsDto, QatarCupMatch match) {
        String teamName1 = match.getTeam1();
        Team team1 = getByName(teamName1);
        String teamName2 = match.getTeam2();
        Team team2 = getByName(teamName2);
        teamsDto.add(new TeamsDto(team1, team2));
    }
}
