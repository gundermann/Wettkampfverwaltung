package business;

import com.comphel.common.definition.Competitor;
import com.comphel.common.definition.Graduierung;

public class KumiteCompetitor extends Competitor {

	private Judgements judgement;
	
	public KumiteCompetitor(String vorname, String nachname, int alter,
			Graduierung grad) {
		super(vorname, nachname, alter, grad);
		judgement = new Judgements();
	}

	public Judgements getJudgement() {
		return judgement;
	}

	public void clearAllJudgements() {
		judgement.clear();
	}

	
}
