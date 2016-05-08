package com.androidbegin.BoiBahar.consumer;

import android.util.Log;

import com.androidbegin.BoiBahar.Book;
import com.androidbegin.BoiBahar.ListViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Azad on 08-May-16.
 */
public class FeatchAsyn extends JsonHttpResponseHandler {

    private ListViewAdapter adapter;
    private ArrayList<Book> arraylist = new ArrayList<Book>();

    public FeatchAsyn(ListViewAdapter adapter){
        this.adapter =adapter;
    }

    @Override
    public void onStart() {
        if (adapter == null){

        }
        // called before request is started
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        // called when response HTTP status is "200 OK"
        try {
            JSONArray ob = response.getJSONArray("books");
            Type listType = new TypeToken<ArrayList<Book>>() {
            }.getType();

            List<Book> books = new Gson().fromJson(ob.toString(), listType);
            Log.d("SSSS", String.valueOf(books.size()));

            if (adapter == null){
                Log.d("NNNNN","NULL");
            }
            arraylist.addAll(books);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Book> getArraylist() {
        return arraylist;
    }
}
