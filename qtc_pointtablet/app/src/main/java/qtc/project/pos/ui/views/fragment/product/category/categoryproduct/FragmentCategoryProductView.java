package qtc.project.pos.ui.views.fragment.product.category.categoryproduct;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.adapter.product.ProductCategoryAdapter;
import qtc.project.pos.fragment.home.FragmentHome;
import qtc.project.pos.fragment.product.FragmentProduct;
import qtc.project.pos.fragment.product.FragmentProductManager;
import qtc.project.pos.fragment.product.productcategory.FragmentCategoryProductDetail;
import qtc.project.pos.fragment.product.productcategory.FragmentCreateProductCategory;
import qtc.project.pos.model.ProductCategoryModel;

public class FragmentCategoryProductView extends BaseView<FragmentCategoryProductView.UIContainer> implements FragmentCategoryProductViewInterface {

    HomeActivity activity;
    FragmentCategoryProductViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentCategoryProductViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        onClickBack();

    }

    private void onClickBack() {
        ui.imageNavLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback!=null)
                    callback.setBackProgress();
            }
        });

        ui.image_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.replaceFragment(new FragmentCreateProductCategory(),true,null);
            }
        });
    }

    @Override
    public void initGetListCategoryProduct(ArrayList<ProductCategoryModel> list) {
        ProductCategoryAdapter categoryAdapter = new ProductCategoryAdapter(activity,list);
        ui.recycler_view_category_product.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        ui.recycler_view_category_product.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        categoryAdapter.setListener(new ProductCategoryAdapter.ProductCategoryAdapterListener() {
            @Override
            public void onClickItem(ProductCategoryModel model) {
                if (callback!=null){
                    callback.onSendData(model);
                }
            }
        });
    }


    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentCategoryProductView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_category_product;
    }

    public static class UIContainer extends BaseUiContainer {
        @UiElement(R.id.recycler_view_category_product)
        public RecyclerView recycler_view_category_product;

        @UiElement(R.id.imageNavLeft)
        public ImageView imageNavLeft;

        @UiElement(R.id.image_create)
        public ImageView image_create;
    }
}
