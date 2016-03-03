package appewtc.masterung.chinalearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Hub12Unit extends AppCompatActivity {

    //Explicit
    private TextView showUserChooseTextView;
    private ListView unit12ListView;
    private Button loadMoreButton;
    private String userChooseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub12_unit);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

    }   // Main Method

    private void showView() {
        userChooseString = getIntent().getStringExtra("userChoose");
        int intIndex = getIntent().getIntExtra("index", 0);
        String[] showTextStrings = {"ง่าย", "ปานกลาง", "ยาก"};

        showUserChooseTextView.setText(showTextStrings[intIndex]);
    }

    private void bindWidget() {
        showUserChooseTextView = (TextView) findViewById(R.id.textView2);
        unit12ListView = (ListView) findViewById(R.id.listView);
        loadMoreButton = (Button) findViewById(R.id.button4);
    }

}   // Main Class
