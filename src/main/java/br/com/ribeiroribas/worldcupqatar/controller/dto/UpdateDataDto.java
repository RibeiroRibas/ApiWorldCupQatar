package br.com.ribeiroribas.worldcupqatar.controller.dto;

import javax.validation.constraints.NotNull;

public class UpdateDataDto {

    @NotNull
    private String round;
    @NotNull
    private String data;

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
