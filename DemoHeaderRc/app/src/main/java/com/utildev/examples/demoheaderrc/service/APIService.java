package com.utildev.examples.demoheaderrc.service;

import com.utildev.examples.demoheaderrc.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("category/list")
    Call<List<Category>> getAllCategory();
}
