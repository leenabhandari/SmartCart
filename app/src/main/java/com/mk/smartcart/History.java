package com.mk.smartcart;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


public class History extends AppCompatActivity {
    List<String> list;
    ArrayAdapter<String> stringArrayAdapter;
    ListView lv;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final String username;
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
                        String database_url = "https://anshpurii.000webhostapp.com/hist1.php";
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
                    lv = (ListView) findViewById(R.id.lvHist);
                    lv.setAdapter(stringArrayAdapter);

                    String price="";

                    int i=res.indexOf(';')+2;
                    while(res.charAt(i)!='+')
                    {

                        while(res.charAt(i)!='/')
                        {
                            price += res.charAt(i++);
                        }


                        list.add(price);
                        price="";
                        i++;
                    }

                    lv.setAdapter(stringArrayAdapter);
                    stringArrayAdapter.notifyDataSetChanged();
                }

            }.execute();
        }
    }
}
