package com.example.android.dinosaurquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final String ANSWER_MESSAGE = "filler";

    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Created to help minimize the keyboard after focus is lost from EditText field
     * Doesn't exactly work in all cases..Thoughts on how to do this better???
     * @param event for motion action of removing focus from the EditText box
     * @return returns the motion event
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //trying to use ACTION_DOWN for press
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
           //checks the currently focused view
            View v = getCurrentFocus();
            //makes sure we are only doing this for EditText views
            if ( v instanceof EditText) {
                Rect myRect = new Rect();
                v.getGlobalVisibleRect(myRect);
                //when EditText no longer has focus tries to clear and remove keyboard
                if (!myRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    /**
     * The method is called when users clicks the 'Submit Answers' button
     * This has the answer reporting logic and opens the answers in a new activity
     * @param view ?
     */
    public void submitAnswers(View view) {
        //initialize required variables
        Context context = getApplicationContext();
        String answerOne;
        answerOne = getString(R.string.answerOne) + "\n";
        String answerTwo;
        answerTwo = getString(R.string.answerTwo) + "\n";
        String answerThree;
        answerThree = getString(R.string.answerThree) + "\n";
        String answerFour;
        answerFour = getString(R.string.answerFour) + "\n";
        String firstQuestion = questionOneState();
        String secondQuestion = questionTwoState();
        String thirdQuestion = questionThreeState();
        String fourthQuestion = questionFourState();

        //fire off our toast for score
        CharSequence text = "You got " + score + " out of 4.";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Set our Answers for the DisplayAnswerActivity
        firstQuestion += answerOne;
        secondQuestion += answerTwo;
        thirdQuestion += answerThree;
        fourthQuestion += answerFour;


        // declare the intent, set the message to be passed, and call the new activity
        Intent intent = new Intent(this, DisplayAnswersActivity.class);
        String message = firstQuestion + "\n" + secondQuestion + "\n" + thirdQuestion + "\n" + fourthQuestion;
        intent.putExtra(ANSWER_MESSAGE, message);
        score = 0;
        startActivity(intent);
    }
    /**
     * Get the button state and set the value based on answer logic
     * @return value returned tells if answer is correct or not
     */
    private String questionOneState(){
        String questionOne = "Question 1: ";

        RadioButton radioTrue = findViewById(R.id.true_radio);
        boolean radioStateTrue = radioTrue.isChecked();

        if (radioStateTrue){
            questionOne += "Correct";
            score += 1;
            return questionOne;
        }
        else {
            questionOne += "Incorrect";
            return questionOne;
        }
    }
    /**
     * Get the button state and set the value based on answer logic
     * @return value returned tells if answer is correct or not
     */
    private String questionTwoState(){
        String questionTwo = "Question 2: ";

        RadioButton radioTrue = findViewById(R.id.one_radio);
        boolean radioStateTrue = radioTrue.isChecked();

        if (radioStateTrue){
            questionTwo += "Correct";
            score += 1;
            return questionTwo;
        }
        else {
            questionTwo += "Incorrect";
            return questionTwo;
        }
    }
    /**
     * Get the button state and set the value based on answer logic
     * @return value returned tells if answer is correct or not
     */
    private String questionThreeState(){
        String questionThree = "Question 3: ";
        EditText lrgDinoEditBox = findViewById(R.id.fill_in_blank);
        String lrgDinoBoxState = lrgDinoEditBox.getText().toString().trim();

        if ("argentinosaurus".equalsIgnoreCase(lrgDinoBoxState)){
            questionThree += "Correct";
            score += 1;
            return questionThree;

        }
        else {
            questionThree += "Incorrect";
            return questionThree;
        }
    }
    /**
     * Get the button state and set the value based on answer logic
     * @return value returned tells if answer is correct or not
     */
    private String questionFourState(){
        String questionFour = "Question 4: ";
        CheckBox allosaurusBox = findViewById(R.id.allosaurus);
        boolean allosaurusBoxState = allosaurusBox.isChecked();

        CheckBox argentinosaurusBox = findViewById(R.id.argentinosaurus);
        boolean argentinosaurusBoxState = argentinosaurusBox.isChecked();

        CheckBox diplodocusBox = findViewById(R.id.diplodocus);
        boolean diplodocusBoxState = diplodocusBox.isChecked();

        CheckBox mapusaurusBox = findViewById(R.id.mapusaurus);
        boolean mapusaurusBoxState = mapusaurusBox.isChecked();

       if (!allosaurusBoxState && diplodocusBoxState && argentinosaurusBoxState &&!mapusaurusBoxState){

          questionFour += "Correct";
          score += 1;
          return questionFour;
       }
       else {
          questionFour += "Incorrect";
          return questionFour;
       }
    }
}
