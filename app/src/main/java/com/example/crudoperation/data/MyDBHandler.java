package com.example.crudoperation.data;
import android.content.ContentValues;
import android.content.Context;
import com.example.crudoperation.params.params;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.crudoperation.model.contact;
import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{
    public MyDBHandler(Context context) {
        super(context, params.DB_NAME, null, params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String Create = "CREATE TABLE" + params.Table_NAME + "("
               + params.KEY_ID + "INTEGER PRIMARY KEY," + params.KEY_NAME
               + "TEXT," + params.KEY_PHONE+ "TEXT" + ")";
        Log.d("dbmasab","Query being run is : "+ Create);
        sqLiteDatabase.execSQL(Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(params.KEY_NAME, contact.getName());
        values.put(params.KEY_PHONE,contact.getPhonenumber());

        db.insert(params.Table_NAME, null, values);
        Log.d("dbmasab","Query working Successfully : ");
        db.close();

    }

    public List<contact> getallcontact(){
        List<contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String Select  = " SELECT * FROM " + params.Table_NAME;

        Cursor cursor = db.rawQuery(Select, null);
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                       contact contact = new contact();
                       contact.setId(Integer.parseInt(cursor.getString(0)));
                       contact.setName(cursor.getString(1));
                       contact.setPhonenumber(cursor.getString(2));
                       contactList.add(contact);
            } while (cursor.moveToNext());
            // moving our cursor to next.
        } return contactList;


    }

    public int updatecontact(contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(params.KEY_NAME,contact.getName());
        values.put(params.KEY_PHONE,contact.getPhonenumber());
        return db.update(params.Table_NAME,values,params.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }
//Changes using ID
    public void deletecontent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(params.Table_NAME, params.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Changes using name
  public void deletecontent(contact contact){
      SQLiteDatabase db = this.getWritableDatabase();
      db.delete(params.Table_NAME, params.KEY_NAME + "=?", new String[]{String.valueOf(contact.getId())});
      db.close();
  }
}
