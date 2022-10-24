package br.com.ribeiroribas.worldcupqatar.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class QatarCupMatch extends Match {

    private LocalDate matchDate;
    private LocalTime matchTime;

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    @Override
    public String toString() {
        return "MatchCupQatar{" +
                "matchDate=" + matchDate +
                ", matchTime=" + matchTime +
                ", team1='" + getTeam1() +
                ", scoreTeam1=" + getScoreTeam1() +
                ", team2='" + getTeam2() +
                ", scoreTeam2=" + getScoreTeam2() +
                '}';
    }
}
