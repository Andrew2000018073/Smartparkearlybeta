package com.example.smartparkearlyaccsess;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DaftarActivity extends AppCompatActivity {

    TextView tombolKembali;
    Button tombolDaftar;
    EditText inputEmail, inputNama, inputPassword;
    AppDatabase db;

    //Menjalankan method Insert Data
    @SuppressLint("StaticFieldLeak")
    private void insertData(final User user){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                //Menjalankan proses insert data
                db.userDao().insertUser(user);
                return "berhasil";
            }

            @Override
            protected void onPostExecute(String status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(DaftarActivity.this,
                        "Akun dengan email " + user.email + " berhasil dibuat silahkan login menggunakan " +
                                "Akun yang telah dibuat",
                        Toast.LENGTH_LONG).show();
                Intent intentMain = new Intent(DaftarActivity.this, MainActivity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentMain);
            }
        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
        tombolKembali = findViewById(R.id.kembali);
        tombolDaftar = findViewById(R.id.Daftar);
        inputEmail = findViewById(R.id.inemail);
        inputNama = findViewById(R.id.inusername);
        inputPassword = findViewById(R.id.inpassword);

        tombolKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(DaftarActivity.this, MainActivity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentMain);
            }
        });

        tombolDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User();
                user.email = inputEmail.getText().toString();
                user.nama = inputNama.getText().toString();
                user.password = inputPassword.getText().toString();

                if (user.nama.trim().equals("") || user.nama.trim().equals("") ||
                        user.password.trim().equals("")) {
                    Toast.makeText(DaftarActivity.this, "datanya diisi dulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                insertData(user);
            }
        });
    }
}