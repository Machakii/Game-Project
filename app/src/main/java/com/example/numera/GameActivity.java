package com.example.numera;

import static java.lang.System.exit;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {


    private Integer firstNumber = 0, secondNumber = 0;

    private String diff = "", question = "";
    private Integer score = 0;
    private int delay = 1000;
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

    private void setNumber(){
        int dummy1 = (int) (Math.random() * 10);
        int dummy2 = (int) (Math.random() * 10);
        int dummy3 = (int) (Math.random() * 10);
        firstNumber = (int) (Math.random() * 10);
        secondNumber = (int) (Math.random() * 10);

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
                answer = firstNumber * secondNumber;
                txtQuestion.setText(String.valueOf(firstNumber) + Multiply + String.valueOf(secondNumber));
                break;
            case Add:
                answer = firstNumber + secondNumber;
                txtQuestion.setText(String.valueOf(firstNumber) + Add + String.valueOf(secondNumber));
                break;
            case Divide:
                if (firstNumber <= secondNumber){
                    answer = secondNumber / firstNumber;
                    txtQuestion.setText(String.valueOf(secondNumber) + Divide + String.valueOf(firstNumber));
                }
                else {
                    answer = firstNumber / secondNumber;
                    txtQuestion.setText(String.valueOf(firstNumber) + Divide + String.valueOf(secondNumber));
                }
        }



        List<Button> buttons = Arrays.asList(btnOne, btnTwo, btnThree, btnFour);
        List<Integer> numbers = Arrays.asList(answer, dummy1, dummy2, dummy3);

        Collections.shuffle(numbers);

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
        //boolean result = textQuestion.getText().equals(btn.getText());
        boolean result = String.valueOf(answer).equalsIgnoreCase(btn.getText().toString());
        if(result){
            // Correct Answer
            score += 10;
            txtScore.setText(String.valueOf(score));
            setNumber(); // Generate NEW Question
        } else {
            // Game Over
            exit(0);
        }
    }


}