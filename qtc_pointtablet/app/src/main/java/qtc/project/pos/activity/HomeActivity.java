package qtc.project.pos.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseFragmentActivity;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.helper.map.location.PermissionUtils;
import b.laixuantam.myaarlibrary.widgets.dialog.alert.KAlertDialog;
import b.laixuantam.myaarlibrary.widgets.multiple_media_picker.Gallery;
import cn.pedant.SweetAlert.SweetAlertDialog;
import qtc.project.pos.R;
//import qtc.project.pos.fragment.account.profile_manager.FragmentProfileManager;
import qtc.project.pos.dependency.AppProvider;
import qtc.project.pos.fragment.home.FragmentHome;
import qtc.project.pos.fragment.levelcustomer.FragmentCreateLevelCustomer;
import qtc.project.pos.fragment.levelcustomer.FragmentLevelCustomerDetail;
import qtc.project.pos.fragment.product.productcategory.FragmentCategoryProductDetail;
import qtc.project.pos.fragment.product.productcategory.FragmentCreateProductCategory;
import qtc.project.pos.fragment.product.productlist.FragmentProductListDetail;
import qtc.project.pos.fragment.product.quanlylohang.FragmentChiTietLoHang;
import qtc.project.pos.fragment.product.quanlylohang.FragmentDonTraHang;
import qtc.project.pos.fragment.report.thongkebanhang.doanhthu_theo_khachhang.FragmentDoanhThuTheoKhachHang;
import qtc.project.pos.fragment.report.thongkebanhang.tomtatdoanhthu.FragmentFilterTomTatDoanhSo;
import qtc.project.pos.fragment.report.thongkebanhang.tomtatdoanhthu.FragmentTomTatDoanhThu;
import qtc.project.pos.fragment.report.thongkebanhang.tomtatdoanhthu.thongke.FragmentThongKe;
import qtc.project.pos.fragment.report.thongkebanhang.tomtatdoanhthu.tongdoanhthu.FragmentTongDoanhThu;
import qtc.project.pos.fragment.report.thongkekho.tonkho_vs_doanhthu.FragmentTK_TonKho_VS_DoanhThu;
import qtc.project.pos.model.SupplierModel;
import qtc.project.pos.ui.views.action_bar.base_main_actionbar.BaseMainActionbarViewInterface;
import qtc.project.pos.ui.views.activity.home_activity.HomeActivityView;
import qtc.project.pos.ui.views.activity.home_activity.HomeActivityViewCallback;
import qtc.project.pos.ui.views.activity.home_activity.HomeActivityViewInterface;

public class HomeActivity extends BaseFragmentActivity<HomeActivityViewInterface, BaseMainActionbarViewInterface, BaseParameters> implements HomeActivityViewCallback {
    private KAlertDialog mCustomAlert;
    private final int OPEN_MEDIA_PICKER = 10101;
    private final int OPEN_MEDIA_PICKER_PERMISSION = 10102;
    private static final int CAMERA_REQUEST = 10103;
    public static final int EMAIL_SEND = 10104;
    private final int MY_CAMERA_PERMISSION_CODE = 10104;
    private ArrayList<String> permissions = new ArrayList<>();
    private PermissionUtils permissionUtils;

    @Override
    protected void initialize(Bundle savedInstanceState) {
        view.init(this, this);
        setLayoutMain();
    }

