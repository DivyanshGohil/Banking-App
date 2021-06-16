package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class users_info extends AppCompatActivity {

    TextView mname, maccount, memail, mage, mphone, mamount;
    Button tra_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info);

        mname = (TextView) findViewById(R.id.single_user_name);
        maccount = (TextView) findViewById(R.id.single_user_account);
        memail = (TextView) findViewById(R.id.single_user_email);
        mage = (TextView) findViewById(R.id.single_user_age);
        mphone = (TextView) findViewById(R.id.single_user_phone);
        mamount = (TextView) findViewById(R.id.single_user_amt);

        tra_btn = (Button) findViewById(R.id.transfer);

        Log.d("my app","getIncomingIntent: checking for incoming intents");


        String name_txt = getIntent().getStringExtra("single_user_name");
        String acc_txt = getIntent().getStringExtra("single_user_acc_no");
        String email_txt = getIntent().getStringExtra("single_user_email");
        String age_txt = getIntent().getStringExtra("single_user_age");
        String phone_txt = getIntent().getStringExtra("single_user_phone_no");
        String amt_txt = getIntent().getStringExtra("single_user_amount");

        mname.setText(name_txt);
        maccount.setText(acc_txt);
        memail.setText(email_txt);
        mage.setText(age_txt);
        mphone.setText(phone_txt);
        mamount.setText(amt_txt);

        Log.d("my app","info set to activity");

        tra_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(users_info.this,SelectUser.class);
                intent.putExtra("account_no",acc_txt);
                intent.putExtra("name",name_txt);
                intent.putExtra("amount",amt_txt);
                startActivity(intent);
            }
        });
    }

}