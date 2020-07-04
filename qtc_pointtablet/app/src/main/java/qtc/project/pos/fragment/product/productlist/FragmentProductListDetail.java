package qtc.project.pos.fragment.product.productlist;

import android.os.Bundle;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.fragment.product.productcategory.FragmentCategoryProductDetail;
import qtc.project.pos.model.ProductCategoryModel;
import qtc.project.pos.model.ProductListModel;
import qtc.project.pos.ui.views.fragment.product.productlist.detail.FragmentProductListDetailView;
import qtc.project.pos.ui.views.fragment.product.productlist.detail.FragmentProductListDetailViewCallback;
import qtc.project.pos.ui.views.fragment.product.productlist.detail.FragmentProductListDetailViewInterface;

public class FragmentProductListDetail extends BaseFragment<FragmentProductListDetailViewInterface, BaseParameters> implements FragmentProductListDetailViewCallback {


    public static FragmentProductListDetail newIntance(ProductListModel item) {
        FragmentProductListDetail frag = new FragmentProductListDetail();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", item);
        frag.setArguments(bundle);
        return frag;
    }

    HomeActivity activity;

    @Override
    protected void initialize() {
        activity = (HomeActivity)getActivity();
        view.init(activity,this);
        callDataToBundle();
    }

    private void callDataToBundle() {
        Bundle extras = getArguments();
        if (extras != null) {
            ProductListModel model = (ProductListModel) extras.get("model");
            view.sendDataToView(model);
        }
    }

    @Override
    protected FragmentProductListDetailViewInterface getViewInstance() {
        return new FragmentProductListDetailView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }

    @Override
    public void onBackprogress() {
        if (activity!=null)
            activity.checkBack();
    }
}
