package com.example.androidtaskv2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtaskv2.Data.GetData;
import com.example.androidtaskv2.Model.RetroPhoto;
import com.example.androidtaskv2.Model.RetroUser;
import com.example.androidtaskv2.R;
import com.example.androidtaskv2.Recycler.MyAdapterGrid;
import com.example.androidtaskv2.Util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private TextView detailsNameID, detailsUsernameID, detailsEmailID, detailsPhoneID, detailsCompanyID;
    private TextView detailsStreetID;
    private RecyclerView recyclerViewGrid;

    private MyAdapterGrid myAdapterGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsNameID = findViewById(R.id.detailsNameID);
        detailsUsernameID = findViewById(R.id.detailsUsernameID);
        detailsEmailID = findViewById(R.id.detailsEmailID);
        detailsPhoneID = findViewById(R.id.detailsPhoneId);
        detailsCompanyID = findViewById(R.id.detailsCompanyID);
        detailsStreetID = findViewById(R.id.detailsStreetID);

        recyclerViewGrid = findViewById(R.id.recyclerViewGrid);
        recyclerViewGrid.setLayoutManager(new GridLayoutManager(this, 3));

        //get user id
        int userId = -1;
        if(getIntent() != null)
            userId = getIntent().getIntExtra("userId", -1);
        Toast.makeText(this, "UserId: " + userId, Toast.LENGTH_SHORT).show();

        //Get user data by id
        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        Call<RetroUser> call = service.getUserById(userId);
        call.enqueue(new Callback<RetroUser>() {
            @Override
            public void onResponse(Call<RetroUser> call, Response<RetroUser> response) {
                Log.e("TAG++", "Up");
                if(response.isSuccessful()){
                    RetroUser user = response.body();
                    detailsNameID.setText("Name: " + user.getName());
                    detailsUsernameID.setText("Username: " + user.getUsername());
                    detailsEmailID.setText("Email: " + user.getEmail());
                    detailsPhoneID.setText("Phone Number: " + user.getPhone());
                    detailsCompanyID.setText("Company name: " + user.getCompanyName());
                    detailsStreetID.setText("City and Address: " + user.getAddress());
                }
            }

            @Override
            public void onFailure(Call<RetroUser> call, Throwable t) {
                Log.e("TAG++", "Down");
            }
        });

        //Get photos
        Call<List<RetroPhoto>> callPhotos = service.getPhotos();
        callPhotos.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                if(response.isSuccessful()){
                    myAdapterGrid = new MyAdapterGrid(DetailsActivity.this, response.body());
                    recyclerViewGrid.setAdapter(myAdapterGrid);
                }
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {

            }
        });

    }
}