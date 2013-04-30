package com.comphel.jiyuippon;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.jiyuippon.R;

public class DialogForFinishing {

	private Dialog dialog;
	
	public DialogForFinishing(final JiyuIppon jiyuIppon) {
		jiyuIppon.runOnUiThread(new Runnable() {
            @Override
            public void run(){
            	
                dialog = jiyuIppon.getDialogBuilder().create();
                dialog.show();
                
                Button btNextMatch = (Button) dialog.findViewById(R.id.buttonNextMatch);
                btNextMatch.setOnClickListener(new OnClickListener() {
                	
                	@Override
                	public void onClick(View arg0) {
                		jiyuIppon.initNewMatch();
                		
                		dialog.dismiss();
                	}
                });
            }
        });
		
	}


}
