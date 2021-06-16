package com.example.bankingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectUserAdapter extends RecyclerView.Adapter<SelectUserAdapter.MyViewHolder> {

    Context context;
    ArrayList name, account_no,amount;
    String first_account, first_name,second_name,second_account,first_amount,second_amount;

    public SelectUserAdapter(Context context, ArrayList name, ArrayList account_no, ArrayList amount) {
        this.context = context;
        this.name = name;
        this.account_no = account_no;
        this.amount = amount;
    }

    @NonNull
    @Override
    public SelectUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.select_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectUserAdapter.MyViewHolder holder, int position) {
        holder.mname.setText(String.valueOf(name.get(position)));


        holder.mname.setOnClickListener(view->{
            second_name = String.valueOf(name.get(position));
            second_account = String.valueOf(account_no.get(position));
            second_amount = String.valueOf(amount.get(position));

            Intent intent  = new Intent(context,tranfer.class);
            intent.putExtra("s_name",first_name);
            intent.putExtra("s_acc",first_account);
            intent.putExtra("s_amt",first_amount);
            intent.putExtra("r_name",second_name);
            intent.putExtra("r_acc",second_account);
            intent.putExtra("r_amt",second_amount);
            context.startActivity(intent);


        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mname = (TextView) itemView.findViewById(R.id.select_user_name);
        }
    }


    public void Sender(String account, String name, String amt){
        first_account = account;
        first_name = name;
        first_amount = amt;
    }
}
