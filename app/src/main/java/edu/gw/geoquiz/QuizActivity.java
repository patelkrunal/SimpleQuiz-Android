package edu.gw.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class QuizActivity extends Activity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private Button mNexButton;
    private Button mPreviousButton;
    private boolean mDidCheat;

    private TrueFalse [] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question1,true),
            new TrueFalse(R.string.question2,false),
            new TrueFalse(R.string.question3,true)
    };

    private int mCurrentIndex = 0;
    private final String TAG ="QuizeActivity";
    private final int CHEAT_REQUEST_CODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d("QuizActivity","inside onCreate");

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
        mNexButton = (Button) findViewById(R.id.next_button);
        mCheatButton=(Button) findViewById(R.id.cheat_button);

        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(QuizActivity.this,activity_cheat.class);
                boolean answer = mQuestionBank[mCurrentIndex].isTrueQuestion();
                newIntent.putExtra("answer",answer);
                startActivityForResult(newIntent, CHEAT_REQUEST_CODE);

            }
        });
        mNexButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDidCheat=false;
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton=(Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = mCurrentIndex-1;
                if(mCurrentIndex==-1)
                {
                    mCurrentIndex=mQuestionBank.length-1;
                }
                updateQuestion();
            }
        });
        mTrueButton =(Button) findViewById(R.id.true_button);
        mFalseButton= (Button) findViewById(R.id.false_button);

        //True button Click event listener
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        //False button Click event Listener
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        if(data==null)
            return;

        mDidCheat=data.getBooleanExtra(activity_cheat.DID_CHEAT_KEY,false);
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    private  void checkAnswer(boolean userPressedTrue)
    {
        boolean currentAnswer=mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId;

        if(mDidCheat)
        {
            messageResId = R.string.did_cheat;
        }
        else {

            if (userPressedTrue == currentAnswer) {
                messageResId = R.string.correct;
            } else {
                messageResId = R.string.incorrect;
            }
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_LONG).show();
    }
    private void updateQuestion()
    {
        int question= mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
