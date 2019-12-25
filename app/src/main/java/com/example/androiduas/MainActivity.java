package com.example.androiduas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText xkode;
    EditText xnama;
    EditText xtgl;
    EditText xwaktu;
    EditText xtempat;
    EditText xtujuan;
    EditText xketerangan;
    Button tblAdd;
    Button tblView;
    Button btnviewUpdate;
    Button btndelete;
    DatabaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BantuDb=new DatabaseHelper(this);
        xkode=(EditText)findViewById(R.id.xkode);
        xnama=(EditText)findViewById(R.id.xnama);
        xtgl=(EditText)findViewById(R.id.xtgl);
        xwaktu=(EditText)findViewById(R.id.xwaktu);
        xtempat=(EditText)findViewById(R.id.xtempat);
        xtujuan=(EditText)findViewById(R.id.xtujuan);
        xketerangan=(EditText)findViewById(R.id.xketerangan);
        tblAdd=(Button)findViewById(R.id.tblAdd);
        tblView=(Button)findViewById(R.id.tblView);
        btnviewUpdate=(Button)findViewById(R.id.btn_update);
        btndelete=(Button)findViewById(R.id.btn_delete);
        viewAll();
        DeleteData();
        UpdateData();
        tblAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted =  BantuDb.insertData(xkode.getText().toString(),xnama.getText().toString(), xtgl.getText().toString(), xwaktu.getText().toString(), xtempat.getText().toString(), xtujuan.getText().toString(), xketerangan.getText().toString());
                if(isInserted == true)
                    Toast.makeText(MainActivity.this,"Data Terimpan",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Gagal Tersimpan", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewAll() {
        tblView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = BantuDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Noting Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Kode       :" + res.getString(0) + "\n");
                            buffer.append("Nama       :" + res.getString(1) + "\n");
                            buffer.append("Tgl        :" + res.getString(2) + "\n");
                            buffer.append("Waktu      :" + res.getString(3) + "\n");
                            buffer.append("Tempat     :" + res.getString(4) + "\n");
                            buffer.append("Tujuan     :" + res.getString(5) + "\n");
                            buffer.append("Keterangan :" + res.getString(6) + "\n");

                        }
                        // show all data
                        showMessage("Jadwal Kegiatan :", buffer.toString());
                    }
                });
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void DeleteData(){
        btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Integer deleteRows = BantuDb.deleteData(xkode.getText().toString());
                        if(deleteRows>0)
                            Toast.makeText(MainActivity.this, "Data Terhapus", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Gagal Menghapus", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate=BantuDb.updateData(xkode.getText().toString(),xnama.getText().toString(), xtgl.getText().toString(), xwaktu.getText().toString(), xtempat.getText().toString(), xtujuan.getText().toString(), xketerangan.getText().toString());
                        if(isUpdate==true)
                            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Gagal Update", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
