package com.demo.projectcontact.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.demo.projectcontact.model.Contact;

import java.util.ArrayList;

public class DBManger extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="contact_manager";
    private static final String TABLE_NAME="contact";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String PHONE_NUMBER="phone_number";
    private static final String GENDER="gender";
    private static final String NOTE="note";
    private static final String AVATAR="avatar";
    private static int VERSION=1;
    private Context context;

    private String SQLQuery="CREATE TABLE "+TABLE_NAME+" ("+
            ID +" integer primary key, "+
            NAME+ " TEXT, "+
            PHONE_NUMBER + " TEXT, "+
            GENDER + " TEXT, "+
            NOTE + " TEXT, "+
            AVATAR +" TEXT)";

    public DBManger(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        Log.d("tlh","on create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addContact(Contact contact){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(NAME,contact.getName());
        values.put(PHONE_NUMBER,contact.getPhone());
        if(contact.isGender()) {
            values.put(GENDER,"female");
        }
        else values.put(GENDER,"male");
        values.put(NOTE,contact.getNote());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact>listContact=new ArrayList<>();
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String phone=cursor.getString(2);
            String gender=cursor.getString(3);
            String note=cursor.getString(4);
            boolean isfemale=true;
            if(gender.equalsIgnoreCase("male")) isfemale=false;
            listContact.add(new Contact(id,name,phone,isfemale,note));
        }
        return listContact;
    }

}
