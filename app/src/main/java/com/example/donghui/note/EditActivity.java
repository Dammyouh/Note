package com.example.donghui.note;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private static final String TAG ="EditActivity" ;
    private EditText etName, etContent;
    private  Button btn1;
    private IMyAidlInterface serviceBinder ;
    private  ServiceConnection mconnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serviceBinder = IMyAidlInterface.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
             serviceBinder = null;
        }
    };
        @Override
        protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btn1 = (Button) findViewById(R.id.baocun);
        etName = (EditText) findViewById(R.id.Notename);
        etContent = (EditText) findViewById(R.id.Notecontent);

        //bind to the service
            // Intent bindIntent = new Intent(this, MainService.class);
            Intent bindIntent = new Intent();
            bindIntent.setClass(EditActivity.this,MainService.class);
            bindService(bindIntent,mconnection,Context.BIND_AUTO_CREATE);

        btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(EditActivity.this, MainActivity.class);
                    if ((etName.getText().toString().equals("")) || (etContent.getText().toString().equals(""))) {
                        Toast.makeText(EditActivity.this, "请输入Note标题与Ｎote内容！", Toast.LENGTH_SHORT).show();
                    } else if(serviceBinder !=null){
                        Toast.makeText(EditActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                      try{
                          serviceBinder.saveNote(serviceBinder.doInsert(etName.getText().toString(),etContent.getText().toString()));
                      }
                        catch (Exception e){
                          Toast.makeText(EditActivity.this,"There is a exception",Toast.LENGTH_SHORT).show();
                        }
                        //Log.d(TAG,"hfwgfg");
                        startActivity(intent);
                    }
                }
            });

    }
}


