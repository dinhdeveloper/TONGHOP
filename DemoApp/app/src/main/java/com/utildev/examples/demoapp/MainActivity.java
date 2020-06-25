package com.utildev.examples.demoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.utildev.examples.adapter.CategoryAdapter;
import com.utildev.examples.adapter.ProductAdapter;
import com.utildev.examples.adapter.SlideAdvertiseAdapter;
import com.utildev.examples.common.ILoadMore;
import com.utildev.examples.model.Category;
import com.utildev.examples.model.Product;
import com.utildev.examples.service.APIService;
import com.utildev.examples.service.APIUntil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ILoadMore {
    private SliderView sliderView;
    private APIService apiService;

    private RecyclerView rc_category;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    private RecyclerView rc_product;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    private ShimmerFrameLayout mShimmerFrameLayout;
    private View viewer;

    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = APIUntil.getServer();

        addControl();
        addSlide();
        getCategory();
        getProduct();
    }

    private void getProduct() {
        rc_product.setHasFixedSize(true);
        rc_product.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        apiService.getAllProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = (List<Product>) response.body();
                    productAdapter = new ProductAdapter(getApplicationContext(),productList);
                    rc_product.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();

                    //shimmerAnimation stop and hide
                    mShimmerFrameLayout.stopShimmer();
                    mShimmerFrameLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("onFailure getProduct ",t.getMessage());
            }
        });
    }

    private void addSlide() {
        SlideAdvertiseAdapter adapter = new SlideAdvertiseAdapter(getApplicationContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SCALE_DOWN); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINSCALINGTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.argb(1, 248, 249, 249));
        sliderView.setScrollTimeInSec(2); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    private void addControl() {
        rc_category = findViewById(R.id.rc_category);
        sliderView = findViewById(R.id.imageSlider);
        rc_product = findViewById(R.id.rc_product);
        mShimmerFrameLayout = findViewById(R.id.shimmer_view_product);
        viewer = findViewById(R.id.viewer);
    }

    private void getCategory() {

        rc_category.setHasFixedSize(true);
        rc_category.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        apiService.getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList = (List<Category>) response.body();
                categoryAdapter = new CategoryAdapter(categoryList, MainActivity.this);
                rc_category.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();

                //shimmerAnimation stop and hide
                mShimmerFrameLayout.stopShimmer();
                mShimmerFrameLayout.setVisibility(View.GONE);
                viewer.setVisibility(View.VISIBLE);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("onFailure getAllCategory", t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerFrameLayout.startShimmer();
    }

    @Override
    public void onPause() {
        mShimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    public void onLoadMore() {
        productList.add(null);
        productAdapter.notifyItemInserted(productList.size() - 1);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                productList.remove(productList.size() - 1);
                int scrollPosition = productList.size();
                productAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                while (currentSize - 1 < nextLimit) {

                }

                productAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }
}