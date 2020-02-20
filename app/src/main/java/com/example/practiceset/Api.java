package com.example.practiceset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://api.myjson.com/bins/";

    @GET("zmp20")
    Call<List<Item>> getItems();
}
