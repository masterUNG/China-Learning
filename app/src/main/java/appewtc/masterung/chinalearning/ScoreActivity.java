package appewtc.masterung.chinalearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

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

        //Button Controller
        buttonController();

    }   // Main Method

    private void buttonController() {
        startButton.setOnClickListener(this);
        learnButton.setOnClickListener(this);
    }

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

        MyManage myManage = new MyManage(this);
        myManage.addUser(dateString, unitString, scoreString);

    }   // showView

    private void bindWidget() {
        titleTextView = (TextView) findViewById(R.id.textView13);
        dateTextView = (TextView) findViewById(R.id.textView14);
        scoreTextView = (TextView) findViewById(R.id.textView15);
        startButton = (Button) findViewById(R.id.button7);
        learnButton = (Button) findViewById(R.id.button8);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button7:

                Intent intent = new Intent(ScoreActivity.this, TestHubActivity.class);
                intent.putExtra("Unit", unitString);
                startActivity(intent);
                finish();

                break;
            case R.id.button8:

                Intent intent1 = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();

                break;
        }   // switch

    }   // onClick

}   //Main Class
