package com.example.carsrecyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RequestInterface {

    @GET("getHaberler")
    Call<List<CarsModel>> getCarsJson();
}
