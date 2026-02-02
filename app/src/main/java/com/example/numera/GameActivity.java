package com.example.numera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameActivity extends AppCompatActivity {


    private Integer firstNumber = 0, secondNumber = 0;

    private String diff = "Easy", question = "";
    private Integer score = 0, dip = 9;
    int progress;
    private TextView txtQuestion, txtScore, txtDiff = null;
    private Button btnOne, btnTwo, btnThree, btnFour = null;
    private ProgressBar progBar = null;
    private final Handler handler = new Handler();
    private static final String Minus = "-";
    private static final String Multiply = "*";
    private static final String Add = "+";
    private static final String Divide = "/";
    private static int answer = 0;
    private static int ms = 200;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_game);


        txtScore = findViewById(R.id.score);
        txtDiff = findViewById(R.id.diff);
        txtQuestion = findViewById(R.id.question);

        btnOne = findViewById(R.id.btn_one);
        btnTwo = findViewById(R.id.btn_two);
        btnThree = findViewById(R.id.btn_three);
        btnFour = findViewById(R.id.btn_four);

        progBar = findViewById(R.id.progressBar);
        progBar.setProgress(0);

        setNumber();


    }

    private void setTimer(int delay){

        progBar.setProgress(0);
        handler.removeCallbacksAndMessages(null);
        final int[] counter  = {1};
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        int progress = counter[0];

                        progBar.setProgress(progress);
                        if(progress == 100){
                            ms = 200;
                            exit();
                        }
                        counter[0]++;
                        handler.postDelayed(this, delay);
                    }
                }, delay
        );
    }

    private void setNumber(){
        setTimer(ms);
        firstNumber = (int) (Math.random() * dip);
        secondNumber = (int) (Math.random() * dip);
        txtDiff.setText(diff);

        switch (diff){
            case "Medium":
                dip = 13;
            case "Hard":
                dip = 20;
            case "Extreme":
                dip = 30;
        }

        String sign = getRandomSign();

        switch (sign){
            case Minus:
                if(firstNumber <= secondNumber){
                    answer = secondNumber - firstNumber;
                    txtQuestion.setText(String.valueOf(secondNumber) + Minus + String.valueOf(firstNumber));
                } else {
                    answer = firstNumber - secondNumber;
                    txtQuestion.setText(String.valueOf(firstNumber) + Minus + String.valueOf(secondNumber));
                }
                break;
            case Multiply:
                // prevent 0
                if (firstNumber == 0) firstNumber = 1;
                if (secondNumber == 0) secondNumber = 1;
                answer = firstNumber * secondNumber;
                txtQuestion.setText(String.valueOf(firstNumber) + Multiply + String.valueOf(secondNumber));
                break;
            case Add:
                answer = firstNumber + secondNumber;
                txtQuestion.setText(String.valueOf(firstNumber) + Add + String.valueOf(secondNumber));
                break;
            case Divide:
                // prevent 0
                if (firstNumber == 0) firstNumber = 1;
                if (secondNumber == 0) secondNumber = 1;

                // para naman divisible talaga
                int multiplier = (int) (Math.random() * dip) + 1;

                if (firstNumber <= secondNumber){
                    secondNumber = firstNumber * multiplier;

                    answer = multiplier;
                    txtQuestion.setText(String.valueOf(secondNumber) + Divide + String.valueOf(firstNumber));
                }
                else {
                    firstNumber = secondNumber * multiplier;

                    answer = multiplier;
                    txtQuestion.setText(String.valueOf(firstNumber) + Divide + String.valueOf(secondNumber));
                }
                break;
        }

        Set<Integer> uniqueNumbers = new HashSet<>();
        uniqueNumbers.add(answer);  // include correct answer first

        while (uniqueNumbers.size() < 4) {
            int rand = (int) (Math.random() * dip) + 1;
            uniqueNumbers.add(rand); // Set prevents duplicates
        }

        List<Integer> numbers = new ArrayList<>(uniqueNumbers);
        Collections.shuffle(numbers);
        List<Button> buttons = Arrays.asList(btnOne, btnTwo, btnThree, btnFour);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(String.valueOf(numbers.get(i)));
        }

    }

    private String getRandomSign(){
        List<String> sign = Arrays.asList(Minus, Multiply, Add, Divide);
        Random random = new Random();
        int randomIndex = random.nextInt(4);    //Arrays Value 0,1,2,3 so 4 is the LIMIT
        return sign.get(randomIndex);
    }

    public void submitAnswer(View view){
        Button btn = (Button) view;
        boolean result = String.valueOf(answer).equalsIgnoreCase(btn.getText().toString());
        if(result){
            SFXManager.click();
            score += 10;
            txtScore.setText(String.valueOf(score));
            if(score == 50){
                SFXManager.changeDifficulty();
                diff = "Medium";
                ms = 80;
            }else if(score == 100){
                SFXManager.changeDifficulty();
                diff = "Hard";
                ms = 50;
            }else if(score >= 200){
                SFXManager.changeDifficulty();
                diff = "Extreme";
                ms = 30;
            }
            setNumber(); // Generate NEW Question
        } else {
            // Game Over
            SFXManager.wrong();
            ms = 200;
            exit();
        }
    }

    private void exit() {
        handler.removeCallbacksAndMessages(null);
        MusicManager.stop();
        if(score > 100) MusicManager.start(GameActivity.this, R.raw.gm_over2);
        else MusicManager.start(GameActivity.this, R.raw.gm_over1);
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("score", String.valueOf(score));
        this.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}