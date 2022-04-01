package com.example.pr15;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pr15.Note.Note;
import com.example.pr15.adapters.Adapter;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    protected Adapter adapter;
    protected MyApp myApp;
    Button btnAdd;
    private final int btnClickCode = 0;
    private final int itemClickCode = 2;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        myApp = (MyApp)getApplicationContext();
        List<Note> lv = myApp.getNotes();
        list = findViewById(R.id.list);
        adapter = new Adapter(lv);
        list.setAdapter(adapter);
        btnAdd =findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        list.setOnItemClickListener(this);
    }
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, SecActivity.class);
        myApp.setCode(-1);
        startActivityForResult(intent, btnClickCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            list.invalidateViews();
            btnAdd.setEnabled(adapter.getCount() < 3);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, SecActivity.class);
        myApp.setCode(i);
        startActivityForResult(intent, itemClickCode);

    }
}