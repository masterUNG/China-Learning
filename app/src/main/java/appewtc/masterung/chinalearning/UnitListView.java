package appewtc.masterung.chinalearning;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        //Create ListView
        createListView();


    }   // Main Method

    private void createListView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM learnTABLE WHERE Unit = " + "'" + unitString + "'" + " AND Level = " + "'" + levelString + "'" , null);
        cursor.moveToFirst();

        final String[] iconStrings = new String[cursor.getCount()];
        final String[] vocabularyStrings = new String[cursor.getCount()];
        final String[] readStrings = new String[cursor.getCount()];
        final String[] meaningStrings = new String[cursor.getCount()];
        final String[] soundStrings = new String[cursor.getCount()];


        for (int i=0;i<cursor.getCount();i++) {

            iconStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image));
            vocabularyStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Vocabulary));
            readStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Read));
            meaningStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Meaning));
            soundStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Sound));

            cursor.moveToNext();
        }   //for
        cursor.close();

        UnitAdapter unitAdapter = new UnitAdapter(UnitListView.this, iconStrings, vocabularyStrings);
        detailListView.setAdapter(unitAdapter);

        detailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(UnitListView.this, DetailActivity.class);
                intent.putExtra("Title", showTitle.getText().toString());
                intent.putExtra("urlImage", iconStrings[i]);
                intent.putExtra("Vocabulary", vocabularyStrings[i]);
                intent.putExtra("Read", readStrings[i]);
                intent.putExtra("Meaning", meaningStrings[i]);
                intent.putExtra("urlSound", soundStrings[i]);
                startActivity(intent);

            }   // event
        });

    }   // createListView

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
