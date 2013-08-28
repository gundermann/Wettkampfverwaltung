package business;

/**
 * Created by lede92 on 27.08.13.
 */
public class Calculator {

    KataPointsCompetitor competitor;
    CalculationListener listener;
    double[] value = new double[5];
    int lowestIndex;
    int highestIndex;
    double solution;

    public Calculator(KataPointsCompetitor comp){
        competitor = comp;
        this.listener = listener;
    }

    public void setUp(){
        double[] values = competitor.getScores();
        value[0]=values[0];
        value[1]=values[1];
        value[2]=values[2];
        value[3]=values[3];
        value[4]=values[4];
    }

    public void calculate(){
        double solutionDummy = value[0] + value[1] + value[2] + value[3] + value[4];

        solutionDummy -= getHighestScore();
        solutionDummy -= getLowestScore();

        solution = solutionDummy;
    }

    public double getSolution() {
        return solution;
    }

    public double getLowestScore() {
        double highest = value[0];
        highestIndex = 0;

        if(highest < value[1]){
            highest = value[1];
            highestIndex = 1;
        }
        if(highest < value[2]){
            highest = value[2];
            highestIndex = 2;
        }
        if(highest < value[3]){
            highest = value[3];
            highestIndex = 3;
        }
        if(highest < value[4]){
            highest = value[4];
            highestIndex = 4;
        }

        return highest;
    }

    public double getHighestScore() {
        double lowest = value[0];
        lowestIndex = 0;

        if(lowest > value[1]){
            lowest = value[1];
            lowestIndex = 1;
        }
        if(lowest > value[2]){
            lowest = value[2];
            lowestIndex = 2;
        }
        if(lowest > value[3]){
            lowest = value[3];
            lowestIndex = 3;
        }
        if(lowest > value[4]){
            lowest = value[4];
            lowestIndex = 4;
        }

        return lowest;
    }
}
