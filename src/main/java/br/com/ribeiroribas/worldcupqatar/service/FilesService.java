package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.exceptions.ResourceFileException;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.THE_RESOURCE_FILE_IS_INVALID;

@Service
public class FilesService {

    protected static final String GROUP_STAGE_TXT = "/groupstage.txt";
    protected static final String ROUND_OF_16_TXT = "/roundOf16.txt";
    protected static final String ROUND_OF_8_TXT = "/roundOf8.txt";
    protected static final String SEMI_FINAL_TXT = "/semiFinal.txt";
    protected static final String FINAL_TXT = "/final.txt";

    @Autowired
    private ObjectMapper mapper;

    protected List<QatarCupMatch> getCupMatches(String fileName) {
        List<QatarCupMatch> matchesCup = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new ResourceFileException(THE_RESOURCE_FILE_IS_INVALID);
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String json = br.readLine();
                mapper.registerModule(new JavaTimeModule());
                if (json != null)
                    matchesCup = Arrays.asList(mapper.readValue(json, QatarCupMatch[].class));
            } catch (IOException e) {
                throw new ResourceFileException(THE_RESOURCE_FILE_IS_INVALID);
            }
        }
        return matchesCup;
    }

    public List<QatarCupMatch> getCupMatches() {
        List<String> filesName = Arrays.asList(GROUP_STAGE_TXT, ROUND_OF_16_TXT, ROUND_OF_8_TXT, SEMI_FINAL_TXT, FINAL_TXT);
        List<QatarCupMatch> cupMatches = new ArrayList<>();
        filesName.forEach(file ->
                cupMatches.addAll(getCupMatches(file))
        );
        return cupMatches;
    }
}
