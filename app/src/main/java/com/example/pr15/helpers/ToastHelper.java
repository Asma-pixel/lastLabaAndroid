package com.example.pr15.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr15.R;

public class ToastHelper {
    Context context;
    public ToastHelper(Context context) {
        this.context = context;
    }

    public void show(String s) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_toast,null);
        TextView tv = layout.findViewById(R.id.toastText);
        tv.setText(s);
        Toast toast = new Toast(context);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
