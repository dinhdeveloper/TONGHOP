package qtc.project.pos.fragment.customer;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.ui.views.fragment.customer.FragmentCustomerView;
import qtc.project.pos.ui.views.fragment.customer.FragmentCustomerViewCallback;
import qtc.project.pos.ui.views.fragment.customer.FragmentCustomerViewInterface;
import qtc.project.pos.ui.views.fragment.product.FragmentProductViewCallback;
import qtc.project.pos.ui.views.fragment.product.FragmentProductViewInterface;

public class FragmentCustomer extends BaseFragment<FragmentCustomerViewInterface, BaseParameters> implements FragmentCustomerViewCallback {
    @Override
    protected void initialize() {
        HomeActivity activity = (HomeActivity) getActivity();
        view.init(activity, this);
    }

    @Override
    protected FragmentCustomerViewInterface getViewInstance() {
        return new FragmentCustomerView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
