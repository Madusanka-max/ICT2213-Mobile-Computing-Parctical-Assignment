package com.example.madusanka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quantum.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    public StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStudentsTable = "CREATE TABLE " + TABLE_STUDENTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PHONE + " TEXT," +
                COLUMN_TIMESTAMP + " TEXT" + ")";
        
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_AGE + " TEXT," +
                COLUMN_TIMESTAMP + " TEXT" + ")";
        
        db.execSQL(createStudentsTable);
        db.execSQL(createUsersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean saveStudent(String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_TIMESTAMP, timestamp);
        long result = db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return result != -1;
    }

    public boolean saveUser(String name, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_TIMESTAMP, timestamp);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public String getFormattedStudents() {
        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
        
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return "[NO DATA FOUND]";
        }
        
        result.append("━━━ QUANTUM DATABASE ━━━\n\n");
        
        int count = 1;
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE));
            String timestamp = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP));
            
            result.append(String.format("[%03d] %s\n", count++, name));
            result.append(String.format("◆ EMAIL: %s\n", email));
            result.append(String.format("◆ PHONE: %s\n", phone));
            result.append(String.format("◆ SAVED: %s\n", timestamp));
            result.append("━━━━━━━━━━\n\n");
        }
        
        cursor.close();
        db.close();
        return result.toString();
    }

    public int getStudentCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_STUDENTS, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }

    public boolean clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_STUDENTS, null, null);
        db.close();
        return result >= 0;
    }


}