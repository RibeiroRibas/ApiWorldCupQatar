package br.com.ribeiroribas.worldcupqatar.controller.dto;

import javax.validation.constraints.NotNull;

public class UpdateDataDto {

    @NotNull
    private String fileName;
    @NotNull
    private String data;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
