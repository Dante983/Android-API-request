package com.example.androidtaskv2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidtaskv2.Model.RetroPosts;
import com.example.androidtaskv2.Recycler.MyAdapter;
import com.example.androidtaskv2.Data.GetData;
import com.example.androidtaskv2.R;
import com.example.androidtaskv2.Util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a handler for the RetrofitInstance interface

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);

        Call<List<RetroPosts>> call = service.getAllPosts();
        Toast.makeText(this, "Welcome to the App", Toast.LENGTH_SHORT).show();

        //Execute the request asynchronously

        call.enqueue(new Callback<List<RetroPosts>>() {

            @Override

        //Handle a successful response

            public void onResponse(Call<List<RetroPosts>> call, Response<List<RetroPosts>> response) {
                loadDataList(response.body());
            }

            @Override

        //Handle execution failures

            public void onFailure(Call<List<RetroPosts>> call, Throwable throwable) {

        //If the request fails, then display the following toast

                Toast.makeText(MainActivity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }

        //Display the retrieved data as a list

    private void loadDataList(List<RetroPosts> usersList) {

        //Get a reference to the RecyclerView

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new MyAdapter(this,usersList);

        //Use a LinearLayoutManager with default vertical orientation

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        myRecyclerView.setLayoutManager(layoutManager);

        //Set the Adapter to the RecyclerView

        myRecyclerView.setAdapter(myAdapter);
    }

}