package com.demo.projectcontact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.projectcontact.R;
import com.demo.projectcontact.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Contact> list;
    public ContactAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.list=objects;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(resource,parent,false);
        TextView name,phone;
        ImageView avatar;
        name=view.findViewById(R.id.name);
        phone=view.findViewById(R.id.phonenumber);
        avatar=view.findViewById(R.id.avatar);

        Contact contact=list.get(position);
        name.setText(contact.getName());
        phone.setText(contact.getPhone());
        if(contact.isGender()==true){
            avatar.setImageResource(R.drawable.female);
        }else{
            avatar.setImageResource(R.drawable.male);
        }

        return view;
    }
}
