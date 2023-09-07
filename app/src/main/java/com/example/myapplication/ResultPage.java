package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultPage extends AppCompatActivity {
    private TextView textview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        textview = (TextView) findViewById(R.id.res);
        Intent intent = getIntent();

        String[] receivedResults = intent.getStringArrayExtra("results");
        int total=0;

        if (receivedResults != null) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < receivedResults.length; i++) {

                if(receivedResults[i].equals("Correct"))
                    total++;

                stringBuilder.append("Question ").append(i + 1).append(" - ").append(receivedResults[i]);

                if (i < receivedResults.length - 1) {
                    stringBuilder.append("\n");
                }
            }
            stringBuilder.append("\n"+"Total - "+total);
            textview.setText(stringBuilder.toString());
        } else {
            Toast.makeText(this,"sth wrong",Toast.LENGTH_LONG).show();
        }







    }
}
