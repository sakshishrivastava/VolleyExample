package com.techpalle.volleyexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sakshi1 on 25-08-2017.
 */

public class Details_Adapter extends RecyclerView.Adapter<Details_Adapter.MyViewHolder> {
    private List<Detail> detail_list;
    private Context context;
    public Details_Adapter(Context context,List<Detail> detail_list){
        this.detail_list=detail_list;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Detail detail = detail_list.get(position);
        holder.name.setText(detail.getName());
        holder.email.setText(detail.getEmail());
        holder.home.setText(detail.getHome());
        holder.mobile.setText(detail.getMobile());

    }

    @Override
    public int getItemCount() {
        return detail_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,email,home,mobile;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        email=itemView.findViewById(R.id.email);
        mobile = itemView.findViewById(R.id.phone);
        home=itemView.findViewById(R.id.home);}
    }
}
