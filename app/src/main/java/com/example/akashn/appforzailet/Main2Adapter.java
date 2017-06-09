package com.example.akashn.appforzailet;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by akashn on 07-06-2017.
 */

public class Main2Adapter extends Adapter <Main2Adapter.MyViewHolder>{
    private LayoutInflater inflator;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private RequestQueue request2;
    private ImageLoader imageLoader;
    private int mPreviousPosition = 0;
private ArrayList<Info_for_posts> data= new ArrayList<>();

    public ArrayList<Info_for_posts> getData() {
        return data;
    }

    public void setData(ArrayList<Info_for_posts> data) {
        this.data = data;
        notifyItemChanged(0,data.size());
    }

    public Main2Adapter(Context context) {
        this.inflator = LayoutInflater.from(context);
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=inflator.inflate(R.layout.recyclerview2,parent,false);
  MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Info_for_posts current=data.get(position);
        if (position > mPreviousPosition) {
            AnimationUtils.animate2(holder, true);
//
        } else {
            AnimationUtils.animate2(holder, false);
//
        }
        holder.title.setText(current.getTitle());
        String url=current.getCover();
        String url2=current.getAuthor_dp();

        holder.author.setText(current.getAuthor());
        holder.time.setText(current.getTime());
        holder.description.setText(current.getDescription());




        holder.cover.setImageUrl("http://zailet.com/"+url,mImageLoader);
        holder.author_dp.setImageUrl("http://zailet.com/"+url2,mImageLoader);


        mPreviousPosition = position;
    }



    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder  {
           TextView title,author,time,description;
        NetworkImageView cover,author_dp;
        public MyViewHolder(View itemView) {
            super(itemView);
              title= (TextView) itemView.findViewById(R.id.title);
            description= (TextView) itemView.findViewById(R.id.description);
        author= (TextView) itemView.findViewById(R.id.author_name);
           cover= (NetworkImageView)itemView.findViewById(R.id.cover);
            author_dp= (NetworkImageView)itemView.findViewById(R.id.author_dp);
                               time= (TextView) itemView.findViewById(R.id.time);
            //itemView.setOnClickListener(this);
        }




    }
}
