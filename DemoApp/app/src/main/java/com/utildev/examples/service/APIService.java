package com.utildev.examples.service;

import com.utildev.examples.model.Category;
import com.utildev.examples.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIService {
    @GET("category/list")
    Call<List<Category>> getAllCategory();

    @GET("product/list")
    Call<List<Product>> getAllProduct();
}
