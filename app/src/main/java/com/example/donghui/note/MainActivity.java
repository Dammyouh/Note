package com.example.donghui.note;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    public static final String TAG =".this";
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btn1 =(Button)findViewById(R.id.button);
         btn2 =(Button)findViewById(R.id.button1);
         btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent();
                it.setClass(MainActivity.this,EditActivity.class );
                startActivity(it);
                Log.d(TAG, "have been done!");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent();
                //Log.d(TAG, "have been done!");
                it.setClass(MainActivity.this,NoteListActivity.class );
                startActivity(it);
               // Log.d(TAG,"this is the error");
            }
        });
    }
}
