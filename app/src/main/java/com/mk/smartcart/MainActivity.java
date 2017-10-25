package com.mk.smartcart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.DialogPreference;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText UserId, Password, coupon;
    Button delbtn, totbtn;
    //ListView lv,listView;//,lvPrice,lvName;
    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;
    ListView lvDrawer;
    List<String> fruits_list, names_list, drawerItems;
    List<Integer> price_list;
    ArrayAdapter<String> drawerButtonAdapter, arrayAdapter;//,nameAdapter;
    Context context;

    //ArrayAdapter<Integer> priceAdapter;
    //  TextView txtTotal,txtBalance;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // delbtn = (Button)findViewById(R.id.btnDel);
        //  drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        context = MainActivity.this;


    }

    String code;

    public void onPay(View view) {
        coupon = (EditText) findViewById(R.id.code);
        pric = 0;
        Iterator<Integer> it;
        it = price_list.iterator();
        while (it.hasNext())
            pric += it.next();
        final Integer x = new Integer(pric);
        // String balance;
        code = coupon.getText().toString();
        if(code.equals(""))
            code="000";
        coupon.setText("");
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        //----------------------------------------------------
        if (x > 0) {
            new AsyncTask<String, Void, String>() {
                String balance;
                ProgressDialog dialog;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Nullable
                @Override
                protected String doInBackground(String... params) {
                    try {
                        String database_url = "https://anshpurii.000webhostapp.com/pay2.php";
                        URL url = new URL(database_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("CustID", "UTF-8") + "=" + URLEncoder.encode(usernID, "UTF-8") + "&"
                                + URLEncoder.encode("total", "UTF-8") + "=" + URLEncoder.encode(x.toString(), "UTF-8") + "&"
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

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                protected void onPostExecute(String res) {

                    if (res.contains("Insufficient balance")) {

                        balance = res.substring(res.indexOf("%") + 1);
                        alertDialogBuilder.setTitle("Insufficient Balance!");
                        alertDialogBuilder.setMessage("Balance = " + balance);
                        alertDialogBuilder.setNegativeButton("Shop More!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }

                    if (res.contains("balance updated")) {
                        balance = res.substring(res.indexOf("%") + 1);
                        alertDialogBuilder.setTitle("Paid and discount applied!");
                        alertDialogBuilder.setMessage("Balance = " + balance);
                        alertDialogBuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }


                    if (res.contains("balance error")) {
                        alertDialogBuilder.setTitle("Coupon code error.");
                        alertDialogBuilder.setMessage("Balance= " + balance);
                        alertDialogBuilder.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                    price_list.clear();
                    list.clear();
                    adapter.notifyDataSetChanged();

                    ArrayAdapter ad = new ArrayAdapter<String>(context,
                            R.layout.lv, mobileArray);
                    ListView lv = (ListView) findViewById(R.id.totlv);
                    lv.setAdapter(ad);

                    pric = 0;
                    Iterator<Integer> it = price_list.iterator();
                    while (it.hasNext())
                        pric += it.next();
                    Integer x = new Integer(pric);
                    mobileArray[0] = ("Total =" + x.toString());
                }

            }.execute();


        } else {
            alertDialogBuilder.setTitle("Scan an Item first!");
            alertDialogBuilder.setNegativeButton("Got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }

    public void onLogout(View v) {
        MainActivity.this.setContentView(R.layout.activity_main);
    }

    public void onScan(View view) {


        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scan");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();


    }

     String usernID;
    public View navHeader;

    public TextView navn;
    String[] mobileArray = {"Total "};

    public void OnLogin(View view) {
        UserId = (EditText) findViewById(R.id.txtUserID);
        Password = (EditText) findViewById(R.id.txtPassword);
        totbtn = (Button) findViewById(R.id.button3);
        usernID = UserId.getText().toString();
        final String pwd = Password.getText().toString();
        // navigationView = (NavigationView) findViewById(R.id.nav_view);
        // navHeader = navigationView.getHeaderView(2);
        //navn = (TextView) findViewById(R.id.navname);

        final Context context = this;
        final Handler handler = new Handler();

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://anshpurii.000webhostapp.com/login.php";
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(usernID, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");
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

            ProgressDialog dialog;

            protected void onPreExecute() {
                super.onPreExecute();

                dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("Hi Smart shopper");
                dialog.setMessage("Please wait!");
                dialog.show();
            }

            public void onPostExecute(String result) {
                super.onPostExecute(result);
                dialog.dismiss();
                if (result.contains("login successful")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show();
                            MainActivity.this.setContentView(R.layout.cart);


                            list = new ArrayList<>();
                            //  list.add(new MyDataModel("abc","533"));

                            adapter = new MyArrayAdapter(context, list);


                            listView = (ListView) findViewById(R.id.listView1);
                            listView.setAdapter(adapter);
                            price_list = new ArrayList<Integer>();


                            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            navigationView = (NavigationView) findViewById(R.id.nav_view);

                            setUpNavigationView();

//                            pric=0;
//                            Iterator<Integer> it;
//                            it=price_list.iterator();
//                            while (it.hasNext())
//                                pric+=it.next();
//                            Integer x = new Integer(pric);
//                            totbtn.setText("Total = "+x.toString());
                            //navn.setText(usernID);

                            ArrayAdapter ad = new ArrayAdapter<String>(context,
                                    R.layout.lv, mobileArray);
                            ListView lv = (ListView) findViewById(R.id.totlv);
                            lv.setAdapter(ad);

                            pric = 0;
                            Iterator<Integer> it = price_list.iterator();
                            while (it.hasNext())
                                pric += it.next();
                            Integer x = new Integer(pric);
                            mobileArray[0] = ("Total =" + x.toString());


                            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int ig, long l) {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                                    alertDialogBuilder.setTitle("Confirm Delete");
                                    alertDialogBuilder.setMessage("Are you sure you want to delete? ");
                                    alertDialogBuilder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            indexfordel = list.get(ig);
                                            adapter.remove(indexfordel);
                                            adapter.notifyDataSetChanged();
                                            String type = "delete";
                                            final HelperClass hc = new HelperClass(context);
                                            hc.execute(type, indexfordel.getId());

                                            price_list.remove(ig);


                                            ArrayAdapter ad = new ArrayAdapter<String>(context,
                                                    R.layout.lv, mobileArray);
                                            ListView lv = (ListView) findViewById(R.id.totlv);
                                            lv.setAdapter(ad);

                                            pric = 0;
                                            Iterator<Integer> it = price_list.iterator();
                                            while (it.hasNext())
                                                pric += it.next();
                                            Integer x = new Integer(pric);
                                            mobileArray[0] = ("Total =" + x.toString());
                                            //    final Context context = this;
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (hc.isDeleted) {
                                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                                        alertDialogBuilder.setMessage("Deleted item!");
                                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                                        //alertDialog.show();
                                                    }
                                                }
                                            }, 1000);
                                        }
                                    });
                                    alertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                    return false;
                                }
                            });
                            //  new GetDataTask().execute();
                           /* lv = (ListView)findViewById(R.id.listItems);

                            // lvName = (ListView)findViewById(R.id.lvName);
                            // lvPrice= (ListView)findViewById(R.id.lvPrice);
                            fruits_list = new ArrayList<String>();
                            price_list = new ArrayList<Integer>();
                            names_list = new ArrayList<String>();
                            drawerItems = new ArrayList<String>();
                            lvDrawer = (ListView)findViewById(R.id.left_drawer);

                            arrayAdapter = new ArrayAdapter<String>
                                    (context, android.R.layout.simple_list_item_single_choice, fruits_list);
                            drawerButtonAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,drawerItems);
                            drawerItems.add("Welcome,"+usernID+"!");
                            lvDrawer.setAdapter(drawerButtonAdapter);
                            drawerButtonAdapter.notifyDataSetChanged();*/
                            // priceAdapter = new ArrayAdapter<Integer>
                            //(context, android.R.layout.simple_list_item_1, price_list);
                            // nameAdapter =  new ArrayAdapter<String>
                            //(context, android.R.layout.simple_list_item_1, names_list);


                        }
                    });
                } else {
                    Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
        //-----------------------------------------------

    }


