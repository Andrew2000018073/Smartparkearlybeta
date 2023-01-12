package com.example.smartparkearlyaccsess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PesanSekarang extends AppCompatActivity {

    String[] noplat;
    String[] merk;
    String[] tipe;
    String[] jenis;
    String[] nama;
    String[] alamat;
    ListView List;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbc;
    public static PesanSekarang ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_parkir);


        ma = this;
        dbc = new com.example.smartparkearlyaccsess.DataHelper(this);
        RefreshList();


    }

    public void RefreshList(){

        Intent i = getIntent();
        String data1 = i.getStringExtra("nama");
        String data2 = i.getStringExtra("alamat");


        SQLiteDatabase db = dbc.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kendaraan",null);
        noplat = new String[cursor.getCount()];
        merk = new String[cursor.getCount()];
        tipe = new String[cursor.getCount()];
        jenis = new String[cursor.getCount()];
        nama = new String[cursor.getCount()];
        alamat = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            noplat[cc] = cursor.getString(0).toString();
            merk[cc] = cursor.getString(1).toString();
            tipe[cc] = cursor.getString(2).toString();
            jenis[cc] = cursor.getString(3).toString();
            nama[cc]= data1;
            alamat[cc]= data2;
        }

        List = (ListView)findViewById(R.id.listparkir);
        List.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, tipe));
        List.setSelected(true);
        List.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView arg0, View arg1,
                                    int arg2, long arg3) {
                final String selection = noplat[arg2];
                final String seleksi = merk[arg2];
                final String selectit = tipe[arg2];
                final String selek = jenis[arg2];
                final String kirim = nama[arg2];
                final String send = alamat[arg2];
//.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lanjut Pemesanan"};
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(PesanSekarang.this);
                builder.setTitle("Silahkan Konfirmasi");
                builder.setItems(dialogitem, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    item) {
                                switch(item){
                                    case 0 :
                                        Intent i = new
                                                Intent(getApplicationContext(), Konfirmasi.class);
                                        i.putExtra("noplat", selection);
                                        i.putExtra("merk", seleksi);
                                        i.putExtra("tipe", selectit);
                                        i.putExtra("jenis", selek);
                                        i.putExtra("nama", kirim);
                                        i.putExtra("alamat", send);
                                        startActivity(i);
                                        RefreshList();
                                        break;
                                }
                            }
                        });

                builder.create().show();
            }});
        ((ArrayAdapter)List.getAdapter()).notifyDataSetInvalidated();
    }


}