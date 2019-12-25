package com.example.androiduas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="uas.db";
    public static final String TABLE_NAME="kegiatan";
    public static final String COL_1="kode";
    public static final String COL_2="nama";
    public static final String COL_3="tgl";
    public static final String COL_4="waktu";
    public static final String COL_5="tempat";
    public static final String COL_6="tujuan";
    public static final String COL_7="keterangan";
    public static final int DATABASE_VERTION=1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERTION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table kegiatan(kode text null, nama text null, tgl text null, waktu text null, tempat text null, tujuan text null, keterangan text null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    //metode untuk tambah data
    public boolean insertData(String kode, String nama, String tgl, String waktu, String tempat, String tujuan, String keterangan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, kode);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, tgl);
        contentValues.put(COL_4, waktu);
        contentValues.put(COL_5, tempat);
        contentValues.put(COL_6, tujuan);
        contentValues.put(COL_7, keterangan);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }


    //metode untuk mengambil data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from kegiatan", null);
        return res;
    }

    //metode untuk merubah data
    public boolean updateData(String kode, String nama, String tgl, String waktu, String tempat, String tujuan, String keterangan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, kode);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, tgl);
        contentValues.put(COL_4, waktu);
        contentValues.put(COL_5, tempat);
        contentValues.put(COL_6, tujuan);
        contentValues.put(COL_7, keterangan);

        db.update(TABLE_NAME, contentValues, "kode = ?", new String[]{kode});
        return true;
    }


    //metode untuk menghapus data
    public int deleteData(String kode) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "kode = ?", new String[]{kode});
    }
}
