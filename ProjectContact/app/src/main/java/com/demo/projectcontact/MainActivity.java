package com.demo.projectcontact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.demo.projectcontact.adapter.ContactAdapter;
import com.demo.projectcontact.database.DBManger;
import com.demo.projectcontact.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton mAdd;
    private ListView mListView;
    private ArrayList<Contact>mListContact;
    private ContactAdapter mAdapter;
    final private int REQUEST_CODE=1000;
    private final DBManger dbManger=new DBManger(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdd=findViewById(R.id.btnadd);
        mListView=findViewById(R.id.listview);
        mListContact=new ArrayList<>();

        mListContact.clear();
        mListContact=dbManger.getAllContact();


        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddContactActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });


        mAdapter=new ContactAdapter(MainActivity.this,R.layout.contact_item_row,mListContact);
        mListView.setAdapter(mAdapter);
        addEvents();

    }

    private void addEvents() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,DetailContact.class);
                Contact contact=mListContact.get(position);
                intent.putExtra("contact",contact);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Contact contact = (Contact) data.getSerializableExtra("contact");
                mListContact.add(contact);
                mAdapter.notifyDataSetChanged();
                dbManger.addContact(contact);
            }
        }
    }

}
