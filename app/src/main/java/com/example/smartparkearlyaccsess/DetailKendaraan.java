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


public class DetailKendaraan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button hapus;
    TextView nomorplat, merk, tipe, jenis;
    public static DetailKendaraan ma;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();
        String data = i.getStringExtra("tipe");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kendaraan);
        dbHelper = new DataHelper(this);
        nomorplat = (TextView) findViewById(R.id.nomorplat);
        merk = (TextView) findViewById(R.id.merk);
        tipe = (TextView) findViewById(R.id.type);
        jenis= (TextView) findViewById(R.id.jenis);
        hapus= (Button) findViewById(R.id.batal);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kendaraan WHERE tipe= '" + getIntent().getStringExtra("tipe") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nomorplat.setText(cursor.getString(0).toString());
            merk.setText(cursor.getString(1).toString());
            tipe.setText(cursor.getString(2).toString());
            jenis.setText(cursor.getString(3).toString());
        }


        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM kendaraan WHERE noplat = '" + data + "'");

                Toast.makeText(getApplicationContext(), "Berhasil Menghapus", Toast.LENGTH_LONG).show();

            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the main; this adds items to the action bar if
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}