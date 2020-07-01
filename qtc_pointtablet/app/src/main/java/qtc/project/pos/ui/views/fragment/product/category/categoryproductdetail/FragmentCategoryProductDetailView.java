package qtc.project.pos.ui.views.fragment.product.category.categoryproductdetail;

import android.view.View;
import android.widget.LinearLayout;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;

public class FragmentCategoryProductDetailView extends BaseView<FragmentCategoryProductDetailView.UIContainer> implements FragmentCategoryProductDetailViewInterface {

    HomeActivity activity;
    FragmentCategoryProductDetailViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentCategoryProductDetailViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        onClickItem();
    }

    private void onClickItem() {
        ui.layout_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentCategoryProductDetailView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_category_product_detail;
    }

    public static class UIContainer extends BaseUiContainer {
        @UiElement(R.id.layout_update)
        public LinearLayout layout_update;
    }
}
