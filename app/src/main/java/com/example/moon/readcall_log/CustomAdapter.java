package com.example.moon.readcall_log;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Calls_data> arrayList;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context context, ArrayList<Calls_data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.custom_adp_layout,null);
        TextView tvNumber,tvDuration,tvType,tvDate;
        tvNumber = (TextView)view.findViewById(R.id.tv_number);
        tvDuration = (TextView)view.findViewById(R.id.tv_duration);
        tvDate = (TextView)view.findViewById(R.id.tv_date);
        tvType = (TextView)view.findViewById(R.id.tv_type);

        tvNumber.setText(arrayList.get(position).getNumber());
        tvDuration.setText(arrayList.get(position).getDuration());
        tvDate.setText(arrayList.get(position).getDate().toString());
        tvType.setText(arrayList.get(position).getType());

        return view;
    }
}
