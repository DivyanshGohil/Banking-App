package com.example.bankingapp;

import android.content.Context;
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
    ArrayList name, account_no;

    public SelectUserAdapter(Context context, ArrayList name, ArrayList account_no) {
        this.context = context;
        this.name = name;
        this.account_no = account_no;
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
            openDialog();
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

    public void openDialog()
    {
        DialogExample dialogExample = new DialogExample();
        dialogExample.show(((AppCompatActivity) context).getSupportFragmentManager(),"Amount dialog");
    }
}
