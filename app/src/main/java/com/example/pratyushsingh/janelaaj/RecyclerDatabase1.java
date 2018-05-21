package com.example.pratyushsingh.janelaaj;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pratyushsingh on 21/05/18.
 */

public class RecyclerDatabase1 extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<FormSendStructure> list;
    Context context;

    public RecyclerDatabase1(ArrayList<FormSendStructure> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_databse1,parent,false);

        return new VIEWHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        FormSendStructure f = list.get(position);
        ((VIEWHolder)holder).id.setText(String.valueOf(f.getId()));
        ((VIEWHolder)holder).name.setText(f.getFname()+" "+f.getLname());
        ((VIEWHolder)holder).age.setText(String.valueOf(f.getAge()));
        ((VIEWHolder)holder).depid.setText(f.getDid());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VIEWHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView age;
        TextView depid;
        TextView id;


        public VIEWHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameid);
            age = (TextView) itemView.findViewById(R.id.ageid);
            depid = (TextView) itemView.findViewById(R.id.depid);
            id = (TextView) itemView.findViewById(R.id.idid);

        }
    }

}
