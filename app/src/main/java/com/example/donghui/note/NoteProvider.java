package com.example.donghui.note;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import static com.example.donghui.note.MySQLiteDatabaseHelper.TABLE_NOTES;

public class NoteProvider extends ContentProvider{
    private static final String TAG = "NoteProvider ";
    private SQLiteOpenHelper mDbHelper;
    private ArrayList<String> list = new ArrayList<String>();
    //在Create中初始化一个数据库
    @Override
    public boolean onCreate() {
        mDbHelper = new MySQLiteDatabaseHelper(getContext());
        return true;
    }
    //实现query方法
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor c = db.query(TABLE_NOTES, projection, selection, selectionArgs, null,
                null, sortOrder);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override

    public Uri insert( Uri uri,ContentValues values) {
        //insert some datas
        SQLiteDatabase db1 = mDbHelper.getWritableDatabase();
        db1.insert("notes","title", values);
        return  null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,String selection,String[] selectionArgs) {
        SQLiteDatabase dbg = mDbHelper.getWritableDatabase();
        dbg.update("notes",values,selection,selectionArgs);
        return 0;
    }
}
