package com.androidbegin.BoiBahar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.squareup.picasso.Picasso;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Book> books = null;
    private ArrayList<Book> arraylist= new ArrayList<Book>();

    public ListViewAdapter(Context context, ArrayList<Book> books) {
        mContext = context;
        this.books = books;
        inflater = LayoutInflater.from(mContext);

        Log.d("Author", String.valueOf(arraylist.size()));

    }
    public void  addBook(Book book)
    {
        arraylist.add(book);
    }
    public class ViewHolder {
        TextView book_name;
        TextView author_name;
        TextView publication_name;
        ImageView imageview;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Book getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml

            holder.book_name = (TextView) view.findViewById(R.id.book);
            holder.author_name= (TextView) view.findViewById(R.id.writer);
            holder.publication_name = (TextView) view.findViewById(R.id.publication);
            // Locate the ImageView in listview_item.xml
            holder.imageview = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.book_name.setText(books.get(position).getBook_name());
        holder.author_name.setText(books.get(position).getAuthor_name());
        holder.publication_name.setText(books.get(position).getPublication_name());

        // Set the results into ImageView
        Picasso.with(mContext)
                .load(books.get(position).getBook_img_url())
                .resize(50, 50)
                .centerCrop()
                .into(holder.imageview);
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);

                // Pass book object

                intent.putExtra("book", books.get(position));

                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        books.clear();
        Log.d("filterChanged()",String.valueOf(charText.length()));
        if (charText.length() == 0) {
            books.addAll(arraylist);
        }

        else {


            for (Book book : arraylist) {



                if (book.getAuthor_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    books.add(book);
                }
            }
        }
        notifyDataSetChanged();
    }

}
