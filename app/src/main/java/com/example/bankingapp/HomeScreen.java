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

        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,users.class);
                startActivity(intent);
            }
        });
    }
}