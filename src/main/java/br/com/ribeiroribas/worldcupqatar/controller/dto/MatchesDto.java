package br.com.ribeiroribas.worldcupqatar.controller.dto;

import br.com.ribeiroribas.worldcupqatar.model.Match;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;

import java.util.List;

public class MatchesDto {

    private Match match;
    private List<Match> matchesTeam1;
    private List<Match> matchesTeam2;

    public MatchesDto() {
    }

    public MatchesDto(Match match, List<QatarCupMatch> matchesTeam1, List<QatarCupMatch> matchesTeam2) {
        this.match = match;
        this.matchesTeam1 = Match.convert(matchesTeam1);
        this.matchesTeam2 = Match.convert(matchesTeam2);
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Match> getMatchesTeam1() {
        return matchesTeam1;
    }

    public void setMatchesTeam1(List<Match> matchesTeam1) {
        this.matchesTeam1 = matchesTeam1;
    }

    public List<Match> getMatchesTeam2() {
        return matchesTeam2;
    }

    public void setMatchesTeam2(List<Match> matchesTeam2) {
        this.matchesTeam2 = matchesTeam2;
    }
}
