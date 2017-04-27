package com.example.donghui.note;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import static com.example.donghui.note.MySQLiteDatabaseHelper.COLUMN_TITLE;

public class NoteListActivity extends AppCompatActivity {

    public static final String EXTRA_NOTEID = "extra_noteid";
    private static final String TAG ="NoteListActiivty";
    //private SQLiteOpenHelper mDbHelper;
    private ListView mylistview;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mylistview = (ListView)findViewById(R.id.my_list_view);
        Cursor c = getContentResolver().query(Uri.parse("content://com.example.donghui.note"),null, null, null, null);
     //   Log.d(TAG,"this is the error?");
        if (c.moveToFirst()) {
            do {
                String title = c.getString(c.getColumnIndex(COLUMN_TITLE));
                list.add(title);
            } while (c.moveToNext());
        }
        c.close();

        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,list);
        mylistview.setAdapter(myArrayAdapter);
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),
                        NoteActivity.class);
                intent.putExtra(EXTRA_NOTEID,position + 1);
                startActivity(intent);
            }
        });
    }
}
