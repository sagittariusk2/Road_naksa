package com.booleanautocrats.road_naksha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageView menuIcon;
    ImageView adminLogin;
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
        setContentView(R.layout.activity_main);
        menuIcon=findViewById(R.id.menuIcon);
        adminLogin=findViewById(R.id.adminLoginItem);
        nagarA=findViewById(R.id.nagarA);
        nagarB=findViewById(R.id.nagarB);
        nagarC=findViewById(R.id.nagarC);
        nagarD=findViewById(R.id.nagarD);
        nagarE=findViewById(R.id.nagarE);
        nagarF=findViewById(R.id.nagarF);
        nagarG=findViewById(R.id.nagarG);
        nagarH=findViewById(R.id.nagarH);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adminLogin.getVisibility()==View.VISIBLE)
                    adminLogin.setVisibility(View.INVISIBLE);
                else {
                    adminLogin.setVisibility(View.VISIBLE);
                    if(!isConnected(MainActivity.this)){
                        showCustomDialog();
                    }
                    else {
                        adminLogin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(MainActivity.this, AdminLoginActivity.class);
                                startActivity(in);
                                finish();
                            }
                        });
                    }
                }
            }

        });

        nagarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)){
                    showCustomDialog();
                }
                else {
                    nagarName = nagarA.getText().toString();
                    Intent in = new Intent(MainActivity.this, feedActivity.class);
                    in.putExtra("nagarName", nagarName);
                    startActivity(in);
                }
            }
        });
        nagarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)){
                    showCustomDialog();
                }
                else {
                    nagarName = nagarB.getText().toString();
                    Intent in = new Intent(MainActivity.this, feedActivity.class);
                    in.putExtra("nagarName", nagarName);
                    startActivity(in);
                }
            }
        });
        nagarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)){
                    showCustomDialog();
                }
                else {
                    nagarName = nagarC.getText().toString();
                    Intent in = new Intent(MainActivity.this, feedActivity.class);
                    in.putExtra("nagarName", nagarName);
                    startActivity(in);
                }
            }
        });
        nagarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)){
                    showCustomDialog();
                }
                else {
                    nagarName = nagarD.getText().toString();
                    Intent in = new Intent(MainActivity.this, feedActivity.class);
                    in.putExtra("nagarName", nagarName);
                    startActivity(in);
                }
            }
        });
        nagarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)){
                    showCustomDialog();
                }
                else {
                    nagarName = nagarE.getText().toString();
                    Intent in = new Intent(MainActivity.this, feedActivity.class);
                    in.putExtra("nagarName", nagarName);
                    startActivity(in);
                }
            }
        });
        nagarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)){
                    showCustomDialog();
                }
                else {
                    nagarName = nagarF.getText().toString();
                    Intent in = new Intent(MainActivity.this, feedActivity.class);
                    in.putExtra("nagarName", nagarName);
                    startActivity(in);
                }
            }
        });
        nagarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)){
                    showCustomDialog();
                }
                else {
                    nagarName = nagarG.getText().toString();
                    Intent in = new Intent(MainActivity.this, feedActivity.class);
                    in.putExtra("nagarName", nagarName);
                    startActivity(in);
                }
            }
        });
        nagarH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)){
                    showCustomDialog();
                }
                else {
                    nagarName = nagarH.getText().toString();
                    Intent in = new Intent(MainActivity.this, feedActivity.class);
                    in.putExtra("nagarName", nagarName);
                    startActivity(in);
                }
            }
        });
    }

    private boolean isConnected(MainActivity mainActivity) {
        ConnectivityManager connectivityManager=(ConnectivityManager)mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wifi!=null && wifi.isConnected()) ||(mobile!=null && mobile.isConnected())){
            return true;
        }
        else{
            return false;
        }
    }

    private void showCustomDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyle);

        builder.setMessage("Please connect to the internet to proceed further").setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));

    }

}