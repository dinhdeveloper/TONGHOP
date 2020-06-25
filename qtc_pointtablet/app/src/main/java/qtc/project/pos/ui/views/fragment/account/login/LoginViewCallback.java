
package qtc.project.pos.ui.views.fragment.account.login;

import com.facebook.AccessToken;

public interface LoginViewCallback
{
    void onClickLogin(String username, String password, boolean isLoginAdmin);

    void onClickForgotPassword();

    void onClickShowRegister();

    void onClickBackHeader();

    void loginFacebookSuccess(AccessToken accessToken);

    void onLoginWithGoogle();
}
