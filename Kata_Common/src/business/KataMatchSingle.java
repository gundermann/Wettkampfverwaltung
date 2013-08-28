package business;

/**
 * Created by lede92 on 28.08.13.
 */
public class KataMatchSingle {

    KataPointsCompetitor competitor;
    Calculator calculator;
    double lowestScore;
    double highestScore;
    double sum;

    public KataMatchSingle(KataPointsCompetitor competitor){
        this.competitor = competitor;
        calculator = new Calculator(competitor);
    }

    public void calculate(){
        calculator.setUp();
        calculator.calculate();

        highestScore = calculator.getHighestScore();
        lowestScore = calculator.getLowestScore();
        sum = calculator.getSolution();
    }

    public double getLowestScore() {
        return lowestScore;
    }

    public double getHighestScore() {
        return highestScore;
    }

    public double getSum() {
        return sum;
    }

    public KataPointsCompetitor getCompetitor() {
        return competitor;
    }
}
