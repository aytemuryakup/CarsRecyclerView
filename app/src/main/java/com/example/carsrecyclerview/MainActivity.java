package com.example.carsrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CarsModel> carsModels = new ArrayList<>();

    private CarsAdapter carsAdapter;
    private RecyclerView cars_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cars_recyclerView=(RecyclerView)findViewById(R.id.cars_recyclerview);
        cars_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getCarsResponse();
    }

    private void getCarsResponse() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.239/api/uygulama/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<List<CarsModel>> call = requestInterface.getCarsJson();

        call.enqueue(new Callback<List<CarsModel>>() {
            @Override
            public void onResponse(Call<List<CarsModel>> call, Response<List<CarsModel>> response) {
                carsModels = new ArrayList<>(response.body());
                carsAdapter = new CarsAdapter(MainActivity.this,carsModels);
                cars_recyclerView.setAdapter(carsAdapter);

                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<CarsModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
