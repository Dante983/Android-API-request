package com.example.androidtaskv2.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.androidtaskv2.Model.RetroPhoto;
import com.example.androidtaskv2.R;

import java.util.List;

public class MyAdapterGrid extends RecyclerView.Adapter<MyAdapterGrid.CustomViewHolder> {

    private List<RetroPhoto> dataList;
    private Context context;

    public MyAdapterGrid(Context context, List<RetroPhoto> dataList){

        this.context = context;
        this.dataList = dataList;

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

    //Get a reference to the Views in our layout

            public final View myView;

            TextView title;
            ImageView image;

            CustomViewHolder(View itemView) {
                super(itemView);
                myView = itemView;

                title = myView.findViewById(R.id.textView);
                image = myView.findViewById(R.id.imageView);
            }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_item, parent, false);

        return new CustomViewHolder(view);
    }

    @Override

    //Set the data

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        RetroPhoto photo = dataList.get(position);
        holder.title.setText(photo.getTitle());
        GlideUrl url = new GlideUrl(photo.getUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
                .build());
        Glide.with(context).load(url).into(holder.image);
    }


    //Calculate the item count for the RecyclerView

    @Override
    public int getItemCount() {

    //load only 20 photos as requested
        return 20;
    }
}