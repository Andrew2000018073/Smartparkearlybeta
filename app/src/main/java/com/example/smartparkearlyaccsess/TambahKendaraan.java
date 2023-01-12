package com.example.smartparkearlyaccsess;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TambahKendaraan extends AppCompatActivity {


    protected Cursor cursor;
    DataHelper dbHelper;
    Button submit;
    EditText merk,model,plat;
    RadioButton motor,mobil;
    RadioGroup type;
    String tipe;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kendaraan);
        dbHelper = new DataHelper(this);
        merk = (EditText) findViewById(R.id.merk);
        submit = (Button) findViewById(R.id.submit);
        model = (EditText) findViewById(R.id.model);
        plat = (EditText) findViewById(R.id.plat);
        motor = (RadioButton) findViewById(R.id.btnmotor);

        if(motor.isChecked()){
           tipe = "Motor";
        }
        if(mobil.isChecked()){
            tipe = "Mobil";
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db =
                        dbHelper.getWritableDatabase();
                db.execSQL("insert into kendaraan( noplat , merk, tipe , jenis ) values('" +
                        plat.getText().toString()+"','"+
                        merk.getText().toString() +"','" +
                        model.getText().toString()+"','"+
                       tipe+ "')");
                Toast.makeText(getApplicationContext(), "Berhasil Mendaftar", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}