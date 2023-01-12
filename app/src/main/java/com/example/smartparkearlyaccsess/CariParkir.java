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
import android.widget.Button;
import android.widget.ListView;

public class CariParkir extends AppCompatActivity {

    String[] namapk, alamatpk;
    ListView List;
    Menu menu;
    Button maps;
    protected Cursor cursor;
    DataHelper dbc;
    public static CariParkir ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_parkir);

        ma = this;
        dbc = new com.example.smartparkearlyaccsess.DataHelper(this);
        RefreshList();

        maps = findViewById(R.id.lewatpeta);

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(CariParkir.this,MapsActivity.class);
                CariParkir.this.startActivity(explicit);
            }
        });
    }

    public void RefreshList(){


        SQLiteDatabase db = dbc.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM parkir",null);
        namapk = new String[cursor.getCount()];
        alamatpk = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            namapk[cc] = cursor.getString(0).toString();
            alamatpk[cc] = cursor.getString(1).toString();
        }
        List = (ListView)findViewById(R.id.listparkir);
        List.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, namapk));
//        List.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, alamat));
        List.setSelected(true);
        List.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView arg0, View arg1,
                                    int arg2, long arg3) {
                final String selection = namapk[arg2];
                final String seleksi = alamatpk[arg2];
//.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Pesan Sekarang"};
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(CariParkir.this);
                builder.setTitle("Lanjut");
                builder.setItems(dialogitem, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    item) {
                                switch(item){
                                    case 0 :
                                        Intent i = new
                                                Intent(getApplicationContext(), PesanSekarang.class);
                                        i.putExtra("nama", selection);
                                        i.putExtra("alamat", seleksi);
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