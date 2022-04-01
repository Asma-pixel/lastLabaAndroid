package com.example.pr15;

import android.app.Application;
import android.widget.Toast;

import com.example.pr15.Note.Note;
import com.example.pr15.helpers.ToastHelper;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    private final List<Note> notes = new ArrayList<>();
    private  int code = -1;
    public MyApp() {

        notes.add(new Note("Заголовок","контент", new Time(System.currentTimeMillis())));
    }
    public List<Note> getNotes() {
        return notes;
    }

    public void addNote (Note s) {
        notes.add(s);
        ToastHelper toast = new ToastHelper(getApplicationContext());
        toast.show(getString(R.string.add));
    }
    public  void setNote (int i, Note s) {
        notes.set(i, s);
        ToastHelper toast = new ToastHelper(getApplicationContext());
        toast.show(getString(R.string.edit));

    }
    public void setCode (int i) {
        this.code = i;
    }
    public int getCode () {
        return this.code;
    }

}
