package appewtc.masterung.chinalearning;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
    private String[] questionStrings, imageStrings, soundStrings,
            choice1Strings, choice2Strings, choice3Strings, choice4Strings, answerStrings;
    private int timesAnInt = 0, userChooseAnInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hub);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

    } // Main Method

    private void radioController() {

        choiceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radioButton:
                        userChooseAnInt = 1;
                        break;
                    case R.id.radioButton2:
                        userChooseAnInt = 2;
                        break;
                    case R.id.radioButton3:
                        userChooseAnInt = 3;
                        break;
                    case R.id.radioButton4:
                        userChooseAnInt = 4;
                        break;
                    default:
                        userChooseAnInt = 0;
                        break;
                } // switch

            }   // event
        });

    } //radioController

    private void buttonController() {

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userChooseAnInt == 0) {
                    Toast.makeText(TestHubActivity.this, "กรุณาเลือก Choice", Toast.LENGTH_SHORT).show();
                } else {

                    checkTimes();

                }   // if

            }   // event
        });

    }   // buttonController

    private void checkTimes() {

        timesAnInt += 1;
        if (timesAnInt < questionStrings.length) {
            changeView(timesAnInt);
        } else {

            Intent intent = new Intent(TestHubActivity.this, ScoreActivity.class);
            startActivity(intent);

        }   // if

    }

    private void showView() {

        unitString = getIntent().getStringExtra("Unit");
        titleTextView.setText("แบบทดสอบ " + unitString);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM testTABLE WHERE Unit = " + "'" + unitString + "'", null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        questionStrings = new String[intCount];
        imageStrings = new String[intCount];
        soundStrings = new String[intCount];
        choice1Strings = new String[intCount];
        choice2Strings = new String[intCount];
        choice3Strings = new String[intCount];
        choice4Strings = new String[intCount];
        answerStrings = new String[intCount];

        for (int i = 0; i < intCount; i++) {

            questionStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Question));
            imageStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image));
            soundStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Sound));
            choice1Strings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Choice1));
            choice2Strings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Choice2));
            choice3Strings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Choice3));
            choice4Strings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Choice4));
            answerStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Answer));

            cursor.moveToNext();
        }   //for
        cursor.close();

        changeView(0);

    }   // showView

    private void changeView(int index) {

        questionTextView.setText(questionStrings[index]);
        choice1RadioButton.setText(choice1Strings[index]);
        choice2RadioButton.setText(choice2Strings[index]);
        choice3RadioButton.setText(choice3Strings[index]);
        choice4RadioButton.setText(choice4Strings[index]);

        Picasso.with(TestHubActivity.this)
                .load(imageStrings[index]).resize(180, 180).into(questionImageView);


    }   // changeView

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
        answerButton = (Button) findViewById(R.id.button6);

    } // bindWidget

}   // Main Class
