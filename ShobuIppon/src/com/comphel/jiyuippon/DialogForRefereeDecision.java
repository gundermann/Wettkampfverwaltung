package com.comphel.jiyuippon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.comphel.common.definition.CompetitorNameInCompetition;

public class DialogForRefereeDecision {
	
	private Dialog dialog;

	public DialogForRefereeDecision(final ShobuIppon jiyuIppon) {
		jiyuIppon.runOnUiThread(new Runnable() {
            @Override
            public void run(){
            	
                dialog = jiyuIppon.getDialogBuilder().create();
                dialog.setCancelable(false);
                dialog.show();
                
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    
                	@Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                               dialog.cancel();
                               return true;
                            }
                         return false;
                    }

                });
                
                dialog.setOnCancelListener(new OnCancelListener() {
					
					@Override
					public void onCancel(DialogInterface arg0) {
						jiyuIppon.finish();
					}
				});
               
                getBtAka().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						jiyuIppon.match.setWinner(CompetitorNameInCompetition.Aka);
						dialog.dismiss();
					}
				});
                
                getBtShiro().setOnClickListener(new OnClickListener() {
					
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
	
	private Button getBtAka(){
		return  (Button) dialog.findViewById(R.id.btAkaWins);
	}

	private Button getBtShiro(){
		return  (Button) dialog.findViewById(R.id.btshiroWins);
	}
	
}
