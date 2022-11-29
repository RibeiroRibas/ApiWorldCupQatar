package br.com.ribeiroribas.worldcupqatar.controller.dto;

import br.com.ribeiroribas.worldcupqatar.model.Match;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class MatchDto {

    private String team1;
    private Integer scoreTeam1;
    private String team2;
    private Integer scoreTeam2;
    private LocalTime matchTime;
    private String groupTeam1;
    private String groupTeam2;
    private LocalDate date;

    public MatchDto() {

    }

    public MatchDto(Match match) {
        this.team1 = match.getTeam1();
        this.team2 = match.getTeam2();
        this.scoreTeam1 = match.getScoreTeam1();
        this.scoreTeam2 = match.getScoreTeam2();
        this.groupTeam1 = match.getGroupTeam1();
        this.groupTeam2 = match.getGroupTeam2();
        this.matchTime = match.getMatchTime();
        this.date = match.getDate();
    }

    public static List<MatchDto> convert(List<Match> matches) {
        return matches.stream().map(MatchDto::new).collect(Collectors.toList());
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public Integer getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(Integer scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public Integer getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(Integer scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public String getGroupTeam1() {
        return groupTeam1;
    }

    public void setGroupTeam1(String groupTeam1) {
        this.groupTeam1 = groupTeam1;
    }

    public String getGroupTeam2() {
        return groupTeam2;
    }

    public void setGroupTeam2(String groupTeam2) {
        this.groupTeam2 = groupTeam2;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
