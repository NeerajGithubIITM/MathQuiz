package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class rapidfire extends AppCompatActivity {
    int tappedAnswer = -1;
    int val1 = 0, val2 = 0, operator = 0, f1 = 0, f2 = 0, f3 = 0, option = 0, answer = 0;
    Random randomGenerator = new Random();
    TextView question, questionNumber, o1, o2, o3, o4, timer;
    Button b1, b2, b3;
    Handler handler = new Handler();
    CountDownTimer cdtimer;
    static boolean viewresult = false;

    public static int c = 0, answered = 0, right = 0, wrong = 0, timedout = 0, skipped = 0;
    public void generateQuestion(){

        o1.setBackgroundColor(Color.WHITE);
        o2.setBackgroundColor(Color.WHITE);
        o3.setBackgroundColor(Color.WHITE);
        o4.setBackgroundColor(Color.WHITE);

        if(cdtimer != null){
            cdtimer.cancel();
            cdtimer = null;
        }

        c += 1;
        questionNumber.setText("Question " + c + " :");

        val1 = randomGenerator.nextInt(100) + 1;
        val2 = randomGenerator.nextInt(100) + 1;
        operator = randomGenerator.nextInt(4) + 1;
        if(operator == 1){
            answer = val1 + val2;
            question.setText(val1 + " + " + val2 + " = ?");
        }
        if(operator == 2){
            answer = val1 - val2;
            question.setText(val1 + " - " + val2 + " = ?");
        }
        if(operator == 3){
            answer = val1 * val2;
            question.setText(val1 + " * " + val2 + " = ?");
        }
        if(operator == 4){
            answer = val1 / val2;
            question.setText(val1 + " / " + val2 + " = ?  (nearest integer");
        }

        generateFakeAnswer();
        while(f1 == f2 || f2 == f3 || f3 == f1 || answer == f1 || answer == f2 || answer == f3){
            generateFakeAnswer();
        }

        option = randomGenerator.nextInt(4);

        if(option == 0){
            o1.setText(answer + "");
            o2.setText(f1 + "");
            o3.setText(f2 + "");
            o4.setText(f3 + "");
        }
        if(option == 1){
            o2.setText(answer + "");
            o3.setText(f1 + "");
            o4.setText(f2 + "");
            o1.setText(f3 +"");
        }
        if(option == 2){
            o3.setText(answer + "");
            o4.setText(f1 + "");
            o1.setText(f2 + "");
            o2.setText(f3 + "");
        }
        if(option == 3){
            o4.setText(answer + "");
            o1.setText(f1 + "");
            o2.setText(f2 + "");
            o3.setText(f3 + "");
        }

        cdtimer = new CountDownTimer(8000, 1000){
            public void onTick(long millisUntilFinished){
                timer.setText(new SimpleDateFormat("s").format(new Date(millisUntilFinished + 1000)));
            }

            public void onFinish(){
                timedout += 1;
                question.setText(R.string.time_up);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        generateQuestion();
                    }
                }, 1000);
            }
        }.start();

    }

    public void generateFakeAnswer(){
        if(answer < 0){
            f1 = -1 * (randomGenerator.nextInt(100) + 1);
            f2 = -1 * (randomGenerator.nextInt(100) + 1);
            f3 = -1 * (randomGenerator.nextInt(100) + 1);
        }
        if(answer > 0 && answer < 10){
            f1 = (randomGenerator.nextInt(9) + 1);
            f2 = (randomGenerator.nextInt(9) + 1);
            f3 = (randomGenerator.nextInt(9) + 1);
        }
        if(answer > 10 && answer < 100){
            f1 = (randomGenerator.nextInt(90) + 11);
            f2 = (randomGenerator.nextInt(90) + 11);
            f3 = (randomGenerator.nextInt(90) + 11);
        }
        if(answer > 100 && answer < 1000){
            f1 = (randomGenerator.nextInt(900) + 101);
            f2 = (randomGenerator.nextInt(900) + 101);
            f3 = (randomGenerator.nextInt(900) + 101);
        }
        if(answer > 1000){
            f1 = (randomGenerator.nextInt(9000) + 1001);
            f2 = (randomGenerator.nextInt(9000) + 1001);
            f3 = (randomGenerator.nextInt(9000) + 1001);
        }
    }

    public void checkRight(View view){
        TextView text = (TextView) view;
        tappedAnswer = Integer.parseInt(text.getTag().toString());

        if(c == 0){
            Toast.makeText(this, "Begin the Session!! ", Toast.LENGTH_SHORT).show();
        }
        else {
            answered += 1;
            if(cdtimer != null){
                cdtimer.cancel();
                cdtimer = null;
            }

            if (tappedAnswer == option) {

                right += 1;
                Toast.makeText(this, "Right Answer!!", Toast.LENGTH_SHORT).show();


                if (option == 0) {
                    o1.setBackgroundColor(Color.GREEN);
                }
                if (option == 1) {
                    o2.setBackgroundColor(Color.GREEN);
                }
                if (option == 2) {
                    o3.setBackgroundColor(Color.GREEN);
                }
                if (option == 3) {
                    o4.setBackgroundColor(Color.GREEN);
                }
            }
            else {

                wrong += 1;
                Toast.makeText(this, "Incorrect Answer!!", Toast.LENGTH_SHORT).show();


                if (tappedAnswer == 0) {
                    o1.setBackgroundColor(Color.RED);
                }
                if (tappedAnswer == 1) {
                    o2.setBackgroundColor(Color.RED);
                }
                if (tappedAnswer == 2) {
                    o3.setBackgroundColor(Color.RED);
                }
                if (tappedAnswer == 3) {
                    o4.setBackgroundColor(Color.RED);
                }

            }

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateQuestion();
                }
            }, 1000);

        }

    }

    public static void reset() {
        c = 0;
        answered = 0;
        right = 0;
        wrong = 0;
        timedout = 0;
        skipped = 0;
        viewresult = false;
    }

    public void beforeStop() {
        if(c == 0) {
            cdtimer = new CountDownTimer(3000, 1000){
                public void onTick(long millisUntilFinished){
                    timer.setText(new SimpleDateFormat("s").format(new Date(millisUntilFinished + 1000)));
                }

                public void onFinish(){
                    b1.setText(R.string.skip);
                    generateQuestion();
                }
            }.start();
        }
        else{
            skipped += 1;
            generateQuestion();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapidfire);

        question = findViewById(R.id.question);
        timer = findViewById(R.id.timer);
        questionNumber = findViewById(R.id.question_no);
        o1 = findViewById(R.id.answer_1);
        o2 = findViewById(R.id.answer_2);
        o3 = findViewById(R.id.answer_4);
        o4 = findViewById(R.id.answer_3);
        b1 = findViewById(R.id.begin);
        b2 = findViewById(R.id.instruction);
        b3 = findViewById(R.id.endgame);

        b3.setText(R.string.stop_quiz);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewresult){
                    beforeStop();
                }
                else{
                    questionNumber.setText("Question :");
                    b3.setText(R.string.stop_quiz);
                    reset();
                    beforeStop();
                }

            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c == 0){
                Intent in1 = new Intent(rapidfire.this, Instructions.class);
                startActivity(in1);
                }
                else{
                    Toast.makeText(getApplicationContext(), "You can't view instructions in the middle of the session", Toast.LENGTH_SHORT).show();
                }
            }
        });



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c == 0){
                    Toast.makeText(getApplicationContext(), getString(R.string.end_game_warning), Toast.LENGTH_SHORT).show();
                }
                if(!viewresult && c !=0 ) {
                    b3.setText(R.string.view_result);
                    b1.setText(R.string.begin_session);
                    viewresult = true;
                    Toast.makeText(getApplicationContext(), " You may view the result or begin another session directly ", Toast.LENGTH_LONG).show();


                    question.setText("");
                    o1.setText("");
                    o2.setText("");
                    o3.setText("");
                    o4.setText("");

                    o1.setBackgroundColor(Color.WHITE);
                    o2.setBackgroundColor(Color.WHITE);
                    o3.setBackgroundColor(Color.WHITE);
                    o4.setBackgroundColor(Color.WHITE);

                    if (cdtimer != null) {
                        cdtimer.cancel();
                        cdtimer = null;
                    }
                    timer.setText("0");

                }
                else if(viewresult && c != 0) {
                    viewresult = false;
                    Toast.makeText(getApplicationContext(), "Loading Report.....", Toast.LENGTH_SHORT).show();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent in1 = new Intent(rapidfire.this, Reportcard.class);
                            startActivity(in1);
                        }
                    }, 3000);
                }
            }

        });
    }
}