package com.comphel.KataPoints;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import business.*;
import com.comphel.common.definition.Competitor;
import com.comphel.common.definition.Graduierung;


public class KataPoints extends Activity implements SingleMatchReceiver {

    KataMatchSingle match;

    EditText[] v = new EditText[5];
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.kata_points_competitions);

        v[0] = (EditText) findViewById(R.id.v1);
        v[1] = (EditText) findViewById(R.id.v2);
        v[2] = (EditText) findViewById(R.id.v3);
        v[3] = (EditText) findViewById(R.id.v4);
        v[4] = (EditText) findViewById(R.id.v5);

        reactOnNewMatch(new KataMatchSingle(new KataPointsCompetitor("", "", 18, Graduierung.DAN1)));
    }

    public void calculate(View view){
        double[] values = new double[5];
        try{
            values[0] = Double.parseDouble(v[0].getText().toString());
            values[1] = Double.parseDouble(v[1].getText().toString());
            values[2] = Double.parseDouble(v[2].getText().toString());
            values[3] = Double.parseDouble(v[3].getText().toString());
            values[4] = Double.parseDouble(v[4].getText().toString());

            match.getCompetitor().setScores(values);
            match.calculate();

            disableLowestScore(match.getLowestScore());
            disableHighestScore(match.getHighestScore());

            ((Button) findViewById(R.id.button)).setEnabled(false);

            TextView sum = (TextView) findViewById(R.id.textView);
            sum.setText(String.valueOf(match.getSum()/10));
        }
        catch(NumberFormatException nfe){
            Toast.makeText(getApplicationContext(), getString(R.string.inputError), Toast.LENGTH_LONG).show();
        }


    }

    private void disableHighestScore(double highestScore){
        for(int i = 0; i<5; i++){
            if(!v[i].getText().toString().equals("X") && Double.parseDouble(v[i].getText().toString()) ==  highestScore && v[i].isEnabled()){
                v[i].setEnabled(false);
                v[i].setTextColor(Color.RED);
                v[i].setInputType(InputType.TYPE_CLASS_TEXT);
                v[i].setText("X");
                break;
            }
        }
    }

    private void disableLowestScore(double lowestScore) {
        for(int i = 0; i<5; i++){
            if(!v[i].getText().toString().equals("X") && Double.parseDouble(v[i].getText().toString()) ==  lowestScore && v[i].isEnabled()){
                v[i].setEnabled(false);
                v[i].setTextColor(Color.RED);
                v[i].setInputType(InputType.TYPE_CLASS_TEXT);
                v[i].setText("X");
                break;
            }
        }
    }

    public void next(View view){
        Intent newKataPoitsMatch = new Intent().setClass(this, KataPoints.class);
        startActivity(newKataPoitsMatch);
        this.finish();
    }

    @Override
    public void reactOnNewMatch(KataMatchSingle match) {
        this.match = match;

        TextView competitorLabel = (TextView) findViewById(R.id.competitorLabel);
        competitorLabel.setText(match.getCompetitor().toString());
    }
}
