package com.mk.smartcart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

/**
 * Created by ansh on 8/16/2017.
 */

public class HelperClass extends AsyncTask<String,Void,String>
{

    Context context;
    String type;
    HelperClass (Context ctx) {
        context = ctx;
        flag=false;
    }
    public boolean flag,dbFlag;

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url = "https://anshpurii.000webhostapp.com/login.php";




        if(type.equals("database")) {
            try {
                String user_ID = params[1];
                String itemID = params[2];
                String database_url = "https://anshpurii.000webhostapp.com/cartPopulate.php";
                URL url = new URL(database_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_ID", "UTF-8") + "=" + URLEncoder.encode(user_ID, "UTF-8") + "&"
                        + URLEncoder.encode("itemID", "UTF-8") + "=" + URLEncoder.encode(itemID, "UTF-8");
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
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(type.equals("delete")) {
            try {
                String indexfordelete = params[1];
                String database_url = "https://anshpurii.000webhostapp.com/delete_element.php";
                URL url = new URL(database_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("index", "UTF-8") + "=" + URLEncoder.encode(indexfordelete, "UTF-8") ;
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
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(type.equals("pay")) {
            try {
                String CustID = params[1];
                String code= params[2];
                String total=params[3];
                String database_url = "https://anshpurii.000webhostapp.com/pay1.php";
                URL url = new URL(database_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("CustID", "UTF-8") + "=" + URLEncoder.encode(CustID, "UTF-8") + "&"
                        + URLEncoder.encode("total", "UTF-8") + "=" + URLEncoder.encode(total, "UTF-8") + "&"
                        + URLEncoder.encode("code", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8");
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
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(type.equals("register")) {
            try {
                String name = params[1];
                name1 = name;
                String pass = params[2];
                String bal = params[3];
                String phone = params[4];
                String database_url = "https://anshpurii.000webhostapp.com/register.php";
                URL url = new URL(database_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&"
                        + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&"
                        + URLEncoder.encode("bal", "UTF-8") + "=" + URLEncoder.encode(bal, "UTF-8") ;
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
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    String name1;

    @Override
    protected void onPreExecute() {
super.onPreExecute();
    }

    public String name,balance;
    public String price;
    public Integer p;
    public boolean isDeleted,balanceflag,cflag=true;
    @Override
    protected void onPostExecute(String result) {

        if(result.contains("item added"))
        {
            Toast.makeText(context,"Item Added!",Toast.LENGTH_SHORT).show();
            name=result.substring(result.lastIndexOf("/")+1);
            price=result.substring(result.lastIndexOf(":")+1,result.indexOf("/")).trim();
            p= Integer.parseInt(price);
        }
        if(result.contains("deleted"))
        {
            isDeleted=true;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setMessage("Deleted item!");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        if(result.contains("Insufficient balance"))
        {
            balanceflag=false;
            balance=result.substring(result.indexOf("%")+1);
        }
        if(result.contains("balance error"))
        {
            cflag=false;
            balance=result.substring(result.indexOf("%")+1);
        }
        if(result.contains("balance updated"))
        {
            balanceflag=true;
            balance=result.substring(result.indexOf("%")+1);
        }

        if(result.contains("customer added")&&type.equals("register"))
        {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setMessage("Welcome to SmartCart, "+name1+"!");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    registeractivity rs=new registeractivity();
                    rs.callmethod(context);

                }
            }, 2000);

        }
        else if(result.contains("customer added")&&!type.equals("register"))
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setMessage("Failed");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}