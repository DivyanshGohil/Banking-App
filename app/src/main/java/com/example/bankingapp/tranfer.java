package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;

public class tranfer extends AppCompatActivity {

    DatabaseHelper myDB = new DatabaseHelper(this);

    TextView sender,receiver;
    EditText amount;
    Button paybtn;

    int amount_int,amt_int,r_amount_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranfer);
        sender = findViewById(R.id.sender_txt);
        receiver = findViewById(R.id.reciever_txt);
        amount = findViewById(R.id.user_amt);
        paybtn = findViewById(R.id.pay_btn);

        String sender_str = getIntent().getStringExtra("s_name");
        String receiver_str = getIntent().getStringExtra("r_name");
        String sender_acc_str = getIntent().getStringExtra("s_acc");
        String receiver_acc_str = getIntent().getStringExtra("r_acc");
        String amount_str = getIntent().getStringExtra("s_amt");
        String amount_str2 = getIntent().getStringExtra("r_amt");

        sender.setText(sender_str);
        receiver.setText(receiver_str);

        Log.d("my app","Balance is: "+amount_str);


        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amt = amount.getText().toString();
                try {
                    amt_int = NumberFormat.getInstance().parse(amt).intValue();
                    Log.d("my app","Int value: "+amt_int);
                    amount_int = NumberFormat.getInstance().parse(amount_str).intValue();
                    Log.d("my app", String.valueOf(amount_int));
                    r_amount_int = NumberFormat.getInstance().parse(amount_str2).intValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(amt_int < 0){
                    Toast.makeText(tranfer.this,"GIVEN AMOUNT CANNOT BE NEGATIVE",Toast.LENGTH_SHORT).show();
                }else if(amt_int == 0){
                    Toast.makeText(tranfer.this,"GIVEN AMOUNT CANNOT BE ZERO",Toast.LENGTH_SHORT).show();
                }else if(amt_int > amount_int){
                    Toast.makeText(tranfer.this,"NOT ENOUGH BALANCE",Toast.LENGTH_LONG).show();
                }else{
                    int amt_result1 =amount_int - amt_int;
                    int amt_result2 = r_amount_int + amt_int;
                    //String amt_result_str1 = String.valueOf(amt_result1);
                    //String amt_result_str2 = String.valueOf(amt_result2);
                    boolean result1 = myDB.UpdateValues(sender_acc_str,amt_result1);
                    boolean result2 = myDB.UpdateValues(receiver_acc_str,amt_result2);
                    if(result1 && result2){
                        Toast.makeText(tranfer.this,"DATABASE UPDATED",Toast.LENGTH_SHORT).show();
                        boolean result = myDB.insertdata_tranfer(sender_str, receiver_str, amt);
                        if(result){
                            Toast.makeText(tranfer.this,"Transaction Added to Tranfer Table",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(tranfer.this,"Error in Transaction Adding to Tranfer Table",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(tranfer.this, "SOME ERROR", Toast.LENGTH_SHORT).show();
                    }


                    Intent intent = new Intent(tranfer.this,users.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}