package com.example.akashn.appforzailet;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayList<Info_for_posts> list=new ArrayList<>();
        setContentView(R.layout.activity_main2);
        recyclerView= (RecyclerView) findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final Main2Adapter main2Adapter=new Main2Adapter(getApplicationContext());
        recyclerView.setAdapter(main2Adapter);



        final StringRequest request2 = new StringRequest(Request.Method.GET,"http://zailet.com/zailet_internship_assignment/get_data.php?topics_info=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // String o_id="";

                Log.d("print",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String title = "";
                    String time="";
                    String cover="";
                    String description="";
                    String author_name="";
                    String author_dp="";



                    JSONArray posts = jsonObject.getJSONArray("posts");




                    for (int i = 0; i < posts.length(); i++) {
                        JSONObject Data2 = posts.getJSONObject(i);
                        JSONObject Data3=Data2.getJSONObject("post_info");

                        title=Data3.getString("title");
                        cover=Data3.getString("cover");
                        time=Data3.getString("time");
                        description=Data3.getString("description");

                        JSONObject Data4=Data2.getJSONObject("author_info");

                        author_name=Data4.getString("name");
                        author_dp=Data4.getString("dp");

                        Info_for_posts ob=new Info_for_posts();
                        ob.setTitle(title);
                        ob.setTime(time);
                        ob.setCover(cover);
                        ob.setDescription(description);
                        ob.setAuthor(author_name);
                        ob.setAuthor_dp(author_dp);
                        list.add(ob);

                    }

                    main2Adapter.setData(list);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request2);






    }
}
