package br.com.ribeiroribas.worldcupqatar.controller.dto;

import br.com.ribeiroribas.worldcupqatar.model.Match;

import java.util.List;

public class MatchesDto {

    private MatchDto match;
    private List<MatchDto> matchesTeam1;
    private List<MatchDto> matchesTeam2;

    public MatchesDto() {
    }

    public MatchesDto(Match match, List<Match> matchesTeam1, List<Match> matchesTeam2) {
        this.match = new MatchDto(match);
        this.matchesTeam1 = MatchDto.convert(matchesTeam1);
        this.matchesTeam2 = MatchDto.convert(matchesTeam2);
    }

    public MatchDto getMatch() {
        return match;
    }

    public void setMatch(MatchDto match) {
        this.match = match;
    }

    public List<MatchDto> getMatchesTeam1() {
        return matchesTeam1;
    }

    public void setMatchesTeam1(List<MatchDto> matchesTeam1) {
        this.matchesTeam1 = matchesTeam1;
    }

    public List<MatchDto> getMatchesTeam2() {
        return matchesTeam2;
    }

    public void setMatchesTeam2(List<MatchDto> matchesTeam2) {
        this.matchesTeam2 = matchesTeam2;
    }
}
