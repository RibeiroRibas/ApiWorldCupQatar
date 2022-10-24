package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.exceptions.RoundInvalidException;
import br.com.ribeiroribas.worldcupqatar.model.MatchQualifier;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.INVALID_ROUND_NUMBER;

@Service
public class FilesService {


    protected static final String GROUP_STAGE_TXT = "groupStage.txt";
    protected static final String ROUND_OF_16_TXT = "roundOf16.txt";
    protected static final String ROUND_OF_8_TXT = "roundOf8.txt";
    protected static final String SEMI_FINAL_TXT = "semiFinal.txt";
    protected static final String FINAL_TXT = "final.txt";
    protected static final String MATCHES_QUALIFIER_TXT = "matchesQualifier.txt";

    @Autowired
    private ObjectMapper mapper;

    protected String getFileName(String round) {
        if (round.equals("4")) return ROUND_OF_16_TXT;
        if (round.equals("5")) return ROUND_OF_8_TXT;
        if (round.equals("6")) return SEMI_FINAL_TXT;
        if (round.equals("7")) return FINAL_TXT;
        if (round.equals("1") || round.equals("2") || round.equals("3")){
            return GROUP_STAGE_TXT;
        }else {
            throw new RoundInvalidException(INVALID_ROUND_NUMBER);
        }
    }

    protected List<QatarCupMatch> getMatchesCup(String fileTxt) {
        List<QatarCupMatch> matchesCup = new ArrayList<>();
        File file = new File(fileTxt);
        if (file.isFile())
            try (FileReader fileReader = new FileReader(file);
                 BufferedReader br = new BufferedReader(fileReader)) {
                String json = br.readLine();
                mapper.registerModule(new JavaTimeModule());
                matchesCup = Arrays.asList(mapper.readValue(json, QatarCupMatch[].class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        return matchesCup;
    }

    protected List<MatchQualifier> getMatchesQualifier() {
        List<MatchQualifier> matchesQualifier = new ArrayList<>();
        File file = new File(MATCHES_QUALIFIER_TXT);
        try (FileReader fileReader = new FileReader(file);
             BufferedReader br = new BufferedReader(fileReader)) {
            String json = br.readLine();
            json = json.replace("-", "");
            matchesQualifier = Arrays.asList(mapper.readValue(json, MatchQualifier[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchesQualifier;
    }

    protected void writeData(List<QatarCupMatch> cupMatches, String fileName) throws IOException {
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(cupMatches);
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
            fileWriter.flush();
        }
    }

}
