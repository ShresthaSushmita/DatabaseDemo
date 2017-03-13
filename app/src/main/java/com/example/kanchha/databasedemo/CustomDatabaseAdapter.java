package com.example.kanchha.databasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kanchha on 3/4/2017.
 */
public class CustomDatabaseAdapter extends BaseAdapter {
    LayoutInflater inflater;
    DatabaseAdapter dbAdapter;
    int [] ids;
    String [] names, rolls;
    int res;
    public CustomDatabaseAdapter(Context c, int datacontent, int[] i, String[] r, String[] n) {
        ids = i;
        res = datacontent;
        names = n;
        rolls = r;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbAdapter = new DatabaseAdapter(c);
    }

    @Override
    public int getCount() {
        return ids.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity, null);
        }
        TextView tvName = (TextView)convertView.findViewById(R.id.listName);
        TextView tvRoll = (TextView)convertView.findViewById(R.id.listRoll);
        Button edit = (Button)convertView.findViewById(R.id.btnEdit);
        Button delete= (Button)convertView.findViewById(R.id.btnDelete);
        tvRoll.setText(rolls[position]);
        tvName.setText(names[position]);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.obj.nameT.setText(names[position]);
                MainActivity.obj.rollT.setText(rolls[position]);
                MainActivity.obj.idT.setText(ids[position]+"");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.deleteRowById(ids[position]+"");
                MainActivity.obj.displayListView();
            }
        });
        return convertView;
    }
}
