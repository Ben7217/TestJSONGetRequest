package com.example.android.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    TextView textView;
    private static final String ENDPOINT = "https://api.myjson.com/bins/qe605";
    private Gson gson;
    private RequestQueue requestQueue;
    private String buildThatString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        requestQueue = Volley.newRequestQueue(this);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        textView = findViewById(R.id.displayJSON);
        fetchPosts();


    }


    private void fetchPosts() {
        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT, onPostsLoaded, onPostsError);

        requestQueue.add(request);
    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            List<Post> posts = Arrays.asList(gson.fromJson(response, Post[].class));
            Log.i("PostActivity", posts.size() + " posts loaded.");
            for (Post post : posts) {
                Log.i("Gigs ", "Event Name: " + post.name + ", Event Location: " + post.location + ", Date: "
                        + post.dateCreated);
                buildThatString += "\nEvent Name: " + post.name + ", \n Event Location: " + post.location + ",\n Date: " +
                        post.dateCreated + "\n";
            }
            textView.setText(buildThatString);
        }
    };

    private final Response.ErrorListener onPostsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("PostActivity", error.toString());

            textView.setText("ERROR!");
        }
    };
}