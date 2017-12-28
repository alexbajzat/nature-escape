package com.bjz.naturescape.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bjz.naturescape.R;
import com.bjz.naturescape.component.ComponentApplication;
import com.bjz.naturescape.model.request.LoginRequest;
import com.bjz.naturescape.service.LoginService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    @Inject
    Retrofit retrofit;
    private LoginService loginService;
    @BindView(R.id.login_user_name_field)
    EditText username;
    @BindView(R.id.login_user_password_field)
    EditText password;
    @BindView(R.id.keep_logged_check)
    CheckBox keepLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ((ComponentApplication) getApplication()).getmNetComponent().inject(LoginActivity.this);
        initLoginButton();
        loginService = retrofit.create(LoginService.class);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        if (preferences.contains("username") && preferences.contains("password")) {
            username.setText(preferences.getString("username", null));
            password.setText(preferences.getString("password", null));
            doLogin();
        }

    }

    private void initLoginButton() {
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    private void doLogin() {

        String usernameParsed = username.getText().toString();
        String passwordParsed = password.getText().toString();

        //TODO remove this, dev scope
        usernameParsed = "mock";
        passwordParsed = "mockpass";

        if (usernameParsed.isEmpty() || passwordParsed.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_LONG).show();
        }
        Log.d(LoginActivity.class.toString(), " MAKING LOGIN REQUEST");

        Call<Void> call = loginService.loginAccount(new LoginRequest()
                .setUsername(usernameParsed)
                .setPassword(passwordParsed));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
                    cacheUsernameAndPassword();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            goToHome();
                        }
                    }, 2000);
                    return;
                }
                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Request failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void cacheUsernameAndPassword() {
        if (keepLogged.isChecked()) {
            SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
            editor.putString("username", username.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.apply();
        }
    }

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
