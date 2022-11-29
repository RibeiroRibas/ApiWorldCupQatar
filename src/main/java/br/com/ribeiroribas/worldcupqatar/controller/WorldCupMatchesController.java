package br.com.ribeiroribas.worldcupqatar.controller;

import br.com.ribeiroribas.worldcupqatar.controller.dto.MatchDto;
import br.com.ribeiroribas.worldcupqatar.controller.dto.MatchesByDateDto;
import br.com.ribeiroribas.worldcupqatar.model.Match;
import br.com.ribeiroribas.worldcupqatar.service.WorldCupMatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qatar")
public class WorldCupMatchesController {

    @Autowired
    private WorldCupMatchesService worldCupMatchesService;

    @GetMapping("/matches-by-date/{date}")
    private MatchesByDateDto getByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return worldCupMatchesService.getCupMatchesByDate(date);
    }

    @GetMapping("/matches-group-stage")
    private List<MatchDto> getCupMatchesGroupStageUpdate() {
        List<Match> matches = worldCupMatchesService.getCupMatchesGroupStage();
        return MatchDto.convert(matches);
    }

    @GetMapping("/all-matches")
    private List<MatchDto> getCupMatchesGroupStage() {
        List<Match> matches = worldCupMatchesService.getCupMatchesGroupStage();
        return MatchDto.convert(matches);
    }

    @GetMapping("/final-match")
    private MatchDto getFinalMatch() {
        return new MatchDto(worldCupMatchesService.getFinalMatch());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Match> byId = worldCupMatchesService.getById(id);
        if (byId.isPresent())
            return ResponseEntity.ok(byId.get());
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    private ResponseEntity<?> update(
            @RequestBody Match match
    ) {
        worldCupMatchesService.update(match);
        return ResponseEntity.ok().build();
    }

}
