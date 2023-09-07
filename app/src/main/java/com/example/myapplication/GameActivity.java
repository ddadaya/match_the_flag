package com.example.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private static final String url = "https://64f6d4e99d7754084952a4aa.mockapi.io/api/v1/task1";
    private ImageButton btn1,btn2,btn3;
    private TextView question;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    int i=0;
    String correct;
    String results[]=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        btn1 = (ImageButton) findViewById(R.id.button1);
        btn2 = (ImageButton) findViewById(R.id.button2);
        btn3 = (ImageButton) findViewById(R.id.button3);
        question = (TextView) findViewById(R.id.question);

        sendAndRequestResponse();

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check_answer(btn1.getTag().toString());

                    sendAndRequestResponse();
                }
            });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_answer(btn2.getTag().toString());
                sendAndRequestResponse();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_answer(btn3.getTag().toString());
                sendAndRequestResponse();
            }
        });

    }

    private void sendAndRequestResponse() {

        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                task data[] = gson.fromJson(response, task[].class);

                set_task(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    public void set_task(task data[]){
        if (i < 10) {
            List<String> answers = new ArrayList<>(Arrays.asList(data[i].getAns1(), data[i].getAns2(), data[i].getCorrect()));
            correct=answers.get(2);
            Collections.shuffle(answers);
            question.setText(i + 1 + ") " + data[i].getQuestionText());
            btn1.setBackgroundResource(getResources().getIdentifier(answers.get(0), "drawable", getPackageName()));
            btn1.setTag(answers.get(0));
            btn2.setBackgroundResource(getResources().getIdentifier(answers.get(1), "drawable", getPackageName()));
            btn2.setTag(answers.get(1));
            btn3.setBackgroundResource(getResources().getIdentifier(answers.get(2), "drawable", getPackageName()));
            btn3.setTag(answers.get(2));
        } else {
            Intent intent = new Intent(GameActivity.this, ResultPage.class);
            intent.putExtra("results", results);
            startActivity(intent);
        }
    }

    public void check_answer(String tag){
        if(i<10) {
            if (tag == correct) {
                results[i] = "Correct";
            } else {
                results[i] = "Incorrect";
            }
            i++;
        }
    }
}

