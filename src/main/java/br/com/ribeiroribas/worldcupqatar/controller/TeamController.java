package br.com.ribeiroribas.worldcupqatar.controller;

import br.com.ribeiroribas.worldcupqatar.controller.dto.TeamsDto;
import br.com.ribeiroribas.worldcupqatar.model.Team;
import br.com.ribeiroribas.worldcupqatar.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/{teamName1}/{teamName2}")
    private TeamsDto getByName(@PathVariable String teamName1, @PathVariable String teamName2) {
        Team team1 = teamService.getByName(teamName1);
        Team team2 = teamService.getByName(teamName2);
        return new TeamsDto(team1, team2);
    }

}
