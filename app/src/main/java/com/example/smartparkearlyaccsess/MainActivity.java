package com.example.smartparkearlyaccsess;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button  tombolLogin;
    private EditText inputEmail, inputPassword;
    private AppDatabase db;
    private TextView tombolDaftar;

    //Menjalankan method Insert Data
    @SuppressLint("StaticFieldLeak")
    private void prosesLogin(final String email, final String password){
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return db.userDao().findByEmail(email);

            }

            @Override
            protected void onPostExecute(User user) {
                //Menandakan bahwa data berhasil disimpan
                if (user == null) {
                    Toast.makeText(MainActivity.this, "Email: " + email +
                            " belum terdaftar", Toast.LENGTH_LONG).show();
                }

                else if (user.email.equals(email) && !user.password.equals(password)) {
                    Toast.makeText(MainActivity.this, "Password yang anda masukkan salah",
                            Toast.LENGTH_LONG).show();
                }

                else {
                    Context context = getApplicationContext();
                    SharedPreferences sharedPref = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.putBoolean("login", true);
                    editor.commit();
                    Intent intentHome = new Intent(MainActivity.this, HomeActivity.class);
                    intentHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intentHome);
                }

            }
        }.execute();
    }

    private void cekLogin() {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        boolean login = sharedPref.getBoolean("login", false);

        if (login) {
            Intent intentHome = new Intent(MainActivity.this, HomeActivity.class);
            intentHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentHome);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tombolLogin = findViewById(R.id.tombolLogin);
        tombolDaftar = findViewById(R.id.tombolDaftar);

        inputEmail= findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();

        tombolLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                prosesLogin(email, password);
            }
        });

        tombolDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentDaftar = new Intent(MainActivity.this, DaftarActivity.class);
                intentDaftar.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentDaftar);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cekLogin();
    }
}