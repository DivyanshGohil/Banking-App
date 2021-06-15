package com.example.bankingapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder> {

    private Context context;
    private ArrayList name, acc_no, email, age, phone_no, amount;
    public customAdapter(Context context, ArrayList name, ArrayList acc_no, ArrayList email,
                         ArrayList age, ArrayList phone_no, ArrayList amount) {

        this.context = context;
        this.name = name;
        this.acc_no = acc_no;
        this.email = email;
        this.age = age;
        this.phone_no = phone_no;
        this.amount = amount;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.user_name_txt.setText(String.valueOf(name.get(position)));



        /*String name_txt = String.valueOf(name.get(position));
        String acc_txt = String.valueOf(acc_no.get(position));
        String email_txt = String.valueOf(email.get(position));
        String age_txt = String.valueOf(age.get(position));
        String phone_txt = String.valueOf(phone_no.get(position));*/
        String amount_txt = String.valueOf(amount.get(position));


        holder.user_name_txt.setOnClickListener((view)->{
            Intent intent = new Intent(context,users_info.class);
            intent.putExtra("single_user_name", String.valueOf(name.get(position)));
            intent.putExtra("single_user_acc_no", String.valueOf(acc_no.get(position)));
            intent.putExtra("single_user_email", String.valueOf(email.get(position)));
            intent.putExtra("single_user_age", String.valueOf(age.get(position)));
            intent.putExtra("single_user_phone_no", String.valueOf(phone_no.get(position)));
            intent.putExtra("single_user_amount", String.valueOf(amount.get(position)));
            Log.d("my app","user information sent to next acivity");
            Log.d("my app","values:" + amount_txt);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView user_name_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_name_txt = itemView.findViewById(R.id.user_name);

        }

    }


}
