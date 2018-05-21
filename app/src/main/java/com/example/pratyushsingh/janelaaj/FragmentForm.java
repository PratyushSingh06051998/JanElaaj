package com.example.pratyushsingh.janelaaj;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pratyushsingh on 20/05/18.
 */

public class FragmentForm extends Fragment{

    String Firstname;
    String Lastname;
    int age;
    String Deptid;

    EditText FN;
    EditText LN;
    EditText A;
    EditText DID;
    RelativeLayout SubmitButton;
    ProgressDialog mProgressDialog;
    LinearLayout warn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_form,container,false);

        FN = (EditText) v.findViewById(R.id.set_firstnametext);
        LN = (EditText) v.findViewById(R.id.set_lastnametext);
        A = (EditText) v.findViewById(R.id.set_agetext);
        DID = (EditText) v.findViewById(R.id.set_depttext);
        SubmitButton = (RelativeLayout) v.findViewById(R.id.submitt);
        warn = (LinearLayout) v.findViewById(R.id.warn);

        warn.setAlpha(0.0f);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Firstname = FN.getText().toString();
                Lastname = LN.getText().toString();
                Deptid = DID.getText().toString();

                if((Deptid.equals("123") ||Deptid.equals("234") ) || Deptid.equals("345") ){
                    warn.setAlpha(0.0f);

                    if(A.getText().toString().equals("") || A.getText().toString() == null){
                        Toast.makeText(getActivity(),"Please enter your age",Toast.LENGTH_SHORT).show();
                    }else{
                        age = Integer.parseInt(A.getText().toString());
                        if(Firstname.equals("") || Firstname == null){
                            Toast.makeText(getActivity(),"Please enter your Firstname",Toast.LENGTH_SHORT).show();
                        }else{
                            if(Lastname.equals("") || Lastname == null){
                                Toast.makeText(getActivity(),"Please enter your Lastname",Toast.LENGTH_SHORT).show();
                            }else{
                                mProgressDialog = ProgressDialog.show(getActivity(),"Please wait","Loading..",true,false);
                                FormSendStructure ff = new FormSendStructure(Firstname,Lastname,age,Deptid);

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("https://janelaaj.herokuapp.com/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                APIInterface apiInterface = retrofit.create(APIInterface.class);
                                Call<Response1> call = apiInterface.getresponse1(ff);
                                call.enqueue(new Callback<Response1>() {
                                    @Override
                                    public void onResponse(Call<Response1> call, Response<Response1> response) {
                                        mProgressDialog.dismiss();

                                        if(response.body() != null){

                                            Response1 r = response.body();
                                            if(r.getStatus().equals("SUCCESS")){
                                                Toast.makeText(getActivity(),"SUCCESS",Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(getActivity(),"Something went wrong. Please try again later.",Toast.LENGTH_SHORT).show();
                                            }

                                        }else{
                                            Toast.makeText(getActivity(),"Something went wrong. Please try again later.",Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<Response1> call, Throwable t) {
                                        mProgressDialog.dismiss();

                                        Toast.makeText(getActivity(),"Something went wrong. Please try again later.",Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        }
                    }


                }else{
                    warn.setAlpha(1.0f);
                }



            }
        });

        return v;
    }
}
