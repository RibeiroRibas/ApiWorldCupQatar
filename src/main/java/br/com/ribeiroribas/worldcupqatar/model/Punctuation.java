package br.com.ribeiroribas.worldcupqatar.model;

public class Punctuation {

    private int points = 0;
    private int wins = 0;
    private int defeats = 0;
    private int draws = 0;
    private int gs = 0;
    private int gc = 0;
    private int gd = 0;
    private int percentage;


    public void setPoints(int points) {
        this.points += points;
    }

    public void setWins(int wins) {
        this.wins += wins;
    }

    public void setDefeats(int defeats) {
        this.defeats += defeats;
    }

    public void setDraws(int draws) {
        this.draws += draws;
    }

    public void setGs(int gs) {
        this.gs += gs;
    }

    public void setGc(int gc) {
        this.gc += gc;
    }

    public int getGd() {
        return gd;
    }

    public void setGd(int gd) {
        this.gd = gd;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getDraws() {
        return draws;
    }

    public int getGs() {
        return gs;
    }

    public int getGc() {
        return gc;
    }

    public void calculatePercentage(int totalMatches) {
        double playedPoints = 3 * totalMatches;
        double result = (points / playedPoints) * 100;
        this.percentage = (int) result;
    }

    public void calculateGoalDifference() {
        this.gd = gs - gc;
    }

    @Override
    public String toString() {
        return "Points{" +
                "points=" + points +
                ", wins=" + wins +
                ", defeats=" + defeats +
                ", draws=" + draws +
                ", gs=" + gs +
                ", gc=" + gc +
                ", gd=" + gd +
                ", percentage=" + percentage +
                '}';
    }
}
