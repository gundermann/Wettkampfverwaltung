package com.comphel.jiyuippon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.comphel.jiyuippon.R;

public class DialogForFinishing {

	private Dialog dialog;
	
	public DialogForFinishing(final ShobuIppon jiyuIppon) {
		
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
                
                TextView winnerTitle = (TextView) dialog.findViewById(R.id.winner);
                if(jiyuIppon.match.getAka() == jiyuIppon.match.getWinner())
                	winnerTitle.setText("Aka");
                else 
                	winnerTitle.setText("Shiro");
                
                Button btNextMatch = (Button) dialog.findViewById(R.id.buttonNextMatch);
                btNextMatch.setOnClickListener(new OnClickListener() {
                	
                	@Override
                	public void onClick(View arg0) {
                		jiyuIppon.initNewMatch();
                		jiyuIppon.reset();
                		dialog.dismiss();
                	}
                });
            }
        });
		
	}


}
