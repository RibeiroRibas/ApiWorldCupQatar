package br.com.ribeiroribas.worldcupqatar.model;

import java.time.LocalTime;

public class MatchQualifier {

    private String team1;
    private Integer scoreTeam1;
    private String team2;
    private Integer scoreTeam2;
    private LocalTime matchTime;
    private String groupTeam1;
    private String groupTeam2;

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
}
