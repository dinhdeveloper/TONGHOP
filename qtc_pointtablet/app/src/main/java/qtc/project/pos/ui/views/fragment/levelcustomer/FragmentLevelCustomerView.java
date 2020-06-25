package qtc.project.pos.ui.views.fragment.levelcustomer;


import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.ui.views.fragment.customer.FragmentCustomerView;
import qtc.project.pos.ui.views.fragment.customer.FragmentCustomerViewInterface;

public class FragmentLevelCustomerView  extends BaseView<FragmentLevelCustomerView.UIContainer> implements FragmentLevelCustomerViewInterface {
    @Override
    public void init(HomeActivity activity, FragmentLevelCustomerViewCallback callback) {

    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentLevelCustomerView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_level_customer;
    }

    public static class UIContainer extends BaseUiContainer{
    }
}
