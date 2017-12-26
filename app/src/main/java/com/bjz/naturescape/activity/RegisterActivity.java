package com.bjz.naturescape.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bjz.naturescape.R;

public class RegisterActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView confirmPasswordTextView;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameTextView = findViewById(R.id.register_name);
        emailTextView = findViewById(R.id.register_email);
        passwordTextView = findViewById(R.id.register_password);
        confirmPasswordTextView = findViewById(R.id.register_password_confirm);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
    }

    private void doRegister() {

    }
}
