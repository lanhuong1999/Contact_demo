package com.demo.projectcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.demo.projectcontact.model.Contact;

public class  AddContactActivity extends AppCompatActivity {
    EditText mName,mPhone,mNote;
    Button mBtnSave,mBtnCancel;
    RadioButton mMale,mFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        mName=findViewById(R.id.txtname);
        mPhone=findViewById(R.id.txtphonenumber);
        mNote=findViewById(R.id.txtnote);
        mBtnSave=findViewById(R.id.btnsave);
        mBtnCancel=findViewById(R.id.btncancel);
        mMale=findViewById(R.id.btnmale);
        mFemale=findViewById(R.id.btnfemale);

        Intent intent=getIntent();
        Contact contact= (Contact) intent.getSerializableExtra("editcontact");
        if(contact!=null){
            mName.setText(contact.getName());
            mPhone.setText(contact.getPhone());
            if(!contact.isGender()) mMale.setChecked(true);
            mNote.setText(contact.getNote());
        }
        addEvents();
    }

    private void addEvents() {
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString();
                String phone=mPhone.getText().toString();
                boolean isFemale=true;
                if(mMale.isChecked()) isFemale=false;
                String note=mNote.getText().toString();

                if(name.equals("")){
                    Toast.makeText(AddContactActivity.this,"Please input name",
                            Toast.LENGTH_SHORT).show();
                }
                else if(phone.equals("")){
                    Toast.makeText(AddContactActivity.this,"Please input phone number",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Contact contact = new Contact(name, phone, isFemale, note);
                    Intent intent = new Intent();
                    intent.putExtra("contact", contact);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}
