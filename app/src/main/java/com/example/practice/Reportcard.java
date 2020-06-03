package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Reportcard extends AppCompatActivity {

    int tc = rapidfire.c - 1, a = rapidfire.answered, r = rapidfire.right, w = rapidfire.wrong, t = rapidfire.timedout, s = rapidfire.skipped;
    int per = (r*100)/tc;
    TextView t1, t2, t3, t4, t5, t6, t7;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportcard);
        button = findViewById(R.id.backfromreport);
        t1 = findViewById(R.id.tcnumber);
        t2 = findViewById(R.id.anumber);
        t3 = findViewById(R.id.rnumber);
        t4 = findViewById(R.id.wnumber);
        t5 = findViewById(R.id.tnumber);
        t6 = findViewById(R.id.snumber);
        t7 = findViewById(R.id.pnumber);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(Reportcard.this, rapidfire.class);
                startActivity(in1);
            }
        });

        t1.setText(tc + "");
        t2.setText(a + "");
        t3.setText(r + "");
        t4.setText(w + "");
        t5.setText(t + "");
        t6.setText(s + "");
        t7.setText(per + "%");

        rapidfire.reset();

    }
}
