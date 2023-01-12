package com.example.smartparkearlyaccsess;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button tombolLogout;
    private Button cariparkir;
    private Button kendaraan;
    private Button liatkendaraan;
    private Button riwayat;

    private void cekLogin() {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        boolean login = sharedPref.getBoolean("login", false);

        if (!login) {
            Intent intentMain = new Intent(HomeActivity.this, MainActivity.class);
            intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentMain);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tombolLogout = findViewById(R.id.logout);
        cariparkir = findViewById(R.id.buttonpesan);
        kendaraan = findViewById(R.id.kendaraan);
        liatkendaraan = findViewById(R.id.seekendaraan);
        riwayat = findViewById(R.id.riwayat);

        cariparkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(HomeActivity.this,CariParkir.class);
                HomeActivity.this.startActivity(explicit);
            }
        });

        kendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(HomeActivity.this,TambahKendaraan.class);
                HomeActivity.this.startActivity(explicit);
            }
        });
        liatkendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(HomeActivity.this,LiatKendaraan.class);
                HomeActivity.this.startActivity(explicit);
            }
        });
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(HomeActivity.this,RiwayatParkir.class);
                HomeActivity.this.startActivity(explicit);
            }
        });
        tombolLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                SharedPreferences sharedPref = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.commit(); // commit changes
                Intent intentMain = new Intent(HomeActivity.this, MainActivity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentMain);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        cekLogin();
    }
}