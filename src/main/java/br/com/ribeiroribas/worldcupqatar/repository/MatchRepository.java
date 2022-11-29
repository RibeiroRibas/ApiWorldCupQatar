package br.com.ribeiroribas.worldcupqatar.repository;

import br.com.ribeiroribas.worldcupqatar.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Long> {

    List<Match> findByDate(LocalDate date);

    List<Match> findByTeam1(String teamName);

    List<Match> findByTeam2(String teamName);

    List<Match> findByStage(String stage);


}
