package com.booleanautocrats.road_naksha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AdminLoginActivity extends AppCompatActivity {
    String email,password;
    EditText emailET,passET;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.admin_login_activity);
        emailET=findViewById(R.id.email);
        passET=findViewById(R.id.password);

        signin=findViewById(R.id.signInBtn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=emailET.getText().toString();
                password=passET.getText().toString();
                if(email.equals("government@gmail.com") && password.equals("demo")){
                    Intent in=new Intent(AdminLoginActivity.this, adminMainActivity.class);
                    startActivity(in);
                    finish();
                }
                else{
                    Toast.makeText(AdminLoginActivity.this,"Incorrect email ID or password",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
