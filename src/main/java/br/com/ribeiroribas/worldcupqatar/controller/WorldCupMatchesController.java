package br.com.ribeiroribas.worldcupqatar.controller;

import br.com.ribeiroribas.worldcupqatar.controller.dto.MatchesByDateDto;
import br.com.ribeiroribas.worldcupqatar.model.Match;
import br.com.ribeiroribas.worldcupqatar.service.WorldCupMatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/qatar")
public class WorldCupMatchesController {

    @Autowired
    private WorldCupMatchesService worldCupMatchesService;

    @GetMapping("/{date}")
    private MatchesByDateDto getByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return worldCupMatchesService.getCupMatchesByDate(date);
    }

    @GetMapping
    private List<Match> getCupMatchesGroupStage() {
        return worldCupMatchesService.getCupMatchesGroupStage();
    }

    @GetMapping("/final/match")
    private Match getFinalMatch() {
        return worldCupMatchesService.getFinalMatch();
    }
}
