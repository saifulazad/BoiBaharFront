package com.androidbegin.BoiBahar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;

    public static int banglakey;

    Intent intent;
    private static String TAG = MainActivity.class.getSimpleName();
    AsyncHttpClient client = new AsyncHttpClient();

    ArrayList<Book> arraylist = new ArrayList<Book>();
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview_main);
        String url = "http://saifulazad.pythonanywhere.com/books";
        // get action bar
        ActionBar actionBar = getActionBar();
        actionBar.show();

        //get process bar

        final ProgressDialog pDialog = new ProgressDialog(this);

        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,JSONObject response) {
                // called when response HTTP status is "200 OK"
                try {
                    JSONArray ob = response.getJSONArray("books");
                    Type listType = new TypeToken<ArrayList<Book>>() {
                    }.getType();

                    List<Book> books = new Gson().fromJson(ob.toString(), listType);
                    arraylist.addAll(books);
                    for (int i =0 ;i< arraylist.size();i++)
                    {
                        adapter.addBook(arraylist.get(i));
                        Log.d("AUTHOR", arraylist.get(i).getPublication_name());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    pDialog.hide();
                    pDialog.dismiss();
                }

            }


            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

        pDialog.setMessage("Loading...");
        pDialog.show();




        Typeface tf; //bangla
        tf = Typeface.createFromAsset(this.getAssets(),
                "solaimanlipinormal.ttf");





        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        Log.d("LIST", String.valueOf(arraylist.size()));
        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);


        banglakey = banglasupport();


        // Binds the Adapter to the ListView
        list.setAdapter(adapter);


        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                Log.d("afterTextChanged()", text);
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
                Log.d("afterTextChanged()", arg0.toString());
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
                Log.d("onTextChanged()", arg0.toString());

            }
        });
    }


    public int banglasupport() {
        int i;
        Locale a[] = Locale.getAvailableLocales();

        if (Arrays.toString(a).contains("bn") || (Build.MANUFACTURER.equals("samsung")
                && Build.VERSION.SDK_INT < 10)) {
            i = 1;    //bangla supported
        } else {
            i = 0;  //bangla unsupported
        }
        return i;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.about1:
                // app icon in action bar clicked; go home
                intent = new Intent(this, About.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}