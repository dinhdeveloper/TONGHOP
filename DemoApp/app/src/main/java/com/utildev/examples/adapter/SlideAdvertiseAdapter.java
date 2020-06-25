package com.utildev.examples.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.utildev.examples.demoapp.R;

public class SlideAdvertiseAdapter extends SliderViewAdapter<SlideAdvertiseAdapter.ViewHolder> {

    private Context context;

    public SlideAdvertiseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_slide_image_advertise, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load("http://mobishops.herokuapp.com/images/imageBanner/tet5.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load("http://mobishops.herokuapp.com/images/imageBanner/tet1.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load("http://mobishops.herokuapp.com/images/imageBanner/tet2.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                Glide.with(viewHolder.itemView)
                        .load("http://mobishops.herokuapp.com/images/imageBanner/tet3.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            case 4:
                Glide.with(viewHolder.itemView)
                        .load("http://mobishops.herokuapp.com/images/imageBanner/tet4.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                Glide.with(viewHolder.itemView)
                        .load("http://mobishops.herokuapp.com/images/imageBanner/tet6.jpg")
                        .into(viewHolder.imageViewBackground);
                break;

        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    class ViewHolder extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
