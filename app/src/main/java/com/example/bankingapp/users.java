package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class users extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> user_name;

    customAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);

        myDB = new DatabaseHelper(this);
        user_name = new ArrayList<>();

        storedDatainArray();

        customAdapter = new customAdapter(users.this,user_name);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(users.this));
    }

    void storedDatainArray()
    {
        Cursor cursor = myDB.readalldata();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                user_name.add(cursor.getString(0));
            }
        }
    }

}