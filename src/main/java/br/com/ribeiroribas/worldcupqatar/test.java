package br.com.ribeiroribas.worldcupqatar;

import br.com.ribeiroribas.worldcupqatar.controller.exceptions.DataScrapingErrorException;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.THE_UPDATE_FILE_IS_INVALID;

public class test {
    public static void main(String[] args) throws IOException {
        writeData("target/classes/teste.txt");
    }
    private static void writeData(String fileName) throws IOException {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fileWriter)) {
            bw.write("json");
            bw.flush();
        } catch (Exception e) {
            throw new DataScrapingErrorException(THE_UPDATE_FILE_IS_INVALID);
        }
    }
}
