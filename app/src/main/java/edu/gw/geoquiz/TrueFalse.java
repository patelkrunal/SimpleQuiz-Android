package edu.gw.geoquiz;

/**
 * Created by Krunal Patel on 9/15/2014.
 */
public class TrueFalse {
    private boolean mTrueQuestion;
    private int mQuestion;
    public TrueFalse(int Question, boolean trueQuestion)
    {
        mTrueQuestion=trueQuestion;
        mQuestion=Question;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public int getQuestion() {
        return mQuestion;
    }
}
