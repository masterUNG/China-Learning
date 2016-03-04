package appewtc.masterung.chinalearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UnitListView extends AppCompatActivity {

    //Explicit
    private TextView showTitle;
    private ListView detailListView;
    private Button testButton;
    private String unitString, levelString;
    private int indexAnInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_list_view);

        //Bind Widget
        bindWidget();

        //Show View
        showView();


    }   // Main Method

    private void showView() {

        unitString = getIntent().getStringExtra("unit");
        levelString = getIntent().getStringExtra("userChoose");
        indexAnInt = getIntent().getIntExtra("index", 0);
        String[] showTextStrings = {"ง่าย", "ปานกลาง", "ยาก"};

        showTitle.setText(unitString + " " + showTextStrings[indexAnInt]);

    }   // showView

    private void bindWidget() {
        showTitle = (TextView) findViewById(R.id.textView5);
        detailListView = (ListView) findViewById(R.id.listView3);
        testButton = (Button) findViewById(R.id.button5);
    }

}   // Main Class
