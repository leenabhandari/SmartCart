package com.mk.smartcart;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    final Context context = this;
    List<String> list;
    ArrayAdapter<String> stringArrayAdapter;
    ListView lv;
     String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Button upbalbtn=(Button)findViewById(R.id.upbtn);
        upd = (EditText) findViewById(R.id.upbal);
        upbalbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                bal = upd.getText().toString();
                if (bal.equals(""))
                    bal = "0";
                upd.setText("");

             //   final HelperClass hc1 = new HelperClass(context);
              //  hc1.execute("balance update",username,bal);

                new AsyncTask<Void, Void, String>() {
                    String balance;
                    ProgressDialog dialog;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
//                        dialog = new ProgressDialog(context);
//                        dialog.setTitle("Hi Smart shopper");
//                        dialog.setMessage("Please wait!");
//                        dialog.show();
                    }

                    @Nullable
                    @Override
                    protected String doInBackground(Void... params) {
                        try {
                            String database_url = "https://anshpurii.000webhostapp.com/balup.php";
                            URL url = new URL(database_url);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                            String post_data =URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                                    + URLEncoder.encode("bal", "UTF-8") + "=" + URLEncoder.encode(bal, "UTF-8");
                            bufferedWriter.write(post_data);
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            outputStream.close();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                            String result = "";
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                result += line;
                            }
                            bufferedReader.close();
                            inputStream.close();
                            httpURLConnection.disconnect();
                            return result;

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    protected void onPostExecute(String res) {
                        Toast.makeText(context,"Balance Updated",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }.execute();

               // Toast.makeText(context,"Balance Updating",Toast.LENGTH_SHORT).show();
            }


        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                username = null;
            } else {
                username = extras.getString("User");
            }
            new AsyncTask<Void, Void, String>() {
                String balance;
                ProgressDialog dialog;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    dialog = new ProgressDialog(context);
                    dialog.setTitle("Hi Smart shopper");
                    dialog.setMessage("Please wait!");
                    dialog.show();
                }

                @Nullable
                @Override
                protected String doInBackground(Void... params) {
                    try {
                        String database_url = "https://anshpurii.000webhostapp.com/profile.php";
                        URL url = new URL(database_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("CustID", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                        bufferedWriter.write(post_data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        String result = "";
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            result += line;
                        }
                        bufferedReader.close();
                        inputStream.close();
                        httpURLConnection.disconnect();
                        return result;

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }


                protected void onPostExecute(String res) {
                    super.onPostExecute(res);
                    dialog.dismiss();
                    list = new ArrayList<>();
                    stringArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list);
                    lv = (ListView) findViewById(R.id.lvProfile);


                    String phone, name, pwd, bal;
                    phone = res.substring(res.indexOf("Phone:"), res.indexOf("Name:"));
                    pwd = res.substring(res.indexOf("Password:"), res.indexOf("Balance:"));
                    bal = res.substring(res.indexOf("Balance:"));
                    name = res.substring(res.indexOf("Name:"), res.indexOf("Password:"));
                    list.add(phone);
                    list.add(name);
                    list.add(pwd);
                    list.add(bal);
                    lv.setAdapter(stringArrayAdapter);
                    stringArrayAdapter.notifyDataSetChanged();
                }

            }.execute();
        }
    }

    EditText upd;
    String bal;

//    public void upbalance() {
//        upd = (EditText) findViewById(R.id.upbal);
//        bal = upd.getText().toString();
//        if (bal.equals(""))
//            bal = "0";
//        upd.setText("");
//        final HelperClass hc1 = new HelperClass(this);
//       hc1.execute("balance update",username,bal);
//
//
//
//        Toast.makeText(context,"Balance Updating",Toast.LENGTH_SHORT).show();
//
//    }
}
