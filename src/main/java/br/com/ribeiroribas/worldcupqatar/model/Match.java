package br.com.ribeiroribas.worldcupqatar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String team1;
    private Integer scoreTeam1;
    @Column(nullable = false)
    private String team2;
    private Integer scoreTeam2;
    @Column(nullable = false)
    private LocalTime matchTime;
    private String groupTeam1;
    private String groupTeam2;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String stage;

    public Match() {
    }

    public Match(String team1, Integer scoreTeam1, String team2, Integer scoreTeam2, LocalTime matchTime, String groupTeam1, String groupTeam2, LocalDate date, String stage) {
        this.team1 = team1;
        this.scoreTeam1 = scoreTeam1;
        this.team2 = team2;
        this.scoreTeam2 = scoreTeam2;
        this.matchTime = matchTime;
        this.groupTeam1 = groupTeam1;
        this.groupTeam2 = groupTeam2;
        this.date = date;
        this.stage = stage;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (groupTeam1 == null)
            return null;
        return groupTeam1;
    }

    public void setGroupTeam1(String groupTeam1) {
        this.groupTeam1 = groupTeam1;
    }

    public String getGroupTeam2() {
        if(groupTeam2 == null)
            return null;
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

    public void update(Match match) {
        this.team1 = match.getTeam1();
        this.team2 = match.getTeam2();
        this.scoreTeam1 = match.getScoreTeam1();
        this.scoreTeam2 = match.getScoreTeam2();
        this.groupTeam1 = match.getGroupTeam1();
        this.groupTeam2 = match.getGroupTeam2();
    }
}
