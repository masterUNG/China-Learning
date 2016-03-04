package appewtc.masterung.chinalearning;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private MyManage myManage;
    public String[] unitStrings;
    private Button easyButton, mediumButton, hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Request Database
        myManage = new MyManage(this);

        //Test Add Value
        //testAddValue();

        //Delete All Data
        deleteAllData();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

        //Button Controller
        buttonController();

    }   // Main Method

    private void bindWidget() {
        easyButton = (Button) findViewById(R.id.button);
        mediumButton = (Button) findViewById(R.id.button2);
        hardButton = (Button) findViewById(R.id.button3);
    }

    private void buttonController() {
        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
    }

    private void synJSONtoSQLite() {

        //Connected http://
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intUnit = 0;
        while (intUnit <= 5) {

            //1. Create InputStream
            InputStream inputStream = null;
            String[] tableStrings = new String[6];
            tableStrings[0] = "http://swiftcodingthai.com/see/php_get_unit1.php";
            tableStrings[1] = "http://swiftcodingthai.com/see/php_get_unit2.php";
            tableStrings[2] = "http://swiftcodingthai.com/see/php_get_unit3.php";
            tableStrings[3] = "http://swiftcodingthai.com/see/php_get_unit4.php";
            tableStrings[4] = "http://swiftcodingthai.com/see/php_get_unit5.php";
            tableStrings[5] = "http://swiftcodingthai.com/see/php_get_test.php";

            unitStrings = new String[5];
            unitStrings[0] = "บทที่ 1";
            unitStrings[1] = "บทที่ 2";
            unitStrings[2] = "บทที่ 3";
            unitStrings[3] = "บทที่ 4";
            unitStrings[4] = "บทที่ 5";

            String tag = "China";

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(tableStrings[intUnit]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

            } catch (Exception e) {
                Log.d(tag, "InputStream ==> " + e.toString());
            }

            //2. Create JSON String
            String strJSON = null;
            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(strLine);
                }
                inputStream.close();
                strJSON = stringBuilder.toString();

            } catch (Exception e) {
                Log.d(tag, "JSON String ==> " + e.toString());
            }

            //3. Update to SQLite
            try {

                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (intUnit == 5) {

                        String strUnit = jsonObject.getString(MyManage.column_Unit);
                        String strQuestion = jsonObject.getString(MyManage.column_Question);
                        String strImage = jsonObject.getString(MyManage.column_Image);
                        String strSound = jsonObject.getString(MyManage.column_Sound);
                        String strChoice1 = jsonObject.getString(MyManage.column_Choice1);
                        String strChoice2 = jsonObject.getString(MyManage.column_Choice2);
                        String strChoice3 = jsonObject.getString(MyManage.column_Choice3);
                        String strChoice4 = jsonObject.getString(MyManage.column_Choice4);
                        String strAnser = jsonObject.getString(MyManage.column_Answer);

                        myManage.addTest(strUnit, strQuestion, strImage, strSound,
                                strChoice1, strChoice2, strChoice3, strChoice4, strAnser);

                    } else {

                        String strUnit = unitStrings[intUnit];
                        String strlevel = jsonObject.getString(MyManage.column_Level);
                        String strImage = jsonObject.getString(MyManage.column_Image);
                        String strVocap = jsonObject.getString(MyManage.column_Vocabulary);
                        String strRead = jsonObject.getString(MyManage.column_Read);
                        String strMeaning = jsonObject.getString(MyManage.column_Meaning);
                        String strSound = jsonObject.getString(MyManage.column_Sound);

                        myManage.addLearn(strUnit, strlevel, strImage, strVocap, strRead,
                                strMeaning, strSound);

                    }   // if

                }   // for


            } catch (Exception e) {
                Log.d(tag, "update ==> " + e.toString());
            }

            intUnit += 1;
        }   // while

    }   // synJSONtoSQLite

    private void deleteAllData() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.learn_table, null, null);
        sqLiteDatabase.delete(MyManage.test_table, null, null);
    }

    private void testAddValue() {
        myManage.addLearn("testUnit", "ง่าย", "urlImage", "Vol", "read", "meaning", "urlSound");
    }

    @Override
    public void onClick(View view) {

        String[] chooseStrings = {"Easy", "Medium", "Hard"};
        String userChoose = null;
        int intIndex = 0;

        switch (view.getId()) {

            case R.id.button:
                userChoose = chooseStrings[0];
                intIndex = 0;
                break;
            case R.id.button2:
                userChoose = chooseStrings[1];
                intIndex = 1;
                break;
            case R.id.button3:
                userChoose = chooseStrings[2];
                intIndex = 2;
                break;

        }   // switch

        Intent intent = new Intent(MainActivity.this, Hub12Unit.class);
        intent.putExtra("userChoose", userChoose);
        intent.putExtra("index", intIndex);
        startActivity(intent);


    }   // onClick

}   // Main Class