//    public void onTotal(View view)
//    {
//
//        pric=0;
//        Iterator<Integer> it;
//        it=price_list.iterator();
//        while (it.hasNext())
//            pric+=it.next();
//        Integer x = new Integer(pric);
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//      //  totbtn.setText("Total = " + x.toString());
//        alertDialogBuilder.setMessage("Total = "+x.toString());
//        alertDialogBuilder.setNegativeButton("Shop More!", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }

    IntentResult result;
    public int pric;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            } else {
/*
                final HelperClass hc = new HelperClass(this);
                hc.execute("database",usernID,result.getContents());
                final Context context = this;
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      //  fruits_list.add(result.getContents()+" | "+hc.name+" | "+hc.p);
                       // price_list.add(hc.p);
                      //  names_list.add(hc.name);

                        //lv.setAdapter(arrayAdapter);
                      //  lvName.setAdapter(nameAdapter);
                      //  lvPrice.setAdapter(priceAdapter);
                       // arrayAdapter.notifyDataSetChanged();
                      //  priceAdapter.notifyDataSetChanged();
                      //  nameAdapter.notifyDataSetChanged();


                        //   Toast.makeText(context,x.toString(),Toast.LENGTH_SHORT).show();

                        //txtTotal.setText(pric.toString());

                    }


                }, 3000);

*/
//                final Handler handler1 = new Handler();
//                handler1.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                ArrayAdapter ad = new ArrayAdapter<String>(context,
//                        R.layout.lv, mobileArray);
//                ListView lv = (ListView) findViewById(R.id.totlv);
//                lv.setAdapter(ad);
//
//                pric=0;
//                Iterator<Integer> it=price_list.iterator();
//                while (it.hasNext())
//                    pric += it.next();
//                Integer x = new Integer(pric);
//                mobileArray[0]=("Total ="+x.toString());
//
//                    }
//                }, 3000);

                new AsyncTask<Void, Void, Void>() {

                    ProgressDialog dialog;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();

                    }

                    @Nullable
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            String database_url = "https://anshpurii.000webhostapp.com/cartPopulate.php";
                            URL url = new URL(database_url);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                            String post_data = URLEncoder.encode("user_ID", "UTF-8") + "=" + URLEncoder.encode(usernID, "UTF-8") + "&"
                                    + URLEncoder.encode("itemID", "UTF-8") + "=" + URLEncoder.encode(result.getContents().toString(), "UTF-8");
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

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    protected void onPostExecute(Void aVoid) {
                        final Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ArrayAdapter ad = new ArrayAdapter<String>(context,
                                        R.layout.lv, mobileArray);
                                ListView lv = (ListView) findViewById(R.id.totlv);
                                lv.setAdapter(ad);

                                pric = 0;
                                Iterator<Integer> it = price_list.iterator();
                                while (it.hasNext())
                                    pric += it.next();
                                Integer x = new Integer(pric);
                                mobileArray[0] = ("Total =" + x.toString());

                            }
                        }, 3000);


                    }

                }.execute();
                new GetDataTask().execute();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    // String indexfordelete;
    MyDataModel indexfordel;


