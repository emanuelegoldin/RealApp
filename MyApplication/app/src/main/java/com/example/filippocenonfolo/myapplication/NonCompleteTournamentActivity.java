package com.example.filippocenonfolo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NonCompleteTournamentActivity extends AppCompatActivity {

    private TextView textViewFirst;
    private TextView textViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_complete_tournament);

        Bundle data = getIntent().getExtras();
        final String titolo = data.getString("titolo");
        final String testo = data.getString("testo");


        textViewFirst = (TextView) findViewById(R.id.textViewFirst);
        textViewSecond = (TextView) findViewById(R.id.textViewSecond);

        textViewFirst.setText(titolo);
        textViewSecond.setText(testo);

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(i);

    }
}
