package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.dto.MatchesByDateDto;
import br.com.ribeiroribas.worldcupqatar.controller.dto.MatchesDto;
import br.com.ribeiroribas.worldcupqatar.model.Match;
import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.ribeiroribas.worldcupqatar.service.FilesService.FINAL_TXT;
import static br.com.ribeiroribas.worldcupqatar.service.FilesService.GROUP_STAGE_TXT;

@Service
public class WorldCupMatchesService {

    @Autowired
    private FilesService filesService;

    public MatchesByDateDto getCupMatchesByDate(LocalDate date) {
        List<QatarCupMatch> cupMatches = filesService.getCupMatches();

        List<QatarCupMatch> cupMatchesByDate = cupMatches
                .stream()
                .filter(match ->
                        match.getMatchDate().equals(date))
                .collect(Collectors.toList());

        MatchesByDateDto matchesDto = new MatchesByDateDto();

        cupMatchesByDate.stream().findFirst().ifPresent(match -> {
            matchesDto.setStage(match.getStage());
        });

        List<MatchesDto> matchesByDate = new ArrayList<>();
        cupMatchesByDate.forEach(matchByDate -> {

            List<QatarCupMatch> matchesTeam1 = cupMatches.stream().filter(match ->
                    matchByDate.getTeam1().equals(match.getTeam1())
                            || matchByDate.getTeam1().equals(match.getTeam2())
            ).collect(Collectors.toList());

            List<QatarCupMatch> matchesTeam2 = cupMatches.stream().filter(match ->
                    matchByDate.getTeam2().equals(match.getTeam1())
                            || matchByDate.getTeam2().equals(match.getTeam2())

            ).collect(Collectors.toList());

            matchesByDate.add(new MatchesDto(new Match(matchByDate), matchesTeam1, matchesTeam2));

        });

        matchesDto.setMatches(matchesByDate);

        return matchesDto;
    }

    public List<Match> getCupMatchesGroupStage() {
        return filesService.getCupMatches(GROUP_STAGE_TXT)
                .stream()
                .map(Match::new).collect(Collectors.toList());
    }

    public Match getFinalMatch() {
        return filesService.getCupMatches(FINAL_TXT)
                .stream()
                .map(Match::new)
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .get();
    }
}
