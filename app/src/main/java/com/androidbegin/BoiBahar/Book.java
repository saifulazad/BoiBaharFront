package com.androidbegin.BoiBahar;


import java.io.Serializable;

/**
 * Created by azad on 7/12/15.
 */
public class Book implements  Serializable{

    private String book_name ;
    private String author_name ;
    private String publication_name;
    private String book_img_url;

    private String stall_no ;
    private String stall_place;

    public  Book( String book_name , String author_name , String publication_name)
    {
        this.author_name = author_name;
        this.book_name = book_name;
        this.publication_name = publication_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getPublication_name() {
        return publication_name;
    }

    public String getStall_no() {
        return stall_no;
    }

    public String getStall_place() {
        return stall_place;
    }

    public String getBook_img_url() {
        return book_img_url;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setPublication_name(String publication_name) {
        this.publication_name = publication_name;
    }

    public void setStall_no(String stall_no) {
        this.stall_no = stall_no;
    }

    public void setStall_place(String stall_place) {
        this.stall_place = stall_place;
    }

    public void setBook_img_url(String book_img_url) {
        this.book_img_url = book_img_url;
    }
}
