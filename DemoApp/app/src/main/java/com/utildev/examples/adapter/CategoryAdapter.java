package com.utildev.examples.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.utildev.examples.demoapp.R;
import com.utildev.examples.model.Category;
import com.utildev.examples.model.Product;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private static final int TYPE_MORE = 0;
    private static final int TYPE_ITEM = 1;

    List<Category> categories;
    Context context;

    public CategoryAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            // Here Inflating your recyclerview item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_category, parent, false);
            return new ViewHolder(itemView);
        } else if (viewType == TYPE_MORE) {
            // Here Inflating your header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_more_category, parent, false);
            return new ViewHolder(itemView);
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);

        if (context != null) {
            if (categories.get(position).getCategoryId() !=6){
                Glide.with(context).load(categories.get(position).getCategoryImage()).into(holder.imageItem);
                holder.nameItem.setText(categories.get(position).getCategoryName().toString());
            }
            else {
                holder.nameItem.setText(null);
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 6) {
            return TYPE_MORE;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        ImageView imageItem;
        TextView nameItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imageItem);
            nameItem = itemView.findViewById(R.id.nameItem);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
