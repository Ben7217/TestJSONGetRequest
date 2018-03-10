package com.example.android.jsontest;

import android.nfc.Tag;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by BenMorrisRains on 3/9/18.
 */

public class Post {

    @SerializedName("id")
    long ID;

    @SerializedName("date")
    Date dateCreated;

    String name;
    String location;
    String title;
    String author;
    String url;
    String body;

}
