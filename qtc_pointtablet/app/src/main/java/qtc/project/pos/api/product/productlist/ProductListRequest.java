package qtc.project.pos.api.product.productlist;


import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.BaseApiParams;
import qtc.project.pos.api.product.productcategory.ProductCategoryRequest;
import qtc.project.pos.helper.Consts;
import qtc.project.pos.model.BaseResponseModel;
import qtc.project.pos.model.ProductCategoryModel;
import qtc.project.pos.model.ProductListModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

@ApiRequest.ApiName("list_product")
public class ProductListRequest  extends ApiRequest<ProductListRequest.Service, BaseResponseModel<ProductListModel>, ProductListRequest.ApiParams> {

    public ProductListRequest() {
        super(ProductListRequest.Service.class, RequestOrigin.NONE, Consts.HOST_API, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(BaseResponseModel<ProductListModel> result) throws Exception {

    }

    @Override
    protected Call<BaseResponseModel<ProductListModel>> call(ProductListRequest.ApiParams params) {
        params.detect = "product_manager";
        return getService().call(params);
    }


    interface Service {
        @Headers(Consts.HEADES)
        @POST(Consts.REST_ENDPOINT)
        Call<BaseResponseModel<ProductListModel>> call(@Body ProductListRequest.ApiParams params);
    }

    public static class ApiParams extends BaseApiParams {
        public String detect;
        public String type_manager;
        public String id_product;
        public String id_code;
        public String name;
        public String description;
        public String barcode ;
        public String category_id ;
        public String quantity_safetystock ;
        public String qr_code ;
        public String image ;
    }
}
