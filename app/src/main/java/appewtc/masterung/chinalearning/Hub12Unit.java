package appewtc.masterung.chinalearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Hub12Unit extends AppCompatActivity {

    //Explicit
    private TextView showUserChooseTextView;
    private ListView unit12ListView;
    private Button loadMoreButton;
    private String userChooseString;
    private int intIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub12_unit);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

        //Create ListView
        createListView();

        //Button Controller
        buttonController();

    }   // Main Method

    private void buttonController() {
        loadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hub12Unit.this, HubAllUnit.class);
                intent.putExtra("userChoose", userChooseString);
                intent.putExtra("index", intIndex);
                startActivity(intent);
                finish();

            }
        });
    }

    private void createListView() {

        String[] titleStrings = {"บทที่ 1", "บทที่ 2"};

        MyAdapter myAdapter = new MyAdapter(Hub12Unit.this, titleStrings);
        unit12ListView.setAdapter(myAdapter);

    }   // createListView

    private void showView() {
        userChooseString = getIntent().getStringExtra("userChoose");
        intIndex = getIntent().getIntExtra("index", 0);
        String[] showTextStrings = {"ง่าย", "ปานกลาง", "ยาก"};

        showUserChooseTextView.setText(showTextStrings[intIndex]);
    }

    private void bindWidget() {
        showUserChooseTextView = (TextView) findViewById(R.id.textView2);
        unit12ListView = (ListView) findViewById(R.id.listView);
        loadMoreButton = (Button) findViewById(R.id.button4);
    }

}   // Main Class
