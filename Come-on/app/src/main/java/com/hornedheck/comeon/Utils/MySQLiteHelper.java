package com.hornedheck.comeon.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TasksDB";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INFO = "info";
    private static final String KEY_DATE = "date";
    private static final String KEY_DONE = "done";

    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + DB_NAME + " ( " + KEY_ID + " integer" + "," + KEY_NAME + " text , " + KEY_INFO + " text , " + KEY_DATE + " text , " + KEY_DONE + " integer" + " );";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }
}
