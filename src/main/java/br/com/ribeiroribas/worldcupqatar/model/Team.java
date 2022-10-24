package br.com.ribeiroribas.worldcupqatar.model;

import java.util.List;

public class Team {

    private String name;
    private List<QatarCupMatch> cupMatches;
    private Punctuation cupPunctuation;
    private List<MatchQualifier> qualifierMatches;
    private Punctuation qualifierPunctuation;

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Punctuation getCupPunctuation() {
        return cupPunctuation;
    }

    public void setCupPunctuation(Punctuation cupPunctuation) {
        this.cupPunctuation = cupPunctuation;
    }

    public Punctuation getQualifierPunctuation() {
        return qualifierPunctuation;
    }

    public void setQualifierPunctuation(Punctuation qualifierPunctuation) {
        this.qualifierPunctuation = qualifierPunctuation;
    }

    public List<QatarCupMatch> getCupMatches() {
        return cupMatches;
    }

    public void setCupMatches(List<QatarCupMatch> cupMatches) {
        this.cupMatches = cupMatches;
    }

    public List<MatchQualifier> getQualifierMatches() {
        return qualifierMatches;
    }

    public void setQualifierMatches(List<MatchQualifier> qualifierMatches) {
        this.qualifierMatches = qualifierMatches;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", matchesCup=" + cupMatches +
                ", pointsCup=" + cupPunctuation +
                ", matchesQualifier=" + qualifierMatches +
                ", pointsQualifier=" + qualifierPunctuation +
                '}';
    }
}
