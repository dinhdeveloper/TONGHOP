package qtc.project.pos.ui.views.fragment.product.productlist.detail;

import qtc.project.pos.model.ProductListModel;

public interface FragmentProductListDetailViewCallback {
    void onBackprogress();

    void unData(ProductListModel listModel);

    void getDataProductCategory();
}
