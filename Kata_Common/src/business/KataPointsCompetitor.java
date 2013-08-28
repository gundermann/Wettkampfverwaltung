package business;

import com.comphel.common.definition.Competitor;
import com.comphel.common.definition.Graduierung;

/**
 * Created by lede92 on 27.08.13.
 */
public class KataPointsCompetitor extends Competitor{

    double[] scores = new double[5];

    public KataPointsCompetitor(String vorname, String nachname, int alter, Graduierung grad) {
        super(vorname, nachname, alter, grad);
    }

    public double[] getScores() {
        return scores;
    }

    public void setScores(double[] scores) {
        this.scores = scores;
    }
}
