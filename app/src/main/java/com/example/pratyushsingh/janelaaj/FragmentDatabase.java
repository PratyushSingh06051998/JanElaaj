package com.example.pratyushsingh.janelaaj;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by pratyushsingh on 20/05/18.
 */

public class FragmentDatabase extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<FormSendStructure> mList;
    RecyclerDatabase1 recyclerDatabase1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_database,container,false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recy);
        mList = new ArrayList<>();

        recyclerDatabase1 = new RecyclerDatabase1(mList,getActivity());
        mRecyclerView.setAdapter(recyclerDatabase1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerDatabase1.notifyDataSetChanged();


        populate();

        return v;
    }


    void populate(){

        mList.clear();
        LocalDATA localDATA = new LocalDATA(getActivity());
        SQLiteDatabase database = localDATA.getReadableDatabase();


        String fname="";
        String lname="";
        int age=0;
        int idid=0;
        String  did="";

        Cursor c = database.query(LocalDATA.QUESTIONEER_TABLENAME,null,null,null,null,null,null,null);
        while (c.moveToNext()){

            fname = c.getString(c.getColumnIndex(LocalDATA.QUESTIONEER_FIRSTNAME));
            lname = c.getString(c.getColumnIndex(LocalDATA.QUESTIONEER_LASTNAME));
            age = c.getInt(c.getColumnIndex(LocalDATA.QUESTIONEER_AGE));
            idid = c.getInt(c.getColumnIndex(LocalDATA.QUESTIONEER_IDID));
            did = c.getString(c.getColumnIndex(LocalDATA.QUESTIONEER_DID));
            FormSendStructure p = new FormSendStructure(fname,lname,age,did,idid);
            mList.add(p);

        }

        recyclerDatabase1.notifyDataSetChanged();

    }


}


