package appewtc.masterung.chinalearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreActivity extends AppCompatActivity {

    //Explicit
    private TextView titleTextView, dateTextView, scoreTextView;
    private Button startButton, learnButton;
    private String unitString, dateString, scoreString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

    }   // Main Method

    private void showView() {

        unitString = getIntent().getStringExtra("Unit");
        int intScore = getIntent().getIntExtra("Score", 0);
        scoreString = Integer.toString(intScore);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        dateString = dateFormat.format(date);

        titleTextView.setText(unitString);
        dateTextView.setText(dateString);
        scoreTextView.setText("คะแนน = " + scoreString);

    }   // showView

    private void bindWidget() {
        titleTextView = (TextView) findViewById(R.id.textView13);
        dateTextView = (TextView) findViewById(R.id.textView14);
        scoreTextView = (TextView) findViewById(R.id.textView15);
        startButton = (Button) findViewById(R.id.button7);
        learnButton = (Button) findViewById(R.id.button8);
    }

}   //Main Class
