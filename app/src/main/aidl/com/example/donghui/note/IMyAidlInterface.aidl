
package com.example.donghui.note;
import android.content.ContentValues;

interface IMyAidlInterface {
   // void saveNote(in ContentValues values);
    void saveNote(in ContentValues values);
    ContentValues doInsert(String name,String content);
    //void getservice();
}
