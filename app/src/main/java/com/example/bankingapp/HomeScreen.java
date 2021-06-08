package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    TextView text1, text2;
    Animation blink_anim;
    Button userbtn;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
// Blink the text in home page
        text1 = (TextView) findViewById(R.id.user_text);
        text2 = (TextView) findViewById(R.id.info);

        blink_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);

        userbtn = (Button) findViewById(R.id.user_button);

        text1.startAnimation(blink_anim);
        text2.startAnimation(blink_anim);

        myDb  = new DatabaseHelper(this);
        //Insert values
        AddDummy();

        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,users.class);
                startActivity(intent);
            }
        });
    }

    public void AddDummy(){
        //inserting 10 dummy data
        myDb.insertData("1111111111","Aakash patel","xyz@gmail.com","21","0264211111","10000");
        myDb.insertData("2222222222","Lakshansh padhiyar","xyz@gmail.com","30","0264222222","25000");
        myDb.insertData("3333333333","Bhargav gohil","xyz@gmail.com","25","0264233333","15000");
        myDb.insertData("4444444444","vikas sarvaiya","xyz@gmail.com","45","0264244444","45000");
        myDb.insertData("5555555555","Piyush patel","xyz@gmail.com","38","0264255555","30000");
        myDb.insertData("6666666666","vivek modi","xyz@gmail.com","41","0264266666","60000");
        myDb.insertData("7777777777","selmon bhai","xyz@gmail.com","20","0264277777","5000");
        myDb.insertData("8888888888","Ruchit yadav","xyz@gmail.com","19","0264288888","3500");
        myDb.insertData("9999999999","Sagar prajapati","xyz@gmail.com","55","0264299999","65000");
        myDb.insertData("1234567890","Carry minati","xyz@gmail.com","75","0264200000","150000");
    }
}