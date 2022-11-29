package br.com.ribeiroribas.worldcupqatar.service;

import br.com.ribeiroribas.worldcupqatar.controller.dto.MatchesByDateDto;
import br.com.ribeiroribas.worldcupqatar.controller.dto.MatchesDto;
import br.com.ribeiroribas.worldcupqatar.model.Match;
import br.com.ribeiroribas.worldcupqatar.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorldCupMatchesService {

    public static final String GROUP_STAGE = "FASE DE GRUPOS";
    public static final LocalDate MATCH_FINAL_DATE = LocalDate.of(2022, 12, 18);
    public static final String A_CONFIRMAR = "A Confirmar";
    @Autowired
    private MatchRepository repository;

    public MatchesByDateDto getCupMatchesByDate(LocalDate date) {

        List<Match> cupMatchesByDate = repository.findByDate(date);

        MatchesByDateDto matchesDto = new MatchesByDateDto();

        List<MatchesDto> matchesByDate = new ArrayList<>();
        cupMatchesByDate.forEach(matchByDate -> {
            List<Match> matchesTeam1 = new ArrayList<>();
            List<Match> matchesTeam2 = new ArrayList<>();
            if (!matchByDate.getTeam1().equals(A_CONFIRMAR)
                    && !matchByDate.getTeam1().startsWith("1")
                    && !matchByDate.getTeam1().startsWith("2")) {
                matchesTeam1.addAll(repository.findByTeam1(matchByDate.getTeam1()));
                matchesTeam1.addAll(repository.findByTeam2(matchByDate.getTeam1()));
            }
            if (!matchByDate.getTeam2().equals(A_CONFIRMAR)
                    && !matchByDate.getTeam2().startsWith("1")
                    && !matchByDate.getTeam2().startsWith("2")) {
                matchesTeam2.addAll(repository.findByTeam1(matchByDate.getTeam2()));
                matchesTeam2.addAll(repository.findByTeam2(matchByDate.getTeam2()));
            }
            matchesByDate.add(new MatchesDto(matchByDate, matchesTeam1, matchesTeam2));
        });

        matchesDto.setMatches(matchesByDate);

        return matchesDto;
    }

    public List<Match> getCupMatchesGroupStage() {
        return repository.findByStage(GROUP_STAGE);
    }

    public Match getFinalMatch() {
        return repository.findByDate(MATCH_FINAL_DATE).stream().findFirst().get();
    }

    public void saveAll(List<Match> matches) {
        repository.saveAll(matches);
    }


    public List<Match> findAll() {
        return repository.findAll();
    }

    public void update(Match match) {
        Match referenceById = repository.getReferenceById(match.getId());
        referenceById.update(match);
        repository.save(referenceById);
    }

    public Optional<Match> getById(Long id) {
        return repository.findById(id);
    }
}
