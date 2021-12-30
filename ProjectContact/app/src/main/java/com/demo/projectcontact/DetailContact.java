package com.demo.projectcontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.projectcontact.model.Contact;

public class DetailContact extends AppCompatActivity {

    Button mEdit;
    ImageView mBack, mAvatar;
    ImageButton mCall, mSms, mDelete;
    TextView mName, mPhone, mNote;
    int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        mEdit = findViewById(R.id.btnedit);
        mBack = findViewById(R.id.btnback);
        mCall = findViewById(R.id.btncall);
        mSms = findViewById(R.id.btnsms);
        mDelete = findViewById(R.id.btndelete);
        mAvatar = findViewById(R.id.btnavatar);
        mName = findViewById(R.id.tvname);
        mPhone = findViewById(R.id.tvphonenumber);
        mNote = findViewById(R.id.tvnote);

        Intent intent = getIntent();
        mPosition=intent.getIntExtra("position",-1);
        Contact contact = (Contact) intent.getSerializableExtra("contact");
        mName.setText(contact.getName());
        mPhone.setText(contact.getPhone());
        mNote.setText(contact.getNote());
        if (contact.isGender()) mAvatar.setImageResource(R.drawable.female);
        else mAvatar.setImageResource(R.drawable.male);

        addEvents();
    }

    private void addEvents() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:" + mPhone.getText().toString());
                intent.setData(uri);
                if (ActivityCompat.checkSelfPermission(DetailContact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DetailContact.this,new String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(intent);
            }
        });

        mSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                Uri uri=Uri.parse("sms:"+mPhone.getText().toString());
                intent.setData(uri);
                if(ActivityCompat.checkSelfPermission(DetailContact.this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(DetailContact.this,new String[]{Manifest.permission.SEND_SMS},1);
                    return;
                }
                startActivity(intent);
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
