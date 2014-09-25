package edu.gw.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class activity_cheat extends Activity {

    private boolean mAnswer;
    private Button mShowAnswerButton;
    private TextView mAnswerTextView;
    //returning this vkey value to intent caller
    public static final String DID_CHEAT_KEY="didCheat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_cheat);
        setDidCheat(false);
        mAnswer = getIntent().getBooleanExtra("answer",false);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mAnswerTextView=(TextView)findViewById(R.id.answerTextView);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAnswer == true)
                {
                    mAnswerTextView.setText(R.string.true_button);
                }
                else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setDidCheat(true);
            }
        });
    }

    private void setDidCheat(boolean didCheat)
    {
        Intent data = new Intent();
        data.putExtra(DID_CHEAT_KEY,didCheat);
        setResult(RESULT_OK,data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_cheat, menu);
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
