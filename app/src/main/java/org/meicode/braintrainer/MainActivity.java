package org.meicode.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    GridLayout gridLayout;
    RelativeLayout relativeLayout;
    ArrayList<Integer> arrayList;
    int indexCorrectAnswer;
    CountDownTimer countDownTimer;
    TextView tv_check;
    TextView tv_question;
    TextView tv_score;
    int sumQuestion = 0;
    int numCorrectAnswer = 0;

    public void initGame() {
        numCorrectAnswer = 0;
        sumQuestion = 0;
        tv_score.setText(String.valueOf(numCorrectAnswer) + "/" + String.valueOf(sumQuestion));
        setNewQuestion();
    }

    public void unAbleClick(boolean a) {
        Button bt_answer1 = (Button) findViewById(R.id.bnt_answer1);
        Button bt_answer2 = (Button) findViewById(R.id.bnt_answer2);
        Button bt_answer3 = (Button) findViewById(R.id.bnt_answer3);
        Button bt_answer4 = (Button) findViewById(R.id.bnt_answer4);
        Button bt_answer5 = (Button) findViewById(R.id.bnt_answer5);
        Button bt_answer6 = (Button) findViewById(R.id.bnt_answer6);
        boolean check = true;
        if(a == true) {
            bt_answer1.setEnabled(false);
            bt_answer2.setEnabled(false);
            bt_answer3.setEnabled(false);
            bt_answer4.setEnabled(false);
            bt_answer5.setEnabled(false);
            bt_answer6.setEnabled(false);
        }
        else{
            bt_answer1.setEnabled(true);
            bt_answer2.setEnabled(true);
            bt_answer3.setEnabled(true);
            bt_answer4.setEnabled(true);
            bt_answer5.setEnabled(true);
            bt_answer6.setEnabled(true);
        }
    }

    public void clickFun(View view) {
        tv_check.setText("");
        Button bnt_playagain = (Button) findViewById(R.id.bnt_playagain);
        unAbleClick(false);
        bnt_playagain.setVisibility(View.INVISIBLE);
        initGame();
        TextView tv_timeCounter = (TextView) findViewById(R.id.tv_time);
        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        //set attributes for counter time ...
        countDownTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timeCounter.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                bnt_playagain.setVisibility(View.VISIBLE);
                countDownTimer.cancel();
                tv_check.setText("Your score is " + String.valueOf(numCorrectAnswer) + "/" +String.valueOf(sumQuestion-1) + ".");
                unAbleClick(true);
            }
        }.start();
    }

    public void setNewQuestion() {
        sumQuestion++;
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        int result = a + b;
        arrayList = new ArrayList<Integer>();
        Button bt_answer1 = (Button) findViewById(R.id.bnt_answer1);
        Button bt_answer2 = (Button) findViewById(R.id.bnt_answer2);
        Button bt_answer3 = (Button) findViewById(R.id.bnt_answer3);
        Button bt_answer4 = (Button) findViewById(R.id.bnt_answer4);
        Button bt_answer5 = (Button) findViewById(R.id.bnt_answer5);
        Button bt_answer6 = (Button) findViewById(R.id.bnt_answer6);
        int incorrectAnswer;
        indexCorrectAnswer = random.nextInt(4);
        for (int i = 0; i < 6; i++) {
            if (i == indexCorrectAnswer) {
                arrayList.add(a + b);
                tv_question.setText(String.valueOf(a) + " + " + String.valueOf(b) + " = ");
            } else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == (a + b)) {
                    incorrectAnswer = random.nextInt(41);
                }
                arrayList.add(incorrectAnswer);
            }
        }
        bt_answer1.setText(String.valueOf(arrayList.get(0)));
        bt_answer2.setText(String.valueOf(arrayList.get(1)));
        bt_answer3.setText(String.valueOf(arrayList.get(2)));
        bt_answer4.setText(String.valueOf(arrayList.get(3)));
        bt_answer5.setText(String.valueOf(arrayList.get(4)));
        bt_answer6.setText(String.valueOf(arrayList.get(5)));
    }


    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(String.valueOf(indexCorrectAnswer))) {
            tv_check.setText("Correctly !!");
            numCorrectAnswer++;
        } else {
            tv_check.setText("Incorrect !!");
        }
        tv_score.setText(String.valueOf(numCorrectAnswer) + "/" + String.valueOf(sumQuestion));
        setNewQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.bnt_go);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        tv_question = (TextView) findViewById(R.id.tv_question);
        relativeLayout.setVisibility(View.INVISIBLE);
        tv_check = (TextView) findViewById(R.id.tv_check);
        tv_score = (TextView) findViewById(R.id.tv_score);

    }
}