package com.bignerdranch.android.geoquiz;

/**
 * Created by xinwei on 18/12/17.
 */

public class Question {

  public int getTextResId() {
    return textResId;
  }

  public void setTextResId(int textResId) {
    this.textResId = textResId;
  }

  public boolean isAnswerTrue() {
    return answerTrue;
  }

  public void setAnswerTrue(boolean answerTrue) {
    this.answerTrue = answerTrue;
  }

  private int textResId;
  private boolean answerTrue;

  public Question(int textResId, boolean answerTrue) {
    this.textResId = textResId;
    this.answerTrue = answerTrue;
  }
}
