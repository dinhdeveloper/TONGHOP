package qtc.project.pos.fragment.product.productcategory;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.ui.views.fragment.product.category.categoryproduct.FragmentCategoryProductView;
import qtc.project.pos.ui.views.fragment.product.category.categoryproduct.FragmentCategoryProductViewCallback;
import qtc.project.pos.ui.views.fragment.product.category.categoryproduct.FragmentCategoryProductViewInterface;

public class FragmentCategoryProduct extends BaseFragment<FragmentCategoryProductViewInterface, BaseParameters> implements FragmentCategoryProductViewCallback {
    @Override
    protected void initialize() {
        HomeActivity activity = (HomeActivity) getActivity();
        view.init(activity, this);
    }

    @Override
    protected FragmentCategoryProductViewInterface getViewInstance() {
        return new FragmentCategoryProductView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
