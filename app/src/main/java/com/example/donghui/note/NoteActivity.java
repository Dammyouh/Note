package com.example.donghui.note;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import static com.example.donghui.note.MySQLiteDatabaseHelper.COLUMN_CONTENT;
import static com.example.donghui.note.MySQLiteDatabaseHelper.COLUMN_TITLE;
import static com.example.donghui.note.NoteListActivity.EXTRA_NOTEID;


public class NoteActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<String>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // get note position
        Intent intent = getIntent();
        position = intent.getIntExtra(EXTRA_NOTEID, 0);
        if (position == -1) {
            //notify user for error postion with Toast
            Toast.makeText(NoteActivity.this,"position is not right!",Toast.LENGTH_SHORT).show();
            return;
        }

        final EditText name = (EditText)findViewById(R.id.Notename);
        final EditText content = (EditText)findViewById(R.id.Notecontent);
        Button btn =(Button)findViewById(R.id.baocun);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent .setClass(getApplicationContext(),NoteListActivity.class );
                startActivity(intent);
                upNote(doUpdate(name.getText().toString(),content.getText().toString()));
            }

            private void upNote (ContentValues values) {
                getContentResolver().update(Uri.parse("content://com.example.donghui.note"), values,"id=?",new String[] { String.valueOf(position)});
            }
            private ContentValues doUpdate(String name,String content){
                ContentValues values = new ContentValues();
                values.put(COLUMN_TITLE,name);
                values.put(COLUMN_CONTENT,content);
                Toast.makeText(NoteActivity.this,"更新成功！",Toast.LENGTH_SHORT).show();
                return values;
            }
        });



        // get note details from content provider according to the position
        Context ctx = NoteActivity.this;
        ContentResolver resolver = ctx.getContentResolver();
        Uri uri = Uri.parse("content://com.example.donghui.note");
        Cursor c = resolver.query(uri, null,"id=?", new String[] { String.valueOf(position) }, null);

        if (c == null || !c.moveToFirst()) {
            return;
        }

        // get note data from cursor
        String namestr = c.getString(c.getColumnIndex(COLUMN_TITLE));
        String contentstr = c.getString(c.getColumnIndex(COLUMN_CONTENT));


        name.setText(namestr);
        content.setText(contentstr);
        btn.setText("更新Note");

    }


}