    public void alerUpdate() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.alert_dialog_success, null);
        TextView title_text = popupView.findViewById(R.id.title_text);
        TextView content_text = popupView.findViewById(R.id.content_text);
        Button custom_confirm_button = popupView.findViewById(R.id.custom_confirm_button);

        title_text.setText("Xác nhận");
        content_text.setText("Bạn đã cập nhật thành công!");

        AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setView(popupView);
        AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        custom_confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void alertSuccess() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.alert_dialog_success, null);
        TextView title_text = popupView.findViewById(R.id.title_text);
        TextView content_text = popupView.findViewById(R.id.content_text);
        Button custom_confirm_button = popupView.findViewById(R.id.custom_confirm_button);

        title_text.setText("Xác nhận");
        content_text.setText("Bạn đã xóa sản phẩm thành công!");

        AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setView(popupView);
        AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        custom_confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBack();
                dialog.dismiss();
            }
        });
    }

    public void alertDelete() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.alert_dialog_waiting, null);
        TextView title_text = popupView.findViewById(R.id.title_text);
        TextView content_text = popupView.findViewById(R.id.content_text);
        Button cancel_button = popupView.findViewById(R.id.cancel_button);
        Button custom_confirm_button = popupView.findViewById(R.id.custom_confirm_button);

        title_text.setText("Cảnh báo");
        content_text.setText("Bạn có muốn xóa sản phẩm này không?");

        AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setView(popupView);
        AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    private void setLayoutMain() {
        FullScreencall();
        replaceFragment(new FragmentHome(), true, Animation.SLIDE_IN_OUT);
    }

    @Override
    public void onBackPressed() {
//        BaseFragment baseFragment = getCurrentFragment();
//        if (baseFragment instanceof ){
//            super.onBackPressed();
//        }else if(){
//            checkBack();
//        }
    }

    public void setDataNhaCungUng(SupplierModel model) {
        BaseFragment baseFragment = getCurrentFragment();
        if (baseFragment instanceof FragmentChiTietLoHang) {
            ((FragmentChiTietLoHang) baseFragment).setDataNhaCungUng(model);
        }
        if (baseFragment instanceof FragmentDonTraHang) {
            ((FragmentDonTraHang) baseFragment).setOnBack();
        }
    }

    public void setDataDate(String nam, String thang,int ngay){
        BaseFragment baseFragment = getCurrentFragment();
        if (baseFragment instanceof FragmentTongDoanhThu) {
            ((FragmentTongDoanhThu) baseFragment).filterDataTheoThang(nam,thang,ngay);
        }

        if (baseFragment instanceof FragmentTK_TonKho_VS_DoanhThu){
            ((FragmentTK_TonKho_VS_DoanhThu)baseFragment).filterDataDate(nam,thang,ngay);
        }

        if (baseFragment instanceof FragmentDoanhThuTheoKhachHang) {
            ((FragmentDoanhThuTheoKhachHang) baseFragment).filterDataTheoThang(nam,thang,ngay);
        }

        if (baseFragment instanceof FragmentTomTatDoanhThu) {
            ((FragmentTomTatDoanhThu) baseFragment).onBackProgress();
        }
    }

    public void setDataYear(String nam) {
        BaseFragment baseFragment = getCurrentFragment();
        if (baseFragment instanceof FragmentThongKe) {
            ((FragmentThongKe) baseFragment).filterDataYear(nam);
        }

        if (baseFragment instanceof FragmentDoanhThuTheoKhachHang) {
            ((FragmentDoanhThuTheoKhachHang) baseFragment).filterDataYear(nam);
        }

        if (baseFragment instanceof FragmentTomTatDoanhThu) {
            ((FragmentTomTatDoanhThu) baseFragment).onBackProgress();
        }
    }

    private int isShowContainer = 0;

    public void checkBack() {
        FullScreencall();

        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }

    }

    public void checkFragment() {

        FragmentManager fm = getSupportFragmentManager();

        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();

        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected HomeActivityViewInterface getViewInstance() {
        return new HomeActivityView();
    }

    @Override
    protected BaseMainActionbarViewInterface getActionbarInstance() {
        return null;
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.content_frame;
    }

    @Override
    public void onClickItemNav(Fragment fragment) {
        replaceFragment((BaseFragment<?, ?>) fragment, false, Animation.SLIDE_IN_OUT);
    }

    @Override
    public void logOut() {
        AppProvider.getPreferences().clear();
        finish();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void showAlr() {
        new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Xác nhận")
                .setContentText("Bạn đã cập nhật thành công!")
                .setConfirmText("Xác nhận")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }


    @Override
    public void onClickBottomBarMenuHome() {

    }

    @Override
    public void onClickBottomBarMenuCategory() {

    }

    @Override
    public void onClickBottomBarMenuAccount() {

    }

    @Override
    public void onClickBottomBarMenuShoppingCart() {

    }

    @Override
    public void onClickBottomBarMenuTransaction() {

    }

    @Override
    public void onClickBottomBarMenuOrder() {

    }

    public void showAlert(String title, String message, int type) {

        if (mCustomAlert == null) {
            mCustomAlert = new KAlertDialog(this);
            mCustomAlert.setCancelable(false);
            mCustomAlert.setCanceledOnTouchOutside(false);
        }
        mCustomAlert.showCancelButton(false);

        mCustomAlert.setTitleText(title);

        mCustomAlert
                .setContentText(message)
                .setConfirmText("OK")
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        if (mCustomAlert != null)
                            mCustomAlert.dismiss();
                    }
                }).changeAlertType(type);
        mCustomAlert.show();
    }

    public void showAlert(String message) {
        showAlert("", message, 0);
    }

    public void showConfirmAlert(String title, String mess, KAlertDialog.KAlertClickListener actionConfirm, int type) {
        showConfirmAlert(title, mess, "", "", actionConfirm, null, type);
    }

    public void showConfirmAlert(String title, String mess, KAlertDialog.KAlertClickListener actionConfirm, KAlertDialog.KAlertClickListener actionCancel, int type) {
        showConfirmAlert(title, mess, "", "", actionConfirm, actionCancel, type);
    }

    public void showConfirmAlert(String title, String mess, String titleButtonConfirm, String titleButtonCancel, KAlertDialog.KAlertClickListener actionConfirm, KAlertDialog.KAlertClickListener actionCancel, int type) {
        if (mCustomAlert == null) {
            mCustomAlert = new KAlertDialog(this);
            mCustomAlert.setCancelable(false);
            mCustomAlert.setCanceledOnTouchOutside(false);
        }

        mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));

        mCustomAlert.setTitleText(Html.fromHtml(title).toString());

        mCustomAlert.setContentText(Html.fromHtml(mess).toString());

        if (!TextUtils.isEmpty(titleButtonConfirm)) {
            mCustomAlert.setConfirmText(titleButtonConfirm);
        } else {
            mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));
        }

        switch (type) {
            case KAlertDialog.SUCCESS_TYPE:
                mCustomAlert.setCustomImage(R.drawable.ic_check);
                break;
            case KAlertDialog.WARNING_TYPE:
                mCustomAlert.setCustomImage(R.drawable.ic_check);
                break;
        }

        mCustomAlert.changeAlertType(KAlertDialog.CUSTOM_IMAGE_TYPE);

        if (actionCancel != null) {
            mCustomAlert.setCancelClickListener(actionCancel);

            if (!TextUtils.isEmpty(titleButtonCancel)) {
                mCustomAlert.setCancelText(titleButtonCancel);
            } else {
                mCustomAlert.setCancelText(getString(R.string.KAlert_cancel_button_text));
            }
        } else {
            mCustomAlert.showCancelButton(false);
        }
        if (actionConfirm != null) {
            mCustomAlert.setConfirmClickListener(actionConfirm);
        } else {
            mCustomAlert.setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                @Override
                public void onClick(KAlertDialog kAlertDialog) {
                    mCustomAlert.dismiss();
                }
            });
        }
        mCustomAlert.show();
    }

    public void changeToActivitySelectImage() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getApplicationContext()), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, OPEN_MEDIA_PICKER_PERMISSION);
            } else {

                intentGallerySelectImage();
            }

        } else {
            intentGallerySelectImage();
        }
    }

    private File photoFile = null;

    public void captureImageFromCamera() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getApplicationContext()), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_CAMERA_PERMISSION_CODE);
            } else {

                intentCameraCaptureImage();
            }

        } else {
            intentCameraCaptureImage();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == OPEN_MEDIA_PICKER_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            intentGallerySelectImage();
        } else if (requestCode == MY_CAMERA_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//            Create the File where the photo should go
                try {
                    photoFile = createMediaFile();

                } catch (IOException ex) {
                    // Error occurred while creating the File
                    ex.printStackTrace();

                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (photoFile != null) {

                            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                            StrictMode.setVmPolicy(builder.build());

                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        } else {
                            showAlert(getString(R.string.error_load_file_image), KAlertDialog.ERROR_TYPE);
                        }
                    }
                }, 300);
                // Continue only if the File was successfully created

            }
        }
    }

    private PermissionUtils.PermissionResultCallback permissionResultCallback = new PermissionUtils.PermissionResultCallback() {

        @Override
        public void PermissionGranted(int request_code) {

            Intent intent = new Intent(HomeActivity.this, Gallery.class);
            // Set the title
            intent.putExtra("title", "Chọn hình ảnh");
            // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
            intent.putExtra("mode", 2);
            intent.putExtra("maxSelection", 1); // Optional
            startActivityForResult(intent, OPEN_MEDIA_PICKER);

        }

        @Override
        public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {

        }

        @Override
        public void PermissionDenied(int request_code) {

        }

        @Override
        public void NeverAskAgain(int request_code) {

        }
    };

    private void intentGallerySelectImage() {
        Intent intent = new Intent(HomeActivity.this, Gallery.class);
        // Set the title
        intent.putExtra("title", "Chọn hình ảnh");
        // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
        intent.putExtra("mode", 2);
        intent.putExtra("maxSelection", 1); // Optional
        startActivityForResult(intent, OPEN_MEDIA_PICKER);
    }

    private void intentCameraCaptureImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//            Create the File where the photo should go
            try {
                photoFile = createMediaFile();

            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                showAlert(getString(R.string.error_load_file_image), KAlertDialog.ERROR_TYPE);
            }
        } else {
            showAlert("Camera không khả dụng.", KAlertDialog.ERROR_TYPE);
        }
    }

    private File createMediaFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile = null;

        String rootPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/529FTN_Images/";
        File root = new File(rootPath);
        if (!root.exists()) {
            root.mkdirs();
        }
