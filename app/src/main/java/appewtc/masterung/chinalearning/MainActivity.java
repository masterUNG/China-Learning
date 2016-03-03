package appewtc.masterung.chinalearning;

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
        testAddValue();

    }   // Main Method

    private void testAddValue() {
        myManage.addLearn("testUnit", "ง่าย", "urlImage", "Vol", "read", "meaning", "urlSound");
    }

}   // Main Class
