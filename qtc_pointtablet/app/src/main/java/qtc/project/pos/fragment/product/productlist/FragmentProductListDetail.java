package qtc.project.pos.fragment.product.productlist;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.ErrorApiResponse;
import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.api.product.productcategory.ProductCategoryRequest;
import qtc.project.pos.dependency.AppProvider;
import qtc.project.pos.fragment.product.productcategory.FragmentCategoryProductDetail;
import qtc.project.pos.model.BaseResponseModel;
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

    @Override
    public void unData(ProductListModel listModel) {

    }

    @Override
    public void getDataProductCategory() {
        showProgress();
        ProductCategoryRequest.ApiParams params = new ProductCategoryRequest.ApiParams();
        params.type_manager = "list_category";
        AppProvider.getApiManagement().call(ProductCategoryRequest.class, params, new ApiRequest.ApiCallback<BaseResponseModel<ProductCategoryModel>>() {
            @Override
            public void onSuccess(BaseResponseModel<ProductCategoryModel> body) {
                dismissProgress();
                if (body != null) {
                    ArrayList<ProductCategoryModel> list = new ArrayList<>();
                    list.addAll(Arrays.asList(body.getData()));
                    view.initDataInSpinner(list);
                }
            }

            @Override
            public void onError(ErrorApiResponse error) {
                dismissProgress();
                Log.e("onError", error.message);
            }

            @Override
            public void onFail(ApiRequest.RequestError error) {
                dismissProgress();
                Log.e("onFail", error.name());
            }
        });
    }
}
