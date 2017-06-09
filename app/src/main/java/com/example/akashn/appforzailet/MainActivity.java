package com.example.akashn.appforzailet;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayList<Info_for_Main> list=new ArrayList<>();
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.rv);
         recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        final MainAdapter mainAdapter=new MainAdapter(getApplicationContext());
        recyclerView.setAdapter(mainAdapter);

        //animatioon







        final StringRequest request2 = new StringRequest(Request.Method.GET,"http://zailet.com/zailet_internship_assignment/get_data.php?get_topics=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // String o_id="";

                Log.d("print",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                String name = "";
                String image="";




                    JSONArray result = jsonObject.getJSONArray("result");




                    for (int i = 0; i < result.length(); i++) {
                        JSONObject Data2 = result.getJSONObject(i);
                        name=Data2.getString("interest");
                        image=Data2.getString("cover");
                        Info_for_Main ob=new Info_for_Main();
                        ob.setTitle(name);
                        ob.setPicture(image);
                        list.add(ob);

                    }

                   mainAdapter.setData(list);


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

        Button proceed= (Button) findViewById(R.id.proceed);
        proceed.setText("Proceed ");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            }
        });



    }




}
