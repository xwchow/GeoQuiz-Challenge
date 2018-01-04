package com.bignerdranch.android.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;

public class CheatActivity extends AppCompatActivity {
  private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
  private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
  private static final String KEY_ANSWER_SHOWN = "answer_shown";

  private boolean answerIsTrue;
  private boolean isAnswerShown;

  private TextView answerTextView;
  private Button showAnswerButton;
  private TextView apiTextView;

  public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
    Intent intent = new Intent(packageContext, CheatActivity.class);
    intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
    return intent;
  }

  public static boolean wasAnswerShown(Intent result) {
    return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
  }

  private void showAnswer(boolean answerIsTrue) {
    if (answerIsTrue) {
      answerTextView.setText(R.string.true_button);
    } else {
      answerTextView.setText(R.string.false_button);
    }
    isAnswerShown = true;
    setAnswerShownResult(isAnswerShown);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
      isAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN);
    }

    setContentView(R.layout.activity_cheat);

    answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
    answerTextView = (TextView) findViewById(R.id.answerTextView);
    showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
    showAnswerButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        showAnswer(answerIsTrue);

        if (Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
          int cx = showAnswerButton.getWidth() / 2;
          int cy = showAnswerButton.getHeight() / 2;
          float radius = showAnswerButton.getWidth();
          Animator anim = ViewAnimationUtils
              .createCircularReveal(showAnswerButton, cx, cy, radius, 0);
          anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              super.onAnimationEnd(animation);
              showAnswerButton.setVisibility(View.INVISIBLE);
            }
          });
          anim.start();
        } else {
          showAnswerButton.setVisibility(View.INVISIBLE);
        }
      }
    });

    apiTextView = (TextView) findViewById(R.id.apiTextView);
    apiTextView.setText("API Level " + Build.VERSION.SDK_INT);

    if (isAnswerShown) {
      showAnswerButton.setVisibility(View.INVISIBLE);
      showAnswer(answerIsTrue);
    }
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
    savedInstanceState.putBoolean(KEY_ANSWER_SHOWN, isAnswerShown);
  }


  private void setAnswerShownResult(boolean isAnswerShown) {
    Intent data = new Intent();
    data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
    setResult(RESULT_OK, data);
  }
}