//    public void onDelete(View view)
//    {
//        //  SparseBooleanArray array = listView.getCheckedItemPositions();
//        Toast.makeText(context,"You may select the items!",Toast.LENGTH_SHORT).show();
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int ig, long l) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//                alertDialogBuilder.setTitle("Delete?");
//                alertDialogBuilder.setMessage("Are you sure you want to delete? ");
//                alertDialogBuilder.setNegativeButton("Yes!", new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        indexfordel=list.get(ig);
//                        adapter.remove(indexfordel);
//                        adapter.notifyDataSetChanged();
//                        String type = "delete";
//                        final HelperClass hc = new HelperClass(context);
//                        hc.execute(type,indexfordel.getId());
//
//                        price_list.remove(ig);
//                        //    final Context context = this;
//                        final Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(hc.isDeleted) {
//                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//                                    alertDialogBuilder.setMessage("Deleted item!");
//                                    AlertDialog alertDialog = alertDialogBuilder.create();
//                                    alertDialog.show();
//                                }
//                            }
//                        }, 3000);
//                    }
//                });
//                alertDialogBuilder.setPositiveButton("No!", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//                return false;
//            }
//        });
//
//
//       /* int count = listView.getCount();
//        for(int item=count-1;item>=0;item--)
//        {
//            if(array.get(item))
//            {
//
//                indexfordel=list.get(item);
//                 delIndex = indexfordel.getId();
//                adapter.remove(indexfordel);
//                //arrayAdapter.remove(fruits_list.get(item));
//                //price_list.remove(price_list.get(item));
//              //  priceAdapter.remove(price_list.get(item));
//               // nameAdapter.remove(names_list.get(item));
//            }
//        }
//        array.clear();
//        adapter.notifyDataSetChanged();
//      //  arrayAdapter.notifyDataSetChanged();
//      //  priceAdapter.notifyDataSetChanged();
//       // nameAdapter.notifyDataSetChanged();
//*/
//
//
//    }


    public void onRegMain(View view) {
        Intent intent = new Intent(this, registeractivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in, R.anim.out);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Hi Smart shopper");
            dialog.setMessage("Please wait!");
            dialog.show();


        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = JSONParser.getDataFromWeb(result.getContents().toString());
            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray(Keys.ans);
                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String name = innerObject.getString(Keys.item_name);
                                String price = innerObject.getString(Keys.item_price);
                                String image = innerObject.getString(Keys.image);

                                price_list.add(Integer.parseInt(price));
                                MyDataModel model = new MyDataModel("a", "3");
                                model.setName(name);
                                model.setEmail(price);
                                model.setImage(image);
                                model.setId(result.getContents().toString());
                                list.add(model);
                            }
                        }
                    }
                }
            } catch (JSONException je) {
                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if (list.size() > 0) {
                adapter.notifyDataSetChanged();
            } else {
                // Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    NavigationView navigationView;
    DrawerLayout drawer;

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;

                    case R.id.nav_his:
                        // launch new intent instead of loading fragment

                        Intent myIntent =new Intent(MainActivity.this, History.class);
                        myIntent.putExtra("User",usernID);
                                //startActivity(new Intent(MainActivity.this, History.class));
                        MainActivity.this.startActivity(myIntent);
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_prof:
                       // startActivity(new Intent(MainActivity.this,Profile.class));
                        Intent myIntent1 =new Intent(MainActivity.this, Profile.class);
                        myIntent1.putExtra("User",usernID);
                        //startActivity(new Intent(MainActivity.this, History.class));
                        MainActivity.this.startActivity(myIntent1);
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_deal:
                        startActivity(new Intent(MainActivity.this,Sale.class));
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_logout:
                        MainActivity.this.setContentView(R.layout.activity_main);
//                    case R.id.nav_privacy_policy:
//                        // launch new intent instead of loading fragment
//                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
//                        drawer.closeDrawers();
//                        return true;
//                    default:
//                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                // loadHomeFragment();

                return true;
            }
        });


    }
}

  /*  private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        //String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
           MyDataModel m=new MyDataModel();
            String name=obj.getString(Keys.iname1);
            String price=obj.getString(Keys.iprice1);
            m.setName(name);
            m.setPrice(price);
            list.add(m);

        }*/

// ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, heroes);
//listView.setAdapter(arrayAdapter);



