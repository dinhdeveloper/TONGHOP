package qtc.project.pos.ui.views.fragment.product.productlist.detail;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import qtc.project.pos.R;
import qtc.project.pos.activity.HomeActivity;
import qtc.project.pos.adapter.product.ProductItemCategoryAdapter;
import qtc.project.pos.dependency.AppProvider;
import qtc.project.pos.model.ProductCategoryModel;
import qtc.project.pos.model.ProductListModel;
import qtc.project.pos.ui.views.fragment.product.productlist.FragmentProductListCategoryView;
import qtc.project.pos.ui.views.fragment.product.productlist.FragmentProductListCategoryViewInterface;

public class FragmentProductListDetailView extends BaseView<FragmentProductListDetailView.UIContainer> implements FragmentProductListDetailViewInterface {

    HomeActivity activity;
    FragmentProductListDetailViewCallback callback;
    String image_pro;

    @Override
    public void init(HomeActivity activity, FragmentProductListDetailViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
        
        onClick();
        getDataSpinner();
    }

    private void getDataSpinner() {
        if (callback!=null)
            callback.getDataProductCategory();
    }

    @Override
    public void sendDataToView(ProductListModel model) {
        if (model!=null){
            AppProvider.getImageHelper().displayImage(model.getImage(),ui.image_product,null,R.drawable.imageloading);
            ui.name_product.setText(model.getName());
            ui.id_product.setText(model.getId());
            ui.description_product.setText(model.getDescription());
            ui.tonkho.setText(model.getQuantity_safetystock());

        }

        ui.layout_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductListModel listModel = new ProductListModel();
                listModel.setId(model.getId());
                listModel.setImage(image_pro);
                //listModel
                if (callback !=null){
                    callback.unData(listModel);
                }
            }
        });
    }

    @Override
    public void setDataProductImage(String outfile) {
        image_pro = outfile;
        AppProvider.getImageHelper().displayImage(outfile, ui.image_product, null, R.drawable.imageloading, false);
    }

    @Override
    public void onBack() {
        if (callback != null)
            callback.onBackprogress();
    }

    @Override
    public void initDataInSpinner(ArrayList<ProductCategoryModel> list) {
        ProductItemCategoryAdapter adapter = new ProductItemCategoryAdapter(activity,list);
    }

    private void onClick() {
        ui.imageNavLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback !=null)
                    callback.onBackprogress();
            }
        });
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentProductListDetailView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_product_detail;
    }



    public static class UIContainer extends BaseUiContainer{
        @UiElement(R.id.imageNavLeft)
        public ImageView imageNavLeft;

        @UiElement(R.id.name_product)
        public EditText name_product;

        @UiElement(R.id.id_product)
        public EditText id_product;

        @UiElement(R.id.id_product_category)
        public Spinner id_product_category;

        @UiElement(R.id.tonkho)
        public EditText tonkho;

        @UiElement(R.id.description_product)
        public EditText description_product;

        @UiElement(R.id.choose_file_image)
        public LinearLayout choose_file_image;

        @UiElement(R.id.image_product)
        public ImageView image_product;

        @UiElement(R.id.layout_update)
        public LinearLayout layout_update;

        @UiElement(R.id.layout_delete)
        public LinearLayout layout_delete;
    }
}
