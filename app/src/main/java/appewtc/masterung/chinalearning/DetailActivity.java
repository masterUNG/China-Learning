package appewtc.masterung.chinalearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    //Explicit
    private TextView titleTextView, vacabularyTextView, readTextView, meaningTextView;
    private ImageView myImageView, speackerImageView;
    private String titleString, vacabularyString, readString, meaningString,
            myImageString, soundURLString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

    }   // Main Method

    private void showView() {

        titleString = getIntent().getStringExtra("Title");
        vacabularyString = getIntent().getStringExtra("Vocabulary");
        readString = getIntent().getStringExtra("Read");
        meaningString = getIntent().getStringExtra("Meaning");

        myImageString = getIntent().getStringExtra("urlImage");
        soundURLString = getIntent().getStringExtra("urlSound");

        titleTextView.setText(titleString);
        vacabularyTextView.setText("คำศัพย์ " + vacabularyString);
        readTextView.setText("คำอ่าน " + readString);
        meaningTextView.setText("ความหมาย " + meaningString);

        Picasso.with(DetailActivity.this)
                .load(myImageString).resize(200, 200).into(myImageView);

    }   // showView

    private void bindWidget() {
        titleTextView = (TextView) findViewById(R.id.textView7);
        vacabularyTextView = (TextView) findViewById(R.id.textView8);
        readTextView = (TextView) findViewById(R.id.textView9);
        meaningTextView = (TextView) findViewById(R.id.textView10);
        myImageView = (ImageView) findViewById(R.id.imageView2);
        speackerImageView = (ImageView) findViewById(R.id.imageView3);

    }

}   // Main Class
