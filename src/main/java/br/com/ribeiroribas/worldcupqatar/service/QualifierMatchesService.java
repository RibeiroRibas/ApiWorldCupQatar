package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.exceptions.TeamNotFoundException;
import br.com.ribeiroribas.worldcupqatar.model.MatchQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.ribeiroribas.worldcupqatar.controller.exceptions.ExceptionMessages.TEAM_NOT_FOUND;

@Service
public class QualifierMatchesService {

    @Autowired
    private FilesService filesService;

    private List<MatchQualifier> getMatchesQualifierByName(String teamName, List<MatchQualifier> matchesQualifier) {
        return matchesQualifier.stream().filter(match ->
                        match.getTeam1().equals(teamName)
                                || match.getTeam2().equals(teamName))
                .collect(Collectors.toList());
    }

    protected List<MatchQualifier> getMatchesQualifier(String teamName) {
        List<MatchQualifier> matchesQualifier = filesService.getMatchesQualifier();
        List<MatchQualifier> matchesQualifierByName = getMatchesQualifierByName(teamName, matchesQualifier);
        if (matchesQualifierByName.isEmpty())
            throw new TeamNotFoundException(TEAM_NOT_FOUND);
        return matchesQualifierByName;
    }

}
