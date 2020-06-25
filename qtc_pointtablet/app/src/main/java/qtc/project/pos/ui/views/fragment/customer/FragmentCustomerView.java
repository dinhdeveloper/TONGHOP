package qtc.project.pos.ui.views.fragment.customer;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.ui.views.fragment.product.FragmentProductView;
import qtc.project.pos.ui.views.fragment.product.FragmentProductViewInterface;

public class FragmentCustomerView extends BaseView<FragmentCustomerView.UIContainer> implements FragmentCustomerViewInterface {
    @Override
    public void init(HomeActivity activity, FragmentCustomerViewCallback callback) {

    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentCustomerView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_customer;
    }

    public static class UIContainer extends BaseUiContainer{
    }
}
