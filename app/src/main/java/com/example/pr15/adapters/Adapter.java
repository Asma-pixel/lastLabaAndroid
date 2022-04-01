package com.example.pr15.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pr15.Note.Note;
import com.example.pr15.R;

import java.util.List;


public class Adapter extends BaseAdapter {
    private List<Note> lv;

    public Adapter(List<Note> lv) {
        this.lv = lv;
    }

    @Override
    public int getCount() {
        return lv.size();
    }

    @Override
    public Object getItem(int i) {
        return lv.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context con = viewGroup.getContext();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tv, viewGroup, false);
        }
        TextView textView = view.findViewById(R.id.textView);
        TextView header = view.findViewById(R.id.header);
        TextView time = view.findViewById(R.id.time);
        textView.setText(lv.get(i).getContent());
        header.setText(lv.get(i).getHeader());
        time.setText(lv.get(i).getTime().toString());
        return view;

    }
}
