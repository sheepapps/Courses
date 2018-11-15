package com.hornedheck.comeon.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hornedheck.comeon.Task;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TasksDB";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INFO = "info";
    private static final String KEY_DATE = "date";
    private static final String KEY_DONE = "done";
    private SQLiteDatabase db;

    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + DB_NAME + " ( " + KEY_ID + " text" + "," + KEY_NAME + " text , " + KEY_INFO + " text , " + KEY_DATE + " text , " + KEY_DONE + " integer" + " );";
        sqLiteDatabase.execSQL(create);
        db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
        db = sqLiteDatabase;
    }

    public void putTask(Task task) {
        db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_NAME, task.getName());
        value.put(KEY_INFO, task.getInfo());
        value.put(KEY_DATE, task.getDateString());
        value.put(KEY_DONE, task.isDone());
        value.put(KEY_ID, task.getId());
        db.insert(DB_NAME, null, value);
    }

    public Task getTask(String id) {
        Cursor cursor = db.query(DB_NAME, new String[]{KEY_ID, KEY_NAME, KEY_INFO, KEY_DATE, KEY_DONE}, KEY_ID + " = ?", new String[]{id}, null, null, null);
        cursor.moveToFirst();
        int datePos = cursor.getColumnIndex(KEY_DATE);
        int infoPos = cursor.getColumnIndex(KEY_INFO);
        int namePos = cursor.getColumnIndex(KEY_NAME);
        int donePos = cursor.getColumnIndex(KEY_DONE);
        int IDPos = cursor.getColumnIndex(KEY_ID);
        Task task = new Task(cursor.getString(namePos), cursor.getString(infoPos), cursor.getString(donePos), cursor.getString(datePos), cursor.getString(IDPos));
        cursor.close();
        return task;
    }

    public List<Task> getAllTasks() {
        SQLiteDatabase db = getReadableDatabase();
        if (DatabaseUtils.queryNumEntries(db, DB_NAME) > 0) {
            Cursor cursor = db.query(DB_NAME, new String[]{KEY_ID, KEY_NAME, KEY_INFO, KEY_DATE, KEY_DONE}, null, null, null, null, null);
            int datePos = cursor.getColumnIndex(KEY_DATE);
            int infoPos = cursor.getColumnIndex(KEY_INFO);
            int namePos = cursor.getColumnIndex(KEY_NAME);
            int donePos = cursor.getColumnIndex(KEY_DONE);
            int IDPos = cursor.getColumnIndex(KEY_ID);
            cursor.moveToFirst();
            ArrayList<Task> tasks = new ArrayList<>();
            do {
                tasks.add(new Task(cursor.getString(namePos), cursor.getString(infoPos), cursor.getString(donePos), cursor.getString(datePos), cursor.getString(IDPos)));
            } while (cursor.moveToNext());
            cursor.close();
            return tasks;
        } else {
            return new ArrayList<>();
        }
    }
}
