package com.example.pratyushsingh.janelaaj;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pratyushsingh on 21/05/18.
 */

public class LocalDATA extends SQLiteOpenHelper {



    public static String QUESTIONEER_TABLENAME="questioneerinformation";
    public static String QUESTIONEER_ID="_id";
    public static String QUESTIONEER_FIRSTNAME="firstname";
    public static String QUESTIONEER_LASTNAME="lastname";
    public static String QUESTIONEER_AGE="age";
    public static String QUESTIONEER_DID="did";
    public static String QUESTIONEER_IDID="idid";


    public LocalDATA(Context context) {
        super(context, "QuestioneerInformation.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String query;
        query = " create table " + QUESTIONEER_TABLENAME + " ( " + QUESTIONEER_ID + " integer primary key autoincrement, " + QUESTIONEER_FIRSTNAME + " text, "
                + QUESTIONEER_LASTNAME + " text, " + QUESTIONEER_AGE + " integer, " + QUESTIONEER_DID + " text, " + QUESTIONEER_IDID + " integer);";
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
