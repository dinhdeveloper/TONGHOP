package qtc.project.pos.fragment.product.productlist;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.base.BaseViewInterface;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.ui.views.fragment.product.productlist.FragmentProductListViewCallback;
import qtc.project.pos.ui.views.fragment.product.productlist.FragmentProductListViewInterface;

public class FragmentProductList extends BaseFragment<FragmentProductListViewInterface, BaseParameters> implements FragmentProductListViewCallback {
    @Override
    protected void initialize() {
        HomeActivity activity = (HomeActivity)getActivity();
        view.init(activity,this);
    }

    @Override
    protected FragmentProductListViewInterface getViewInstance() {
        return null;
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
