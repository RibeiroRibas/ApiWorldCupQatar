package br.com.ribeiroribas.worldcupqatar.controller.dto;

import java.util.List;

public class MatchesByDateDto {

    private String stage = "";
    private List<MatchesDto> matches;

    public MatchesByDateDto() {
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<MatchesDto> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchesDto> matches) {
        this.matches = matches;
    }
}
