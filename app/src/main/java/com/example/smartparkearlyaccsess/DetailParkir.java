package com.example.smartparkearlyaccsess;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetailParkir extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView nomor, nama, alamat, plat, merk, tipe, jenis, harga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parkir);

        nomor= (TextView) findViewById(R.id.nomorparkir);
        nama = (TextView) findViewById(R.id.namaparkir);
        alamat= (TextView) findViewById(R.id.alamatparkir);
        plat= (TextView) findViewById(R.id.platnomor);
        merk= (TextView) findViewById(R.id.kendaraanmerk);
        tipe= (TextView) findViewById(R.id.kendaraantipe);
        jenis= (TextView) findViewById(R.id.jenis);
        harga= (TextView) findViewById(R.id.harga);

        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM transaksi WHERE noplat= '" + getIntent().getStringExtra("noplat") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            nomor.setText((cursor.getString(0).toString()));
            plat.setText(cursor.getString(1).toString());
            merk.setText(cursor.getString(2).toString());
            tipe.setText(cursor.getString(3).toString());
            jenis.setText(cursor.getString(4).toString());
            nama.setText(cursor.getString(5).toString());
            alamat.setText(cursor.getString(6).toString());
            harga.setText(cursor.getString(7).toString());
        }

    }
}