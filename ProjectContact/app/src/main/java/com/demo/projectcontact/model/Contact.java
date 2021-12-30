package com.demo.projectcontact.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id;
    private String name;
    private String phone;
    private boolean gender;
    private String note;

    public Contact(int id, String name, String phone, boolean gender,String note) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.note=note;
    }

    public Contact(String name, String phone, boolean gender,String note) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.note=note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean compareContact(Contact c){
        if(!(this.name.equals(c.getName())&&this.phone.equals(c.getPhone())&&
                this.gender!=c.isGender())&&this.note.equals(c.getNote())){
            return false;
        }
        return true;
    }
}
