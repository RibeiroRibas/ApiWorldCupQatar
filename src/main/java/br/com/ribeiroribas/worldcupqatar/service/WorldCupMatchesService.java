package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.exceptions.TeamNotFoundException;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.TEAM_NOT_FOUND;
import static br.com.ribeiroribas.worldcupqatar.service.FilesService.GROUP_STAGE_TXT;

@Service
public class WorldCupMatchesService {

    @Autowired
    private FilesService filesService;

    public List<QatarCupMatch> getCupMatchesByRound(String round) {

        List<QatarCupMatch> cupMatches;

        if (round.equals("1") || round.equals("2") || round.equals("3")) {
            int roundNumber = Integer.parseInt(round);
            cupMatches = getCupMatchesGroupStage(roundNumber);
        } else {
            String fileNameTxt = filesService.getFileName(round);
            cupMatches = filesService.getMatchesCup(fileNameTxt);
        }

        return cupMatches;
    }

    protected List<QatarCupMatch> getCupMatchesGroupStage(int roundNumber) {
        LocalDate startDate;
        LocalDate endDate;
        if (roundNumber == 1) {
            startDate = LocalDate.of(2022, 11, 20);
            endDate = LocalDate.of(2022, 11, 24);
        } else if (roundNumber == 2) {
            startDate = LocalDate.of(2022, 11, 25);
            endDate = LocalDate.of(2022, 11, 28);
        } else {
            startDate = LocalDate.of(2022, 11, 29);
            endDate = LocalDate.of(2022, 12, 2);
        }
        List<QatarCupMatch> cupMatches = filesService.getMatchesCup(GROUP_STAGE_TXT);
        cupMatches = getMatchesCupByDate(startDate, endDate, cupMatches);
        return cupMatches;
    }

    private List<QatarCupMatch> getMatchesCupByDate(LocalDate startDate, LocalDate endDate, List<QatarCupMatch> cupMatches) {
        return cupMatches.stream().filter(match ->
                !match.getMatchDate().isBefore(startDate)
                        && !match.getMatchDate().isAfter(endDate)
        ).collect(Collectors.toList());
    }

    protected List<QatarCupMatch> getCupMatchesGroupStageByName(String teamName) {
        List<QatarCupMatch> cupMatches = filesService.getMatchesCup(GROUP_STAGE_TXT);
        cupMatches = getCupMatchesByName(teamName, cupMatches);
        if (cupMatches.isEmpty())
            throw new TeamNotFoundException(TEAM_NOT_FOUND);
        return cupMatches;
    }

    private List<QatarCupMatch> getCupMatchesByName(String teamName, List<QatarCupMatch> matchesCup) {
        return matchesCup.stream().filter(match ->
                        match.getTeam1().equals(teamName)
                                || match.getTeam2().equals(teamName))
                .collect(Collectors.toList());
    }
}
