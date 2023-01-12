package com.example.smartparkearlyaccsess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Konfirmasi extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    TextView nama, alamat, plat, merk, tipe, jenis;
    String harga;
    Button submit;
    public static Konfirmasi ma;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        dbHelper = new DataHelper(this);

        nama = (TextView) findViewById(R.id.namaparkir);
        alamat= (TextView) findViewById(R.id.alamatparkir);
        plat= (TextView) findViewById(R.id.platnomor);
        merk= (TextView) findViewById(R.id.kendaraanmerk);
        tipe= (TextView) findViewById(R.id.kendaraantipe);
        jenis= (TextView) findViewById(R.id.jenis);
        submit= (Button) findViewById(R.id.kirim);

        Intent i = getIntent();
        String platis = i.getStringExtra("noplat");
        String merkis = i.getStringExtra("merk");
        String tipeis = i.getStringExtra("tipe");
        String jenisis = i.getStringExtra("jenis");
        String namais = i.getStringExtra("nama");
        String alamatis = i.getStringExtra("alamat");

        nama.setText(namais);
        alamat.setText(alamatis);
        plat.setText(platis);
        merk.setText(merkis);
        tipe.setText(tipeis);
        jenis.setText(jenisis);

        if (jenis.equals("Motor")){
            harga = "2000";
        }else{
            harga = "5000";
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db =
                        dbHelper.getWritableDatabase();
                db.execSQL("insert into transaksi(noplat, merk , tipe, jenis, nama, alamatpk, harga) values('" +
                        platis+"','"+
                        merkis +"','" +
                        tipeis+"','"+
                        jenisis+"','"+
                        namais+"','"+
                        alamatis+"','"+
                        harga+ "')");
                Toast.makeText(getApplicationContext(), "Berhasil Mendaftar", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}