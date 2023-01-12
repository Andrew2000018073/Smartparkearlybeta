package com.example.smartparkearlyaccsess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RSUD.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
// TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        String sql = "create table transaksi(noparkir integer primary key autoincrement, noplat text null, merk text null, tipe text null, jenis text null, nama text null, alamatpk text null, harga text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        String sqlot = "create table kendaraan(noplat text primary key , merk text null, tipe text null, jenis text null);";
        Log.d("Data", "onCreate: " + sqlot);
        db.execSQL(sqlot);



        String sqlite = "create table parkir(nama text primary key, alamatpk text null, chief text null);";
        Log.d("Data", "onCreate: " + sqlite);
        db.execSQL(sqlite);
        sqlite = "INSERT INTO parkir (nama, alamatpk, chief) " +
                "VALUES ('Tempat Parkir STTKD', '5968+VX4, Druwo, Bangunharjo, Kec. Sewon, Kabupaten Bantul, Daerah Istimewa Yogyakarta', 'Suparman');";
        db.execSQL(sqlite);

        sqlite = "INSERT INTO parkir (nama, alamatpk, chief) " +
                "VALUES ('Parkir Selatan UAD Kampus 4', '598J+CXX, Tamanan, Kec. Banguntapan, Kabupaten Bantul, Daerah Istimewa Yogyakarta 55191', 'Sukirman');";
        db.execSQL(sqlite);

        sqlite = "INSERT INTO parkir (nama, alamatpk, chief) " +
                "VALUES ('Kantong Parkir Beskalan', 'Jl. Beskalan No.28, RW.08, Ngupasan, Kec. Gondomanan, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55122', 'Sudirman');";
        db.execSQL(sqlite);

        sqlite = "INSERT INTO parkir (nama, alamatpk, chief) " +
                "VALUES ('Parkir Omah Dhuwur', '59FV+3PR, Jl. Nyi Pembayun, Bodon, Prenggan, Kec. Kotagede, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55172', 'Sukijan');";
        db.execSQL(sqlite);

        sqlite = "INSERT INTO parkir (nama, alamatpk, chief) " +
                "VALUES ('Parkiran Timur Galeria Mall', '698J+X23, Terban, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55223', 'Supriman');";
        db.execSQL(sqlite);

    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {
// TODO Auto-generated method stub
    }
}