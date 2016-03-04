package appewtc.masterung.chinalearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TestHubActivity extends AppCompatActivity {

    //Explicit
    private TextView titleTextView, questionTextView;
    private ImageView questionImageView, speackerImageView,
            choice1ImageView, choice2ImageView, choice3ImageView, choice4ImageView;
    private RadioGroup choiceRadioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton, choice4RadioButton;
    private Button answerButton;
    private String unitString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hub);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

    } // Main Method

    private void showView() {

        unitString = getIntent().getStringExtra("Unit");
        titleTextView.setText("แบบทดสอบ " + unitString);

    }   // showView

    private void bindWidget() {

        titleTextView = (TextView) findViewById(R.id.textView11);
        questionTextView = (TextView) findViewById(R.id.textView12);
        questionImageView = (ImageView) findViewById(R.id.imageView4);
        speackerImageView = (ImageView) findViewById(R.id.imageView5);
        choice1ImageView = (ImageView) findViewById(R.id.imageView6);
        choice2ImageView = (ImageView) findViewById(R.id.imageView7);
        choice3ImageView = (ImageView) findViewById(R.id.imageView8);
        choice4ImageView = (ImageView) findViewById(R.id.imageView9);
        choiceRadioGroup = (RadioGroup) findViewById(R.id.ragChoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);


    } // bindWidget

}   // Main Class
