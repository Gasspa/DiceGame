package com.example.kostki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RzutyCount rzutyCountViewModel;
    private TextView textView;
    private Button buttonIncrement;
    private TextView P1, P2, P3, P4, P5;
    private TextView wynik, wynik2, rzutyCountTextView;
    private Button buttonReset;
    private TextView[] P = new TextView[5];
    private int[] kosc = new int[5];
    private int WynikCount = 0;
    private int wynik2Count = 0;
    private int Rzuty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        buttonIncrement = findViewById(R.id.buttonIncrement);
        P1 = findViewById(R.id.P1);
        P2 = findViewById(R.id.P2);
        P3 = findViewById(R.id.P3);
        P4 = findViewById(R.id.P4);
        P5 = findViewById(R.id.P5);
        wynik = findViewById(R.id.wynik);
        wynik2 = findViewById(R.id.wynik2);
        rzutyCountTextView = findViewById(R.id.rzutyCount);
        buttonReset = findViewById(R.id.buttonReset);

        P[0] = P1;
        P[1] = P2;
        P[2] = P3;
        P[3] = P4;
        P[4] = P5;

        rzutyCountViewModel = new ViewModelProvider(this).get(RzutyCount.class);
        updateThrowCount();

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rolldice();
                rzutyCountViewModel.incrementCount();
                updateThrowCount();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }
    public void rolldice() {
        Random ran = new Random();
        WynikCount = 0;

        for (int i = 0; i < P.length; i++) {
            kosc[i] = ran.nextInt(6) + 1;
            WynikCount += kosc[i];
            P[i].setText(String.valueOf(kosc[i]));
        }

        Rzuty++;
        wynik2Count += WynikCount;
        wynik.setText("Wynik tego losowania: " + WynikCount);
        wynik2.setText("Wynik gry: " + wynik2Count);
    }

    public void resetGame() {

        for (int i = 0; i < P.length; i++) {
            P[i].setText(" ? ");
        }
        WynikCount = 0;
        wynik2Count = 0;
        Rzuty = 0;

        wynik2.setText("Wynik tego losowania: " + WynikCount);
        wynik.setText("Wynik gry: " + wynik2Count);

        rzutyCountViewModel.resetCount();
        updateThrowCount();
    }

    private void updateThrowCount() {
        rzutyCountTextView.setText("Liczba rzutów: " + rzutyCountViewModel.getCount());
    }
}