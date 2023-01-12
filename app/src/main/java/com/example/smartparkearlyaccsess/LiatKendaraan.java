package com.example.smartparkearlyaccsess;

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

import androidx.appcompat.app.AppCompatActivity;


public class LiatKendaraan extends AppCompatActivity {

    String[] namak, alamat;
    ListView List;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbc;
    public static LiatKendaraan ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liat_kendaraan);
        ma = this;
        dbc = new com.example.smartparkearlyaccsess.DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {


        SQLiteDatabase db = dbc.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kendaraan", null);
        namak = new String[cursor.getCount()];
        alamat = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            namak[cc] = cursor.getString(2).toString();
        }
        List = (ListView) findViewById(R.id.daftark);
        List.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, namak));
        //List.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, alamat));
        List.setSelected(true);
        List.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView arg0, View arg1,
                                    int arg2, long arg3) {
                final String selection = namak[arg2];
//.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Detail"};
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(LiatKendaraan.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    item) {
                                switch(item){
                                    case 0 :
                                        Intent i = new
                                                Intent(getApplicationContext(), DetailKendaraan.class);
                                        i.putExtra("tipe", selection);

                                        startActivity(i);
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }});
        ((ArrayAdapter)List.getAdapter()).notifyDataSetInvalidated();
    }
}