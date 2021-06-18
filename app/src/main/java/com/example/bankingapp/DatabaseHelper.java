package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";
    public static final String USER_TABLE_NAME = "user_table";
    public static final String TRANFER_TABLE_NAME = "tranfer_table";

    public static final String USER_col1 = "account_no";
    public static final String USER_col2 = "name";
    public static final String USER_col3 = "email";
    public static final String USER_col4 = "age";
    public static final String USER_col5 = "phone";
    public static final String USER_col6 = "amount";

    public static final String TRANFER_col1 = "from_name";
    public static final String TRANFER_col2 = "to_name";
    public static final String TRANFER_col3 = "t_amount";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + USER_TABLE_NAME +" (account_no INTEGER PRIMARY KEY , name TEXT, email TEXT," +
                " age INTEGER, phone INTEGER, amount INTEGER ) ");
        db.execSQL("CREATE TABLE "+ TRANFER_TABLE_NAME+"(from_name TEXT, to_name TEXT, t_amount INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dummy = "NULL";

        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TRANFER_TABLE_NAME);

        onCreate(db);

    }

    public boolean insertData (String account_no,String name, String email, String age, String phone, String amount){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_col1,account_no);
        contentValues.put(USER_col2,name);
        contentValues.put(USER_col3,email);
        contentValues.put(USER_col4,age);
        contentValues.put(USER_col5,phone);
        contentValues.put(USER_col6,amount);

       long result = db.insert(USER_TABLE_NAME,null,contentValues);
       if(result == -1)
           return false;
       else
           return true;
    }
    public boolean insertdata_tranfer(String sender, String receiver, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANFER_col1,sender);
        contentValues.put(TRANFER_col2,receiver);
        contentValues.put(TRANFER_col3,amount);

        long result = db.insert(TRANFER_TABLE_NAME,null,contentValues);
        if(result == -1){
            return  false;
        }else{
            return true;
        }

    }
    public Cursor readalldata()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+USER_TABLE_NAME;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    /*public Cursor InsertTransaction(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT * FROM "+TRANFER_TABLE_NAME;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }*/

    Cursor readDataWithoutSelect(String account)
    {

        Log.d("my app","Given Account no is "+ account);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT * FROM "+ USER_TABLE_NAME+ " WHERE account_no != "+account;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return  cursor;
    }

    public boolean UpdateValues(String sender_acc, int amt){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("my app","updated value: "+amt);
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_col1,sender_acc);
        contentValues.put(USER_col6,amt);
        long result = db.update(USER_TABLE_NAME,contentValues,"account_no=?",new String[]{String.valueOf(sender_acc)});
        db.close();
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


}
