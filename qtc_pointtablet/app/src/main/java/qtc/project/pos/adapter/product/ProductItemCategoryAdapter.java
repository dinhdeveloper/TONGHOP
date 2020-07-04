package qtc.project.pos.adapter.product;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;
import qtc.project.pos.R;
import qtc.project.pos.model.ProductCategoryModel;

public class ProductItemCategoryAdapter extends SuperAdapter<ProductCategoryModel> {

    Context context;
    List<ProductCategoryModel> items;

    public ProductItemCategoryAdapter(Context context, List<ProductCategoryModel> items) {
        super(context, items, R.layout.custom_item_product_category_in_product_detail);
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ProductCategoryModel item) {
        TextView id_product_category = holder.findViewById(R.id.id_product_category);
        ImageView close_item = holder.findViewById(R.id.close_item);

        id_product_category.setText(item.getName());
    }
}
