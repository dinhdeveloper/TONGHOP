package qtc.project.pos.fragment.product.productcategory;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.ui.views.fragment.product.category.categoryproductdetail.FragmentCategoryProductDetailView;
import qtc.project.pos.ui.views.fragment.product.category.categoryproductdetail.FragmentCategoryProductDetailViewCallback;
import qtc.project.pos.ui.views.fragment.product.category.categoryproductdetail.FragmentCategoryProductDetailViewInterface;

public class FragmentCategoryProductDetail extends BaseFragment<FragmentCategoryProductDetailViewInterface, BaseParameters> implements FragmentCategoryProductDetailViewCallback {
    @Override
    protected void initialize() {
        HomeActivity activity = (HomeActivity) getActivity();
        view.init(activity, this);
    }

    @Override
    protected FragmentCategoryProductDetailViewInterface getViewInstance() {
        return new FragmentCategoryProductDetailView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
