package com.androidbegin.BoiBahar.builtin;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbegin.BoiBahar.About;
import com.androidbegin.BoiBahar.Book;
import com.androidbegin.BoiBahar.MainActivity;
import com.androidbegin.BoiBahar.R;
import com.squareup.picasso.Picasso;

public class SingleItemView extends Activity {


    // Declare Variables
    TextView book_name;
    TextView publication_name;
    TextView author_name;
    TextView stall_place;
    TextView stall_no;
    ImageView imageView;
    Context context;

    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);


        // get action bar
        ActionBar actionBar = getActionBar();

        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.show();


        Intent intent = getIntent();

        // Get the book object passed from ListViedAdepter

        Book book = (Book) intent.getExtras().getSerializable("book");


        // Locate the TextViews in singleitemview.xml
        book_name = (TextView) findViewById(R.id.book);
        author_name = (TextView) findViewById(R.id.writer);
        publication_name = (TextView) findViewById(R.id.publication);
        stall_place = (TextView) findViewById(R.id.place);
        stall_no = (TextView) findViewById(R.id.stall);

        // Locate the ImageView in singleitemview.xml
        imageView = (ImageView) findViewById(R.id.image);

        // Load the results into the TextViews
        book_name.setText(book.getBook_name());
        author_name.setText(book.getAuthor_name());
        publication_name.setText(book.getPublication_name());
        stall_place.setText("স্থানঃ" + book.getStall_place());
        stall_no.setText("স্টল নং:" + book.getStall_no());

        // Load the image into the ImageView
        Picasso.with(context)
                .load(book.getBook_img_url())
                .resize(50, 50)
                .centerCrop()
                .into(imageView);
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
            case android.R.id.home:
                // app icon in action bar clicked; go home
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;


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