//        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String fileName = "JPEG_" + timeStamp + "_";
        mediaFile = File.createTempFile(fileName,  // prefix
                ".jpg",         // suffix
                root      // directory
        );

        return mediaFile;
    }

    public void deleteTempMedia() {
        String rootPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/529FTN_Images/";
        File root = new File(rootPath);
        if (root.exists()) {
            String[] children = root.list();
            if (children != null && children.length > 0) {
                for (int i = 0; i < children.length; i++) {
                    new File(root, children[i]).delete();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == OPEN_MEDIA_PICKER) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> selectionResult = data.getStringArrayListExtra("result");
                if (selectionResult != null && selectionResult.size() > 0) {
                    BaseFragment fragment = getCurrentFragment();
                    if (fragment instanceof FragmentCategoryProductDetail) {
                        ((FragmentCategoryProductDetail) fragment).setImageSelected(selectionResult.get(0));
                    }
                    if (fragment instanceof FragmentCreateProductCategory) {
                        ((FragmentCreateProductCategory) fragment).setImageSelected(selectionResult.get(0));
                    }
                    if (fragment instanceof FragmentLevelCustomerDetail) {
                        ((FragmentLevelCustomerDetail) fragment).setImageSelected(selectionResult.get(0));
                    }
                    if (fragment instanceof FragmentCreateLevelCustomer) {
                        ((FragmentCreateLevelCustomer) fragment).setImageSelected(selectionResult.get(0));
                    }
                    if (fragment instanceof FragmentProductListDetail) {
                        ((FragmentProductListDetail) fragment).setImageSelected(selectionResult.get(0));
                    }
                }
            }
        } else if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                BaseFragment fragment = getCurrentFragment();

                if (fragment instanceof FragmentCategoryProductDetail) {
                    if (photoFile != null)
                        ((FragmentCategoryProductDetail) fragment).setImageSelected(photoFile.getAbsolutePath());
                }
                if (fragment instanceof FragmentCreateProductCategory) {
                    if (photoFile != null)
                        ((FragmentCreateProductCategory) fragment).setImageSelected(photoFile.getAbsolutePath());
                }
                if (fragment instanceof FragmentLevelCustomerDetail) {
                    if (photoFile != null)
                        ((FragmentLevelCustomerDetail) fragment).setImageSelected(photoFile.getAbsolutePath());
                }
                if (fragment instanceof FragmentCreateLevelCustomer) {
                    if (photoFile != null)
                        ((FragmentCreateLevelCustomer) fragment).setImageSelected(photoFile.getAbsolutePath());
                }
                if (fragment instanceof FragmentProductListDetail) {
                    if (photoFile != null)
                        ((FragmentProductListDetail) fragment).setImageSelected(photoFile.getAbsolutePath());
                }
            }
        } else if (requestCode == EMAIL_SEND) {
            if (resultCode == RESULT_OK || resultCode == RESULT_CANCELED) {
                BaseFragment fragment = getCurrentFragment();

//                if (fragment instanceof FragmentStatictisReportDetail) {
//                    ((FragmentStatictisReportDetail) fragment).deleteTempMedia();
//                }
            }
        }
    }
}