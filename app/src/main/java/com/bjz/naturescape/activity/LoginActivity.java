package com.bjz.naturescape.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bjz.naturescape.R;
import com.bjz.naturescape.component.ComponentApplication;
import com.bjz.naturescape.model.login.LoginRequest;
import com.bjz.naturescape.service.LoginService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    @Inject
    Retrofit retrofit;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((ComponentApplication) getApplication()).getmNetComponent().inject(LoginActivity.this);
        initLoginButton();
        loginService = retrofit.create(LoginService.class);
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
        EditText username = (EditText) findViewById(R.id.login_user_name_field);
        EditText password = (EditText) findViewById(R.id.login_user_password_field);

        String usernameParsed = username.getText().toString();
        String passwordParsed = password.getText().toString();
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

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
