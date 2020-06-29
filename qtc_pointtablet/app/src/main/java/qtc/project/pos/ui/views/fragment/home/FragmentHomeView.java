package qtc.project.pos.ui.views.fragment.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import b.laixuantam.myaarlibrary.base.BaseFragmentActivity;
import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.fragment.product.FragmentProduct;

public class FragmentHomeView  extends BaseView<FragmentHomeView.UIContainer> implements FragmentHomeViewInterface{

    HomeActivity activity;
    FragmentHomeViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentHomeViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        onClickItem();
    }

    private void onClickItem() {
        ui.layoutQLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.addFragment(new FragmentProduct(),false,null);
            }
        });
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
        @UiElement(R.id.layoutQLSP)
        public LinearLayout layoutQLSP;
        @UiElement(R.id.layoutQLKH)
        public LinearLayout layoutQLKH;

        @UiElement(R.id.layoutQLDH)
        public LinearLayout layoutQLDH;

        @UiElement(R.id.layoutQLNV)
        public LinearLayout layoutQLNV;

        @UiElement(R.id.layoutQLCDKH)
        public LinearLayout layoutQLCDKH;

        @UiElement(R.id.layoutQLTK)
        public LinearLayout layoutQLTK;

        @UiElement(R.id.layoutQLNCU)
        public LinearLayout layoutQLNCU;
    }
}
