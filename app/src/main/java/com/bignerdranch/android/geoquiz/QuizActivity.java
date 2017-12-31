package com.bignerdranch.android.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
  private static final String TAG = "QuizActivity";
  private static final String KEY_INDEX = "index";

  private Button TrueButton;
  private Button FalseButton;
  private ImageButton NextButton;
  private ImageButton PrevButton;
  private Button CheatButton;
  private TextView QuestionTextView;

  private Question[] QuestionBank = new Question[] {
      new Question(R.string.question_oceans, true),
      new Question(R.string.question_mideast, false),
      new Question(R.string.question_africa, false),
      new Question(R.string.question_americas, true),
      new Question(R.string.question_asia, true),
  };

  private int currentIndex = 0;

  private void updateQuestion() {
    int question = QuestionBank[currentIndex].getTextResId();
    QuestionTextView.setText(question);
  }

  private void nextQuestion() {
    currentIndex = (currentIndex + 1) % QuestionBank.length;
    updateQuestion();
  }

  private void prevQuestion() {
    currentIndex = (currentIndex - 1);
    if (currentIndex < 0) currentIndex += QuestionBank.length;
    updateQuestion();
  }

  private void checkAnswer(boolean userPressedTrue) {
    boolean answerIsTrue = QuestionBank[currentIndex].isAnswerTrue();
    int messageResId =
        (answerIsTrue == userPressedTrue) ?
        R.string.correct_toast :
        R.string.incorrect_toast;
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
      currentIndex = savedInstanceState.getInt(KEY_INDEX);
    }

    Log.d(TAG, "onCreate(Bundle) called");
    setContentView(R.layout.activity_quiz);

    QuestionTextView = (TextView) findViewById(R.id.question_text_view);
    QuestionTextView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        nextQuestion();
      }
    });

    TrueButton = (Button) findViewById(R.id.true_button);
    TrueButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        checkAnswer(true);
      }
    });

    FalseButton = (Button) findViewById(R.id.false_button);
    FalseButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        checkAnswer(false);
      }
    });

    NextButton = (ImageButton) findViewById(R.id.next_button);
    NextButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        nextQuestion();
      }
    });

    PrevButton = (ImageButton) findViewById(R.id.prev_button);
    PrevButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        prevQuestion();
      }
    });

    CheatButton = (Button) findViewById(R.id.cheat_button);
    CheatButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(QuizActivity.this, CheatActivity.class);
        startActivity(i);
      }
    });

    updateQuestion();
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
    Log.i(TAG, "onSaveInstanceState");
    savedInstanceState.putInt(KEY_INDEX, currentIndex);
  }

  @Override
  public void onStart() {
    super.onStart();
    Log.d(TAG, "onStart() called");
  }

  @Override
  public void onPause() {
    super.onPause();
    Log.d(TAG, "onPause() called");
  }

  @Override
  public void onResume() {
    super.onResume();
    Log.d(TAG, "onResume() called");
  }

  @Override
  public void onRestart() {
    super.onRestart();
    Log.d(TAG, "onRestart called");
  }

  @Override
  public void onStop() {
    super.onStop();
    Log.d(TAG, "onStop() called");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy() called");
  }
}
