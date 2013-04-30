package com.comphel.jiyuippon;

import android.app.Dialog;

public class DialogForRefereeDecision {
	
	private Dialog dialog;

	public DialogForRefereeDecision(final JiyuIppon jiyuIppon) {
		// TODO Auto-generated constructor stub
		jiyuIppon.runOnUiThread(new Runnable() {
            @Override
            public void run(){
            	
                dialog = jiyuIppon.getDialogBuilder().create();
                dialog.show();
            }
        });
	}

}
