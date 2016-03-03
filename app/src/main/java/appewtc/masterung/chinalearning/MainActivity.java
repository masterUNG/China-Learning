package appewtc.masterung.chinalearning;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request Database
        myManage = new MyManage(this);

        //Test Add Value
        //testAddValue();

        //Delete All Data
        deleteAllData();

    }   // Main Method

    private void deleteAllData() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.learn_table, null, null);
    }

    private void testAddValue() {
        myManage.addLearn("testUnit", "ง่าย", "urlImage", "Vol", "read", "meaning", "urlSound");
    }

}   // Main Class
