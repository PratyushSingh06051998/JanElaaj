package com.example.pratyushsingh.janelaaj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pratyushsingh on 21/05/18.
 */

public class FragmentDatabase2 extends Fragment {

    TextView dev;
    TextView man;
    TextView sale;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_databse2,container,false);

        dev = (TextView) v.findViewById(R.id.timedev);
        man  = (TextView) v.findViewById(R.id.timeman);
        sale = (TextView) v.findViewById(R.id.timesale);



        return v;
    }


    void setVAl(int d,int m,int s){
        dev.setText(String.valueOf(d));
        man.setText(String.valueOf(m));
        sale.setText(String.valueOf(s));
    }

}
