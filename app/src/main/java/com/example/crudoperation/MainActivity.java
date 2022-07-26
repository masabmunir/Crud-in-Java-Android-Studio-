package com.example.crudoperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.crudoperation.data.MyDBHandler;
import com.example.crudoperation.model.contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHandler db = new MyDBHandler(MainActivity.this);

//        Creating a contact for Adding

        contact masab = new contact();
        masab.setPhonenumber("051-223333");
        masab.setName("Masab Khan");

        //  Adding data to db
        db.addContact(masab);

        //Update Content
//        masab.setId(); write id here
        masab.setName("Masab Khan Niazi");
        masab.setPhonenumber("8383838833");
        int affectedrows = db.updatecontact(masab);
        Log.d("dbmasab","afftected rows are " + affectedrows);

        //For Delete operation
        db.deletecontent(1);
       //Get All Contacts
        List<contact> allContacts = db.getallcontact();
        for(contact contact : allContacts){
            Log.d("dbmasab","id" + contact.getId() + "\n"+
                                "Name: " + contact.getName() + "\n"+
                                "Phone Number: " + contact.getPhonenumber()+ "\n");
        }

    }

}