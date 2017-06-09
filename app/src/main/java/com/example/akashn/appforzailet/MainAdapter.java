package com.example.akashn.appforzailet;

import android.content.Context;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.media.Image;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by akashn on 07-06-2017.
 */

public class MainAdapter extends RecyclerView.Adapter <MainAdapter.MyViewHolder>{
    private LayoutInflater inflator;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private int mPreviousPosition = 0;
private ArrayList<Info_for_Main> data= new ArrayList<>();

    public ArrayList<Info_for_Main> getData() {
        return data;
    }

    public void setData(ArrayList<Info_for_Main> data) {
        this.data = data;
        notifyItemChanged(0,data.size());
    }

    public MainAdapter(Context context) {

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
       View view=inflator.inflate(R.layout.recyclerview,parent,false);

  MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Info_for_Main current=data.get(position);

        holder.tv.setText(current.getTitle());
        String url=current.getPicture();
        if(current.getTick().equals("true"))
            holder.tick.setVisibility(View.VISIBLE);
        if(current.getTick().equals("false"))

        holder.tick.setVisibility(View.INVISIBLE);

        holder.im1.setImageUrl("http://zailet.com/"+url,mImageLoader);
        if (position > mPreviousPosition) {
            AnimationUtils.animate(holder, true);
//
        } else {
            AnimationUtils.animate(holder, false);
//
        }
        mPreviousPosition = position;
    }



    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
           TextView tv;
        View tick;
        NetworkImageView im1;


        public MyViewHolder(View itemView) {
            super(itemView);
              tick=itemView.findViewById(R.id.tick);
            tv= (TextView) itemView.findViewById(R.id.text1);




            im1 = (NetworkImageView)itemView.findViewById(R.id.image1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Info_for_Main current=data.get(getAdapterPosition());
            if(current.getTick().equals("true"))
                current.setTick("false");
            else

            current.setTick("true");



            notifyItemChanged(getAdapterPosition());




        }

    }
}
