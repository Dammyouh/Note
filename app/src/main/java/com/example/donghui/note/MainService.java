package com.example.donghui.note;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import static com.example.donghui.note.MySQLiteDatabaseHelper.COLUMN_CONTENT;
import static com.example.donghui.note.MySQLiteDatabaseHelper.COLUMN_TITLE;

public class MainService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        //Return the communication channel to the service.
        //MyBinder myBinder = new MyBinder();
        return mBinder;
    }
    // private class MyBinder extends IMyAidlInterface.Stub{
    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
       @Override
        public void saveNote(ContentValues values) throws RemoteException {
            ContentResolver contentResolver = getContentResolver();
            contentResolver.insert(Uri.parse("content://com.example.donghui.note"),values);
        }
        @Override
        public ContentValues doInsert(String name,String content) throws RemoteException {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE,name);
            values.put(COLUMN_CONTENT,content);
            return values;
        }
    };

}


