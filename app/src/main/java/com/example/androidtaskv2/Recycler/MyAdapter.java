package com.example.androidtaskv2.Recycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtaskv2.Activity.DetailsActivity;
import com.example.androidtaskv2.Model.RetroPosts;
import com.example.androidtaskv2.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private List<RetroPosts> dataList;
    private Context context;

    public MyAdapter(Context context, List<RetroPosts> dataList){

        this.context = context;
        this.dataList = dataList;

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

    //Get a reference to the Views in our layout

        public final View myView;

        TextView title;
        TextView body;
        TextView id;
        RetroPosts retroPost;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            title = myView.findViewById(R.id.titleID);
            body = myView.findViewById(R.id.bodyID);
            id = myView.findViewById(R.id.id);
        }

        //Set up the data
        public void setPost(RetroPosts post){
            this.retroPost = post;

            title.setText(retroPost.getTitle());
            body.setText(String.valueOf(retroPost.getBody()));
            id.setText(("ID: " + retroPost.getId()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("userId", retroPost.getUserId());
                    Log.i("TAG", "User id: " + retroPost.getUserId());
                    context.startActivity(intent);
                }
            });
        }

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_list, parent, false);

        return new CustomViewHolder(view);
    }

    @Override

    //Set the data

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.setPost(dataList.get(position));
    }

    //Calculate the item count for the RecylerView

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}