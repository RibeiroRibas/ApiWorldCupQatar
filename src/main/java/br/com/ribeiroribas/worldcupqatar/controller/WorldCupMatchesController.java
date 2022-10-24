package br.com.ribeiroribas.worldcupqatar.controller;

import br.com.ribeiroribas.worldcupqatar.model.QatarCupMatch;
import br.com.ribeiroribas.worldcupqatar.service.WorldCupMatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qatar")
public class WorldCupMatchesController {

    @Autowired
    private WorldCupMatchesService worldCupMatchesService;

    @GetMapping("/{round}")
    private List<QatarCupMatch> getByRound(@PathVariable String round){
        return worldCupMatchesService.getCupMatchesByRound(round);
    }

}
