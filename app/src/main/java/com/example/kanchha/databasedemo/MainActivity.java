package com.example.kanchha.databasedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView name, roll;
    EditText nameT, rollT, idT;
    Button send, reset;
    ListView lv;
    public static MainActivity obj;
    DatabaseAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameT = (EditText) findViewById(R.id.nameText);
        rollT = (EditText) findViewById(R.id.rollText);
        idT = (EditText) findViewById(R.id.ids);
        send = (Button)findViewById(R.id.btnSend);
        reset = (Button)findViewById(R.id.btnReset);
        lv = (ListView)findViewById(R.id.listview);
        obj  = this;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });

        dbAdapter = new DatabaseAdapter(this);
        displayListView();
    }

    public void saveToDatabase() {
        ContentValues cv = new ContentValues();
        long id = 0;
        if(idT.getText().toString().equals("0")){
            cv.put(DBConstant.ST_NAME, nameT.getText().toString());
            cv.put(DBConstant.ST_ROLL, rollT.getText().toString());
            id = dbAdapter.insertData(cv);
            showToast("Data Inserted");
        }else{
            cv.put(DBConstant.ST_NAME, nameT.getText().toString());
            cv.put(DBConstant.ST_ROLL, rollT.getText().toString());
            id = dbAdapter.updateRowById(idT.getText().toString(), cv);
            showToast("Row Updated");
        }
        if(id!=-1){
            nameT.setText("");
            rollT.setText("");
            idT.setText("0");

            displayListView();
        }
    }

    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public  void resetAll() {
        idT.setText("0");
        nameT.setText("");
        rollT.setText("");
    }

    public void displayListView(){
        Cursor c = dbAdapter.getAllData();
        int [] ids = new int[c.getCount()];
        String [] names= new String[c.getCount()];
        String [] rolls = new String[c.getCount()];
        int i = 0;
        while(c.moveToNext()){
            ids[i] = c.getInt(c.getColumnIndex(DBConstant._ID));
            names[i] = c.getString(c.getColumnIndex(DBConstant.ST_NAME));
            rolls[i] = c.getString(c.getColumnIndex(DBConstant.ST_ROLL));
            i++;
        }
        CustomDatabaseAdapter custmAdapter = new CustomDatabaseAdapter(this, R.layout.activity, ids, rolls, names);
        lv.setAdapter(custmAdapter);
    }
}