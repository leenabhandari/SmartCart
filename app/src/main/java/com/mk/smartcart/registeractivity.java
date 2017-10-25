package com.mk.smartcart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registeractivity extends AppCompatActivity {

    EditText etName, etPass, etBal, etPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        etName= (EditText)findViewById(R.id.txtusern);
        etPass = (EditText)findViewById(R.id.txtpass);
        etBal = (EditText)findViewById(R.id.txtbal);
        etPhone = (EditText)findViewById(R.id.txtPhone);

    }


    String name;
    public void onRegister(View view)
    {
        name = etName.getText().toString();
        String pass = etPass.getText().toString();
        String bal = etBal.getText().toString();
        String phone = etPhone.getText().toString();
       final HelperClass hc = new HelperClass(this);
        hc.execute("register",name,pass,bal,phone);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 1000);



    }
Context context123=this;
//    public registeractivity(Context context123)
//    {
//        this.context123=context123;
//    }

    public void callmethod(Context context123)
    {

        ((Activity)context123).finish();
    }
}
