package com.comphel.jiyuippon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import business.KumiteCompetitor;


class DialogForSetupCompetitors{
	
	private Dialog dialog;
	private ShobuIppon jiyuIppon;

	public DialogForSetupCompetitors(final ShobuIppon jiyuIppon) {

		this.jiyuIppon = jiyuIppon;
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
                
                Button btAddCompetitor = (Button) dialog.findViewById(R.id.btAddCompetitors);
                btAddCompetitor.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						ToggleButton finaleToggle = (ToggleButton) dialog.findViewById(R.id.finaleButton);
						KumiteCompetitor aka = createCompetitor(((EditText) dialog.findViewById(R.id.akaFirstname)).getText().toString(), ((EditText) dialog.findViewById(R.id.akaLastname)).getText().toString());
						KumiteCompetitor shiro = createCompetitor(((EditText) dialog.findViewById(R.id.shiroFirstname)).getText().toString(), ((EditText) dialog.findViewById(R.id.shiroLastname)).getText().toString());
						
						jiyuIppon.createNewMatch(aka, shiro, finaleToggle.isChecked());
						
						jiyuIppon.updateStrings();
						
						jiyuIppon.match.start(); 
						
						dialog.dismiss();
					}
				});
            }


        });
	}
	

	private KumiteCompetitor createCompetitor(String firstname,
			String lastname) {
		return new KumiteCompetitor(firstname, lastname, 0, null);
	}
}
