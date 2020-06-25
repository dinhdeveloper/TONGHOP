package com.dinh.demopopup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    PopupWindow mOverlay;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View popupContent = getLayoutInflater().inflate(R.layout.popup, null);
        mOverlay = new PopupWindow();
        mOverlay.setWindowLayoutMode(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        mOverlay.setWidth(getResources().getDimensionPixelSize(
                R.dimen.popupWidth));
        mOverlay.setHeight(getResources().getDimensionPixelSize(
                R.dimen.popupHeight));
        mOverlay.setContentView(popupContent);
        mOverlay.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background));
        mOverlay.setTouchInterceptor(this);
        mOverlay.setFocusable(true);
        e = (EditText) popupContent.findViewById((R.id.e));
    }
    public void close(View v) {
        String nhap = e.getText().toString();
        if (nhap == null || nhap.trim().equals("")) {
            Toast.makeText(this, "Bạn chưa nhập gì cả", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Đã nhập " + nhap, Toast.LENGTH_SHORT).show();
            e.setText("");
            mOverlay.dismiss();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
// PopupWindow is like Dialog, it will leak if left visible while the Activity finishes
        mOverlay.dismiss();
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Handle any direct touch events passed to the PopupWindow
        return false;
    }

    public void onShowWindowClick(View view) {
        if (mOverlay.isShowing()) {
            mOverlay.dismiss();
        } else {

            mOverlay.showAsDropDown(view);
        }
    }
}