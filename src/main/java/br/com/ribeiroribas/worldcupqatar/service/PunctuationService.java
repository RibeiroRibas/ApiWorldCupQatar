package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.model.Match;
import br.com.ribeiroribas.worldcupqatar.model.Punctuation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunctuationService {

    protected Punctuation getPunctuation(List<Match> matchByName, String teamName) {
        Punctuation punctuation = new Punctuation();
        matchByName.forEach(match -> {
            if (match.getScoreTeam1() != null && match.getScoreTeam2() != null) {
                punctuation.setPoints(match.getPoints(teamName));
                punctuation.setDraws(match.getDraw());
                punctuation.setDefeats(match.getDefeat(teamName));
                punctuation.setWins(match.getVictory(teamName));
                punctuation.setGc(match.getGc(teamName));
                punctuation.setGs(match.getGp(teamName));
            }
        });
        int matchTotal = matchByName.size();
        punctuation.calculateGoalDifference();
        punctuation.calculatePercentage(matchTotal);
        return punctuation;
    }
}
