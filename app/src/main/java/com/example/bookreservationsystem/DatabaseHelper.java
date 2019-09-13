package com.example.bookreservationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "brs.db";
    public static final String TABLE_NAME = "Member_table";
    public static final String TABLE_NAME1 = "Book_table";
    public static final String TABLE_NAME2 = "Reserved_Books";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "NIC";
    public static final String COL_4 = "Mobile";
    public static final String COL_5 = "BookID";
    public static final String COL_6 = "Book_Name";
    public static final String COL_7 = "Author_Name";
    public static final String COL_8 = "Published_Year";
    public static final String COL_9 = "Reserved_Id";
    public static final String COL_10 = "Member_Id";
    public static final String COL_11 = "Book_Name";
    public static final String COL_12 = "Book_Id";
    public static final String COL_13 = "date";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME +"(ID INTEGER PRIMARY KEY  AUTOINCREMENT, Name TEXT, NIC TEXT, Mobile TEXT)");
        db.execSQL("CREATE TABLE " +TABLE_NAME1 +"(BookID INTEGER PRIMARY KEY AUTOINCREMENT,Book_Name TEXT,Author_Name TEXT,Published_Year TEXT)");
        db.execSQL("CREATE TABLE " +TABLE_NAME2 +"(Reserved_Id INTEGER PRIMARY KEY  AUTOINCREMENT, Member_Id TEXT, Book_Name TEXT, Book_Id TEXT,date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertMember(String name, String nic, String mobile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, nic);
        contentValues.put(COL_4, mobile);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        else
            return true;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }

    public Cursor getMemberDetails(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_1 + "," + COL_3 + "," + COL_4+  " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '" + name + "'";
        Cursor data  = db.rawQuery(query, null);
        return data;
    }

    public void updateDetails(String newName, String newNic, String newMobile, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL_2 + " = '" + newName + "' , " + COL_3 + " = '" + newNic + "' , " + COL_4 + " = '" + newMobile + "' WHERE " + COL_1 + " = '" + id + "'";
        Log.d(TAG, "updateDetails: query: " + query);
        Log.d(TAG, "updateDetails: Setting name to " + newName);
        Log.d(TAG, "updateDetails: Setting nic to " + newNic);
        Log.d(TAG, "updateDetails: Setting mobile to " + newMobile);
        db.execSQL(query);
    }

    public void deleteMember(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME +" WHERE "+COL_1+" = '"+id+"'";
        db.execSQL(query);
    }

    public boolean insertBook(String Book_Name, String Author_Name, String Published_Year ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, Book_Name);
        contentValues.put(COL_7, Author_Name);
        contentValues.put(COL_8, Published_Year);
        long result = db.insert(TABLE_NAME1,null,contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
        return data;
    }

    public Cursor getBookDetails(String name){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_5 + "," + COL_7 + "," + COL_8+  " FROM " + TABLE_NAME1 + " WHERE " + COL_6 + " = '" + name + "'";
        Cursor data  = db.rawQuery(query, null);
        return data;

    }

    public void updateBookDetails(String newBookName, String newAuthor, String newYear, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME1 + " SET " + COL_6 + " = '" + newBookName + "' , " + COL_7 + " = '" + newAuthor + "' , " + COL_8 + " = '" + newYear + "' WHERE " + COL_5 + " = '" + id + "'";
        Log.d(TAG, "updateDetails: query: " + query);
        Log.d(TAG, "updateDetails: Setting name to " + newBookName);
        Log.d(TAG, "updateDetails: Setting author to " + newAuthor);
        Log.d(TAG, "updateDetails: Setting year to " + newYear);
        db.execSQL(query);

    }

    public void deleteBook(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME1 +" WHERE "+COL_5+" = '"+id+"'";
        db.execSQL(query);
    }


    public boolean insertReservation(String memberId, String bookName, String bookId,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_10, memberId);
        contentValues.put(COL_11, bookName);
        contentValues.put(COL_12, bookId);
        contentValues.put(COL_13, date);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        if(result == -1){
            return false;
        }
        else
            return true;
    }

    public Cursor getReservedListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME2,null);
        return data;
    }

    public Cursor getReserved(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_9 + "," + COL_10 + "," + COL_12+  "," + COL_13 + " FROM " + TABLE_NAME2 + " WHERE " + COL_11 + " = '" + name + "'";
        Cursor data  = db.rawQuery(query, null);
        return data;
    }

    public void updateReserveDetails(String newDate, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME2 + " SET " + COL_13 + " = '" + newDate + "' WHERE " + COL_9 + " = '" + id + "'";
        Log.d(TAG, "updateDetails: query: " + query);
        Log.d(TAG, "updateDetails: Setting date to " + newDate);
        db.execSQL(query);

    }

    public void deleteReservation(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME2 +" WHERE "+COL_9+" = '"+id+"'";
        db.execSQL(query);
    }

}
