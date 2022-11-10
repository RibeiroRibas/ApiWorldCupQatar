package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.dto.UpdateDataDto;
import br.com.ribeiroribas.worldcupqatar.controller.exceptions.DataScrapingErrorException;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.THE_UPDATE_FILE_IS_INVALID;

@Service
public class UpdateDataService {

    @Autowired
    private FilesService filesService;

    public void update(UpdateDataDto dataDto) throws IOException {

        List<QatarCupMatch> cupMatches = getDataScraping(dataDto);

        filesService.writeData(cupMatches, dataDto.getFileName());
    }

    private List<QatarCupMatch> getDataScraping(UpdateDataDto data) {

        List<QatarCupMatch> cupMatches = new ArrayList<>();

        List<Element> elementMatch = getElementsByClass(data.getData());

        elementMatch.forEach(match -> {
            QatarCupMatch cupMatch = new QatarCupMatch();

            try {
                String team1 = match.getElementsByClass("event__participant event__participant--home").first().text();
                cupMatch.setTeam1(team1);

                String groupTeam1 = filesService.getGroup(team1);
                cupMatch.setGroupTeam1(groupTeam1);

                setScoreTeam1(match, cupMatch);

                String team2 = match.getElementsByClass("event__participant event__participant--away").first().text();
                cupMatch.setTeam2(team2);

                String groupTeam2 = filesService.getGroup(team2);
                cupMatch.setGroupTeam2(groupTeam2);

                setScoreTeam2(match, cupMatch);

                String event__time = match.getElementsByClass("event__time").first().text();
                char[] chars = event__time.toCharArray();

                setMatchDate(cupMatch, chars);

                setMatchTime(cupMatch, chars);

                cupMatch.setStage(filesService.getStageName(data.getFileName()));

                cupMatches.add(cupMatch);
            } catch (Exception e) {
                throw new DataScrapingErrorException(THE_UPDATE_FILE_IS_INVALID);
            }
        });

        return cupMatches;
    }

    private List<Element> getElementsByClass(String data) {

        Document doc = Jsoup.parse(data);

        List<Element> elementMatch = new ArrayList<>();

        elementMatch.addAll(
                doc.getElementsByClass(
                        "event__match event__match--static event__match--scheduled event__match--twoLine"
                ));
        elementMatch.addAll(
                doc.getElementsByClass(
                        "event__match event__match--static event__match--scheduled event__match--last event__match--twoLine"
                )
        );

        if (elementMatch.isEmpty())
            throw new DataScrapingErrorException(THE_UPDATE_FILE_IS_INVALID);

        return elementMatch;
    }

    private void setScoreTeam2(Element match, QatarCupMatch cupMatch) {
        if (!match.getElementsByClass("event__score event__score--away").first().text().equals("-")) {
            String scoreTeam2 = match.getElementsByClass("event__score event__score--away").first().text();
            cupMatch.setScoreTeam2(Integer.parseInt(scoreTeam2));
        }
    }

    private void setScoreTeam1(Element match, QatarCupMatch cupMatch) {
        if (!match.getElementsByClass("event__score event__score--home").first().text().equals("-")) {
            String scoreTeam1 = match.getElementsByClass("event__score event__score--home").first().text();
            cupMatch.setScoreTeam1(Integer.parseInt(scoreTeam1));
        }
    }

    private void setMatchTime(QatarCupMatch cupMatch, char[] chars) {
        String hour = chars[7] + "" + chars[8];
        LocalTime time = LocalTime.of(Integer.parseInt(hour), 0);
        cupMatch.setMatchTime(time);
    }

    private void setMatchDate(QatarCupMatch cupMatch, char[] chars) {
        String month = chars[3] + "" + chars[4];
        String day = chars[0] + "" + chars[1];
        LocalDate date = LocalDate.of(2022, Integer.parseInt(month), Integer.parseInt(day));
        cupMatch.setMatchDate(date);
    }

}
