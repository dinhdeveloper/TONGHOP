package qtc.project.pos.ui.views.fragment.product;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.ui.views.fragment.home.FragmentHomeView;
import qtc.project.pos.ui.views.fragment.home.FragmentHomeViewCallback;
import qtc.project.pos.ui.views.fragment.home.FragmentHomeViewInterface;

public class FragmentProductView  extends BaseView<FragmentProductView.UIContainer> implements FragmentProductViewInterface {

    HomeActivity activity;
    FragmentProductViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentProductViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentProductView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_product;
    }

    public static class UIContainer extends BaseUiContainer {
    }
}
