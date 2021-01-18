package com.booleanautocrats.road_naksha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class adminMainActivity extends AppCompatActivity {
    Button nagarA;
    Button nagarB;
    Button nagarC;
    Button nagarD;
    Button nagarE;
    Button nagarF;
    Button nagarG;
    Button nagarH;
    String nagarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.admin_main_activity);
        nagarA=findViewById(R.id.nagarA);
        nagarB=findViewById(R.id.nagarB);
        nagarC=findViewById(R.id.nagarC);
        nagarD=findViewById(R.id.nagarD);
        nagarE=findViewById(R.id.nagarE);
        nagarF=findViewById(R.id.nagarF);
        nagarG=findViewById(R.id.nagarG);
        nagarH=findViewById(R.id.nagarH);

        nagarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagarName=nagarA.getText().toString();
                Intent in=new Intent(adminMainActivity.this, adimFeedActivity.class);
                in.putExtra("nagarName",nagarName);
                startActivity(in);
            }
        });
        nagarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagarName=nagarB.getText().toString();
                Intent in=new Intent(adminMainActivity.this, adimFeedActivity.class);
                in.putExtra("nagarName",nagarName);
                startActivity(in);
            }
        });
        nagarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagarName=nagarC.getText().toString();
                Intent in=new Intent(adminMainActivity.this, adimFeedActivity.class);
                in.putExtra("nagarName",nagarName);
                startActivity(in);
            }
        });
        nagarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagarName=nagarD.getText().toString();
                Intent in=new Intent(adminMainActivity.this, adimFeedActivity.class);
                in.putExtra("nagarName",nagarName);
                startActivity(in);
            }
        });
        nagarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagarName=nagarE.getText().toString();
                Intent in=new Intent(adminMainActivity.this, adimFeedActivity.class);
                in.putExtra("nagarName",nagarName);
                startActivity(in);
            }
        });
        nagarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagarName=nagarF.getText().toString();
                Intent in=new Intent(adminMainActivity.this, adimFeedActivity.class);
                in.putExtra("nagarName",nagarName);
                startActivity(in);
            }
        });
        nagarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagarName=nagarG.getText().toString();
                Intent in=new Intent(adminMainActivity.this, adimFeedActivity.class);
                in.putExtra("nagarName",nagarName);
                startActivity(in);
            }
        });
        nagarH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagarName=nagarH.getText().toString();
                Intent in=new Intent(adminMainActivity.this, adimFeedActivity.class);
                in.putExtra("nagarName",nagarName);
                startActivity(in);
            }
        });

    }
}
