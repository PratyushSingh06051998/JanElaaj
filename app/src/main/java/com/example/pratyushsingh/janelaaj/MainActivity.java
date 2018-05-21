package com.example.pratyushsingh.janelaaj;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    
    FragmentForm mFragmentForm;
    FragmentDatabase mFragmentDatabase;
    FragmentDatabase2 mFragmentDatabase2;

    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProgressDialog = ProgressDialog.show(MainActivity.this,"Please wait","Loading..",true,false);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://janelaaj.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                APIInterface apiInterface = retrofit.create(APIInterface.class);
                Call<Response2> call = apiInterface.getresponse2();
                call.enqueue(new Callback<Response2>() {
                    @Override
                    public void onResponse(Call<Response2> call, Response<Response2> response) {

                        mProgressDialog.dismiss();

                        Response2 r = response.body();

                        if(response.body() != null && r.getStatus().equals("SUCCESS")){

                            mFragmentDatabase2.setVAl(r.getDevtime(),r.getMantime(),r.getSaletime());

                            LocalDATA localDATA = new LocalDATA(MainActivity.this);
                            SQLiteDatabase database = localDATA.getWritableDatabase();

                            database.delete(LocalDATA.QUESTIONEER_TABLENAME,null,null);
                            ContentValues cv = new ContentValues();

                            for(int i=0;i<r.data.size();i++){
                                cv.put(LocalDATA.QUESTIONEER_AGE,r.data.get(i).getAge());
                                cv.put(LocalDATA.QUESTIONEER_DID,r.data.get(i).getDid());
                                cv.put(LocalDATA.QUESTIONEER_FIRSTNAME,r.data.get(i).getFname());
                                cv.put(LocalDATA.QUESTIONEER_IDID,r.data.get(i).getId());
                                cv.put(LocalDATA.QUESTIONEER_LASTNAME,r.data.get(i).getLname());
                                database.insert(LocalDATA.QUESTIONEER_TABLENAME,null,cv);
                            }

                            mFragmentDatabase.populate();

                        }else{
                            Toast.makeText(MainActivity.this,"Something went wrong. Please try again later.",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<Response2> call, Throwable t) {
                        mProgressDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Something went wrong. Please try again later.",Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            mProgressDialog = ProgressDialog.show(MainActivity.this,"Please wait","Loading..",true,false);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://janelaaj.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            APIInterface apiInterface = retrofit.create(APIInterface.class);
            Call<Response1> call = apiInterface.getdeldone();
            call.enqueue(new Callback<Response1>() {
                @Override
                public void onResponse(Call<Response1> call, Response<Response1> response) {
                    mProgressDialog.dismiss();
                    Response1 r = response.body();
                    if(response.body() != null && r.getStatus().equals("SUCCESS")){
                        LocalDATA localDATA = new LocalDATA(MainActivity.this);
                        SQLiteDatabase database = localDATA.getWritableDatabase();

                        database.delete(LocalDATA.QUESTIONEER_TABLENAME,null,null);
                        try{
                            mFragmentDatabase.populate();
                        }catch (Exception e){

                        }

                    }else{
                        Toast.makeText(MainActivity.this,"Something went wrong. Please try again later.",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Response1> call, Throwable t) {
                    mProgressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Something went wrong. Please try again later.",Toast.LENGTH_SHORT).show();

                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 0){
                FragmentForm f = new FragmentForm();
                mFragmentForm = f;
                return f;
            }else if(position ==1){
                FragmentDatabase d = new FragmentDatabase();
                mFragmentDatabase = d;
                return d;
            }else{
                FragmentDatabase2 f2 = new FragmentDatabase2();
                mFragmentDatabase2 = f2;
                return f2;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }


    }
}
