package com.utildev.examples.datepicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager;

public class MainActivity extends AppCompatActivity {

    List<Years> yearsList;
    //YearAdapter adapter;
    RecyclerView rv, rc_month;
    PickerAdapter adapter;

    String nam = "";
    String thang = "";

    int thangnow = 0;
    int namnow = 0;

    LinearLayout buttonLayout;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLayout = findViewById(R.id.buttonLayout);

        rv = (RecyclerView) findViewById(R.id.rv);


        LocalDateTime currentDateTime = LocalDateTime.now();
        thangnow = currentDateTime.get(ChronoField.MONTH_OF_YEAR);
        namnow = currentDateTime.get(ChronoField.YEAR);

        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.99f);
        pickerLayoutManager.setScaleDownDistance(1f);

        adapter = new PickerAdapter(this, getData(2030), rv);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv);
        rv.setLayoutManager(pickerLayoutManager);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        rv.setHasFixedSize(true);

        pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.onScrollStopListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void selectedView(View view) {
                ((TextView) view).setTextColor(Color.parseColor("#FFC107"));
                if (Integer.parseInt(((TextView) view).getText().toString()) == namnow) {
                    rv.scrollToPosition(namnow);
                }
                nam = ((TextView) view).getText().toString();
                //Toast.makeText(MainActivity.this, ("Selected value : " + ((TextView) view).getText().toString()), Toast.LENGTH_SHORT).show();
            }
        });

        dataMonth();


        buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Bạn chọn Năm :" + nam + " " + thang, Toast.LENGTH_SHORT).show();
            }
        });

        getDate();


    }

    private void dataMonth() {
        rc_month = (RecyclerView) findViewById(R.id.rc_month);

        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false);
        //rc_month.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.99f);
        pickerLayoutManager.setScaleDownDistance(1.5f);

        adapter = new PickerAdapter(this, getMonth(13), rc_month);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rc_month);
        rc_month.setLayoutManager(pickerLayoutManager);
        rc_month.setAdapter(adapter);
        rc_month.getLayoutManager().scrollToPosition(thangnow);
        adapter.notifyDataSetChanged();
        rc_month.setHasFixedSize(true);

        pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.onScrollStopListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void selectedView(View view) {
                ((TextView) view).setTextColor(Color.parseColor("#FFC107"));
                thang = ((TextView) view).getText().toString();
                //Toast.makeText(MainActivity.this, ("Selected value : " + ((TextView) view).getText().toString()), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<String> getData(int count) {
        List<String> data = new ArrayList<>();
        for (int i = 2010; i < count; i++) {
            data.add(String.valueOf(i));
        }
        data.add("           ");
        return data;
    }

    public List<String> getMonth(int count) {
        List<String> data = new ArrayList<>();
        data.add("                    ");
        for (int i = 1; i < count; i++) {
            data.add(String.valueOf("Tháng " + i));
        }
        data.add("                  ");
        return data;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void getDate() {
        DateRangeCalendarView datecale = findViewById(R.id.calendar);
        Calendar calendar = Calendar.getInstance();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        datecale.setCurrentMonth(calendar);
    }

}