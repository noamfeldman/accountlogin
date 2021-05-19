package com.borderfree.account.login.ui.login;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.borderfree.account.login.R;
import com.borderfree.account.login.data.AccountRestClient;
import com.borderfree.account.login.data.LoginRepository;
import com.borderfree.account.login.data.Result;
import com.borderfree.account.login.data.model.AccountLoginData;
import com.borderfree.account.login.data.model.LoggedInUser;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static android.util.Log.DEBUG;
import static android.util.Log.WARN;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private final LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginAccount(Context context, String email, String password) {
        Log.println(DEBUG, "loginAccount", String.format("Logging in user: %s", email));
        AccountRestClient.loginAccount(context, email, password, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                Gson g = new Gson();
                AccountLoginData accountLoginData = g.fromJson(jsonObject.toString(), AccountLoginData.class);
                loginResult.setValue(new LoginResult(new LoggedInUserView(accountLoginData.getData().getUser().getFirstName() + " "
                + accountLoginData.getData().getUser().getLastName())));
                Log.println(DEBUG, "loginAccount", String.format("User: %s logged in successfully, name: %s", email, accountLoginData.getData().getUser().getLastName()));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject jsonObject) {
                Log.println(WARN, "loginAccount", jsonObject.toString());
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}