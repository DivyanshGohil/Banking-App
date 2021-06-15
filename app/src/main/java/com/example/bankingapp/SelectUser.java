package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectUser extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name, account_no;
    DatabaseHelper myDB;
    SelectUserAdapter selectUserAdapter;

    //String account = getIntent().getStringExtra("account_no");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        String account = getIntent().getStringExtra("account_no");

        recyclerView = (RecyclerView) findViewById(R.id.select_user_recycleview);
        myDB = new DatabaseHelper(SelectUser.this);
        name = new ArrayList<>();
        account_no = new ArrayList<>();

        displayResult(account);

        selectUserAdapter = new SelectUserAdapter(SelectUser.this,name,account_no);
        recyclerView.setAdapter(selectUserAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SelectUser.this));
    }

    void displayResult(String account)
    {
        Cursor cursor = myDB.readDataWithoutSelect(account);
        if(cursor.getCount() == 0){
            Toast.makeText(this,"NO DATA",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                account_no.add(cursor.getString(0));
                name.add(cursor.getString(1));
            }
        }
    }

}