package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.exceptions.DataScrapingErrorException;
import br.com.ribeiroribas.worldcupqatar.controller.exceptions.ResourceFileException;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.THE_RESOURCE_FILE_IS_INVALID;
import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.THE_UPDATE_FILE_IS_INVALID;

@Service
public class FilesService {

    protected static final String GROUP_STAGE_TXT = "/groupstage.txt";
    protected static final String ROUND_OF_16_TXT = "/roundOf16.txt";
    protected static final String ROUND_OF_8_TXT = "/roundOf8.txt";
    protected static final String SEMI_FINAL_TXT = "/semiFinal.txt";
    protected static final String FINAL_TXT = "/final.txt";
    private static final String FASE_DE_GRUPOS = "FASE DE GRUPOS";
    private static final String OITAVAS_DE_FINAL = "OITAVAS DE FINAL";
    private static final String QUARTAS_DE_FINAL = "QUARTAS DE FINAL";
    private static final String SEMI_FINAL = "SEMI FINAL";
    private static final String FINAL = "FINAL";

    @Autowired
    private ObjectMapper mapper;

    protected List<QatarCupMatch> getCupMatches(String fileTxt) {
        List<QatarCupMatch> matchesCup = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream(fileTxt);
        if (inputStream == null) {
            throw new ResourceFileException(THE_RESOURCE_FILE_IS_INVALID);
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String json = br.readLine();
                mapper.registerModule(new JavaTimeModule());
                matchesCup = Arrays.asList(mapper.readValue(json, QatarCupMatch[].class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return matchesCup;
    }

    protected void writeData(List<QatarCupMatch> cupMatches, String fileName) throws IOException {
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(cupMatches);
        if (getStageName(fileName) == null)
            throw new DataScrapingErrorException(THE_UPDATE_FILE_IS_INVALID);
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fileWriter)) {
            bw.write(json);
            bw.flush();
        } catch (Exception e) {
            throw new DataScrapingErrorException(THE_UPDATE_FILE_IS_INVALID);
        }
    }

    public List<QatarCupMatch> getCupMatches() {
        List<String> filesName = Arrays.asList(GROUP_STAGE_TXT, ROUND_OF_16_TXT, ROUND_OF_8_TXT, SEMI_FINAL_TXT, FINAL_TXT);
        List<QatarCupMatch> cupMatches = new ArrayList<>();
        filesName.forEach(file ->
                cupMatches.addAll(getCupMatches(file))
        );
        return cupMatches;
    }

    public String getStageName(String file) {
        if (file.equals(GROUP_STAGE_TXT)) return FASE_DE_GRUPOS;
        if (file.equals(ROUND_OF_16_TXT)) return OITAVAS_DE_FINAL;
        if (file.equals(ROUND_OF_8_TXT)) return QUARTAS_DE_FINAL;
        if (file.equals(SEMI_FINAL_TXT)) return SEMI_FINAL;
        if (file.equals(FINAL_TXT)) return FINAL;
        return null;
    }

    public String getGroup(String team1) {
        Map<String, String> teams = new HashMap<>();
        teams.put("Alemanha", "GRUPO E");
        teams.put("Arábia Saudita", "GRUPO C");
        teams.put("Argentina", "GRUPO C");
        teams.put("Austrália", "GRUPO D");
        teams.put("Bélgica", "GRUPO F");
        teams.put("Brasil", "GRUPO G");
        teams.put("Camarões", "GRUPO G");
        teams.put("Canadá", "GRUPO F");
        teams.put("Catar", "GRUPO A");
        teams.put("Coreia do Sul", "GRUPO H");
        teams.put("Costa Rica", "GRUPO E");
        teams.put("Croácia", "GRUPO F");
        teams.put("Dinamarca", "GRUPO D");
        teams.put("Equador", "GRUPO A");
        teams.put("Espanha", "GRUPO E");
        teams.put("EUA", "GRUPO B");
        teams.put("França", "GRUPO D");
        teams.put("Gana", "GRUPO H");
        teams.put("Países Baixos", "GRUPO A");
        teams.put("Inglaterra", "GRUPO B");
        teams.put("Irã", "GRUPO B");
        teams.put("Japão", "GRUPO E");
        teams.put("Marrocos", "GRUPO F");
        teams.put("México", "GRUPO C");
        teams.put("País de Gales", "GRUPO B");
        teams.put("Polônia", "GRUPO C");
        teams.put("Portugal", "GRUPO H");
        teams.put("Senegal", "GRUPO A");
        teams.put("Sérvia", "GRUPO G");
        teams.put("Suíça", "GRUPO G");
        teams.put("Tunísia", "GRUPO D");
        teams.put("Uruguai", "GRUPO H");
        for (Map.Entry<String, String> entry : teams.entrySet()) {
            if (team1.equals(entry.getKey()))
                return entry.getValue();
        }
        return "";
    }
}
