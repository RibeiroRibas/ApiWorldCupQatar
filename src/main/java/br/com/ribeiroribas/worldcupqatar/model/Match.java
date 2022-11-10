package br.com.ribeiroribas.worldcupqatar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class Match {

    private String team1;
    private Integer scoreTeam1;
    private String team2;
    private Integer scoreTeam2;
    private LocalTime matchTime;
    private String groupTeam1;
    private String groupTeam2;
    private LocalDate date;

    public Match() {
    }

    public Match(QatarCupMatch qatarCupMatch) {
        this.team1 = qatarCupMatch.getTeam1();
        this.scoreTeam1 = qatarCupMatch.getScoreTeam1();
        this.team2 = qatarCupMatch.getTeam2();
        this.scoreTeam2 = qatarCupMatch.getScoreTeam2();
        this.matchTime = qatarCupMatch.getMatchTime();
        this.groupTeam1 = qatarCupMatch.getGroupTeam1();
        this.groupTeam2 = qatarCupMatch.getGroupTeam2();
        this.date = qatarCupMatch.getMatchDate();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static List<Match> convert(List<QatarCupMatch> cupMatchesByDate) {
        return cupMatchesByDate.stream().map(Match::new).collect(Collectors.toList());
    }

    public String getGroupTeam2() {
        return groupTeam2;
    }

    public void setGroupTeam2(String groupTeam2) {
        this.groupTeam2 = groupTeam2;
    }

    public String getGroupTeam1() {
        return groupTeam1;
    }

    public void setGroupTeam1(String groupTeam1) {
        this.groupTeam1 = groupTeam1;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public Integer getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(Integer scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public Integer getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(Integer scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public int getPoints(String teamName) {
        int homePoints = 1;
        int awayPoints = 1;

        if (scoreTeam1 > scoreTeam2) {
            homePoints = 3;
            awayPoints = 0;
        } else if (scoreTeam1 < scoreTeam2) {
            homePoints = 0;
            awayPoints = 3;
        }

        if (teamName.equals(team1))
            return homePoints;

        return awayPoints;
    }
    @JsonIgnore
    public int getDraw() {
        if (scoreTeam1 != null && scoreTeam2 != null)
            if (scoreTeam1.equals(scoreTeam2))
                return 1;
        return 0;
    }

    public int getDefeat(String teamName) {
        if (scoreTeam1 < scoreTeam2 && team1.equals(teamName)
                || scoreTeam2 < scoreTeam1 && team2.equals(teamName))
            return 1;

        return 0;
    }

    public int getVictory(String teamName) {
        if (scoreTeam1 > scoreTeam2 && team1.equals(teamName)
                || scoreTeam2 > scoreTeam1 && team2.equals(teamName))
            return 1;

        return 0;
    }

    public int getGc(String teamName) {
        if (team1.equals(teamName))
            return scoreTeam2;

        return scoreTeam1;
    }

    public int getGp(String teamName) {
        if (team1.equals(teamName))
            return scoreTeam1;

        return scoreTeam2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "team1='" + team1 + '\'' +
                ", scoreTeam1=" + scoreTeam1 +
                ", team2='" + team2 + '\'' +
                ", scoreTeam2=" + scoreTeam2 +
                ", matchTime=" + matchTime +
                ", group=" + groupTeam1 +
                '}';
    }
}
