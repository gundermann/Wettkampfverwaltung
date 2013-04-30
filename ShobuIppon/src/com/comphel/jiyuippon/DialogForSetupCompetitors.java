package com.comphel.jiyuippon;

import com.comphel.jiyuippon.business.KumiteCompetitor;
import com.example.jiyuippon.R;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

class DialogForSetupCompetitors{
	
	private Dialog dialog;

	public DialogForSetupCompetitors(final JiyuIppon jiyuIppon) {

		jiyuIppon.runOnUiThread(new Runnable() {
            @Override
            public void run(){
            	
                dialog = jiyuIppon.getDialogBuilder().create();
                dialog.show();
                
                Button btAddCompetitor = (Button) dialog.findViewById(R.id.btAddCompetitors);
                btAddCompetitor.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						KumiteCompetitor aka = createCompetitor(((EditText) dialog.findViewById(R.id.akaFirstname)).getText().toString(), ((EditText) dialog.findViewById(R.id.akaLastname)).getText().toString());
						KumiteCompetitor shiro = createCompetitor(((EditText) dialog.findViewById(R.id.shiroFirstname)).getText().toString(), ((EditText) dialog.findViewById(R.id.shiroLastname)).getText().toString());
						
						
						jiyuIppon.createNewMatch(aka, shiro);
						
						jiyuIppon.updateStrings();
						
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
