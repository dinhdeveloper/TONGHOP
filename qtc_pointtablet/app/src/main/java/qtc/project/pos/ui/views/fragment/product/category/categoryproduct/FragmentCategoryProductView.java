package qtc.project.pos.ui.views.fragment.product.category.categoryproduct;

import android.view.View;
import android.widget.LinearLayout;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.fragment.product.productcategory.FragmentCategoryProductDetail;

public class FragmentCategoryProductView extends BaseView<FragmentCategoryProductView.UIContainer> implements FragmentCategoryProductViewInterface {

    HomeActivity activity;
    FragmentCategoryProductViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentCategoryProductViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        onClick();
    }

    private void onClick() {
        ui.item_category_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.addFragment(new FragmentCategoryProductDetail(), false, null);
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
        @UiElement(R.id.item_category_product)
        public LinearLayout item_category_product;
    }
}
