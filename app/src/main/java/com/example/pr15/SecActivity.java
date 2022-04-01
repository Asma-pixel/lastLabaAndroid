package com.example.pr15;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.pr15.Note.Note;

import java.sql.Date;
import java.sql.Struct;
import java.sql.Time;

public class SecActivity extends MainActivity implements TextWatcher {
    EditText header, content, date, time;
    Button btnOk, btnCancel, btnTime, btnDate;
    Spinner spinner;
    View dialog, dialog2;
    AlertDialog showBuilder, showBuilder2;
    private final String[] state = {"Выполнено", "Отложено", "Заброшено", "В планах"};
    private final Note note = new Note();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_act);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        header = findViewById(R.id.header);
        content = findViewById(R.id.content);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        btnTime = findViewById(R.id.btnTime);
        btnDate = findViewById(R.id.btnDate);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setEnabled(false);
        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        header.addTextChangedListener(this);
        content.addTextChangedListener(this);
        date.addTextChangedListener(this);
        time.addTextChangedListener(this);
        if(myApp.getCode() != -1) {
            content.setText(myApp.getNotes().get(myApp.getCode()).getContent());
            header.setText(myApp.getNotes().get(myApp.getCode()).getHeader());
            spinner.setSelection(myApp.getNotes().get(myApp.getCode()).getState());
            time.setText(myApp.getNotes().get(myApp.getCode()).getTime().toString());
            date.setText(myApp.getNotes().get(myApp.getCode()).getDate().toString());
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        note.setHeader(header.getText().toString());
        note.setContent(content.getText().toString());
        note.setState(spinner.getSelectedItemPosition());
        switch (view.getId()) {

            case R.id.btnOk: {
                if (myApp.getCode() == -1)
                    myApp.addNote(note);
                else  {
                    myApp.setNote(myApp.getCode(),note);
                }
                setResult(RESULT_OK);
                finish();
            }
            break;
            case R.id.btnCancel: {
                setResult(RESULT_CANCELED);
                finish();
            }
            break;
            case R.id.btnTime: {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                dialog = inflater.inflate(R.layout.time_picker, null);
                builder.setView(dialog);
                builder.create();
                showBuilder = builder.show();

            }break;
            case R.id.btnDate: {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                dialog2 = inflater.inflate(R.layout.date_picker, null);
                builder.setView(dialog2);
                builder.create();
                showBuilder2 = builder.show();

            }
            break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void timeChoose(View v) {
        TimePicker timeTable = dialog.findViewById(R.id.timePicker);
        note.setTime(new Time(timeTable.getHour(), timeTable.getMinute(), 0));
        time.setText(note.getTime().toString());
        showBuilder.dismiss();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void dateChoose(View v) {
        DatePicker dateTable = dialog2.findViewById(R.id.datePicker);
        note.setDate(new Date(dateTable.getYear(), dateTable.getMonth(), dateTable.getDayOfMonth()));
        date.setText(note.getDate().toString().substring(2));
        showBuilder2.dismiss();
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String hString = header.getText().toString().trim();
        String cString = content.getText().toString().trim();
        String tString = time.getText().toString().trim();
        String dString = date.getText().toString().trim();
        btnOk.setEnabled(hString.length() > 0 && cString.length() > 0 && tString.length() > 0 && dString.length() > 0);

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
