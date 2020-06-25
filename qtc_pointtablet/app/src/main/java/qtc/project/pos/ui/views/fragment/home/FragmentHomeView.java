package qtc.project.pos.ui.views.fragment.home;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;

public class FragmentHomeView  extends BaseView<FragmentHomeView.UIContainer> implements FragmentHomeViewInterface{

    HomeActivity activity;
    FragmentHomeViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentHomeViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentHomeView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_home;
    }

    public static class UIContainer extends BaseUiContainer {
    }
}
