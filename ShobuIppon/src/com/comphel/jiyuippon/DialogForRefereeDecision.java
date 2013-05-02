package com.comphel.jiyuippon;

import com.comphel.jiyuippon.definition.CompetitorNameInCompetition;
import com.example.jiyuippon.R;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogForRefereeDecision {
	
	private Dialog dialog;

	public DialogForRefereeDecision(final JiyuIppon jiyuIppon) {
		jiyuIppon.runOnUiThread(new Runnable() {
            @Override
            public void run(){
            	
                dialog = jiyuIppon.getDialogBuilder().create();
                dialog.setCancelable(false);
                dialog.show();
                
                Button btAkaWins = (Button) dialog.findViewById(R.id.btAkaWins);
                btAkaWins.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						jiyuIppon.match.setWinner(CompetitorNameInCompetition.Aka);
						dialog.dismiss();
					}
				});
                
                Button btShiroWins = (Button) dialog.findViewById(R.id.btshiroWins);
                btShiroWins.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						jiyuIppon.match.setWinner(CompetitorNameInCompetition.Shiro);
						dialog.dismiss();
					}
				});
                
                Button btHikewake = (Button) dialog.findViewById(R.id.btHikewake);
                btHikewake.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						jiyuIppon.match.nextRound();
						jiyuIppon.reset();
						dialog.dismiss();
					}
				});
                
            }
        });
	}

}
