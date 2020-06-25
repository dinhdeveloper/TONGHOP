package qtc.project.pos.fragment.levelcustomer;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.ui.views.fragment.customer.FragmentCustomerViewCallback;
import qtc.project.pos.ui.views.fragment.customer.FragmentCustomerViewInterface;
import qtc.project.pos.ui.views.fragment.levelcustomer.FragmentLevelCustomerView;
import qtc.project.pos.ui.views.fragment.levelcustomer.FragmentLevelCustomerViewCallback;
import qtc.project.pos.ui.views.fragment.levelcustomer.FragmentLevelCustomerViewInterface;

public class FragmentLevelCustomer extends BaseFragment<FragmentLevelCustomerViewInterface, BaseParameters> implements FragmentLevelCustomerViewCallback {
    @Override
    protected void initialize() {
        HomeActivity activity = (HomeActivity) getActivity();
        view.init(activity, this);
    }

    @Override
    protected FragmentLevelCustomerViewInterface getViewInstance() {
        return new FragmentLevelCustomerView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
