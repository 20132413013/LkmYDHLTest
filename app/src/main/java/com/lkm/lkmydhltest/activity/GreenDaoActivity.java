package com.lkm.lkmydhltest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lkm.lkmydhltest.MyApplication;
import com.lkm.lkmydhltest.R;
import com.lkm.lkmydhltest.entity.Note;

import org.litepal.LitePalApplication;

import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        initView();
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Note note = new Note();
                note.setText("note");
                note.setDes("notetest");
                ((MyApplication) getApplication()).getDaoSession().getNoteDao().insert(note);
                Toast.makeText(this, "增加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:

                break;
            case R.id.button3:

                break;
            case R.id.button4:
                List<Note> notes = ((MyApplication) getApplication()).getDaoSession().getNoteDao().loadAll();
                Toast.makeText(this, "hh" + notes, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}