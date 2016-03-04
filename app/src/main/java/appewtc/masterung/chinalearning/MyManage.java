package appewtc.masterung.chinalearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 3/3/16 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String learn_table = "learnTABLE";
    public static final String column_Unit = "Unit";
    public static final String column_Level = "Level";
    public static final String column_Image = "Image";
    public static final String column_Vocabulary = "Vocabulary";
    public static final String column_Read = "Read";
    public static final String column_Meaning = "Meaning";
    public static final String column_Sound = "Sound";

    public static final String test_table = "testTABLE";
    public static final String column_Question = "Question";
    public static final String column_Choice1 = "Choice1";
    public static final String column_Choice2 = "Choice2";
    public static final String column_Choice3 = "Choice3";
    public static final String column_Choice4 = "Choice4";
    public static final String column_Answer = "Answer";

    public static final String user_table = "userTABLE";
    public static final String column_Date = "Date";
    public static final String column_Score = "Score";


    public MyManage(Context context) {

        //Create & Connected
        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addUser(String strDate,
                        String strUnit,
                        String strScore) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Date, strDate);
        contentValues.put(column_Unit, strUnit);
        contentValues.put(column_Score, strScore);

        return writeSqLiteDatabase.insert(user_table, null, contentValues);
    }

    public long addTest(String strUnit,
                        String strQuestion,
                        String strImage,
                        String strSound,
                        String strChoice1,
                        String strChoice2,
                        String strChoice3,
                        String strChoice4,
                        String strAnswer) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Unit, strUnit);
        contentValues.put(column_Question, strQuestion);
        contentValues.put(column_Image, strImage);
        contentValues.put(column_Sound, strSound);
        contentValues.put(column_Choice1, strChoice1);
        contentValues.put(column_Choice2, strChoice2);
        contentValues.put(column_Choice3, strChoice3);
        contentValues.put(column_Choice4, strChoice4);
        contentValues.put(column_Answer, strAnswer);

        return writeSqLiteDatabase.insert(test_table, null, contentValues);
    }

    public long addLearn(String strUnit,
                         String strLevel,
                         String strImage,
                         String strVocabulary,
                         String strRead,
                         String strMeaning,
                         String strSound) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Unit, strUnit);
        contentValues.put(column_Level, strLevel);
        contentValues.put(column_Image, strImage);
        contentValues.put(column_Vocabulary, strVocabulary);
        contentValues.put(column_Read, strRead);
        contentValues.put(column_Meaning, strMeaning);
        contentValues.put(column_Sound, strSound);

        return writeSqLiteDatabase.insert(learn_table, null, contentValues);
    }

}   // Main Class
