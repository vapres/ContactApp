package com.route.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainContactsScreenActivity extends AppCompatActivity {

    FloatingActionButton addNewContact;
    RecyclerView contactRecycler;
    RecyclerMainContactsAdapter adapter;
    List<Contact> contactList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contacts_screen);
        addNewContact = (FloatingActionButton) findViewById(R.id.btn_add_contact);
        contactRecycler = findViewById(R.id.rv_contacts);

        Intent intent = getIntent();
        if (intent != null) {
            Contact contact = intent.getParcelableExtra("contact");
            contactList.add(contact);
        }

        addNewContact.setOnClickListener(v -> onAddClick());
        if (contactList != null) {
            adapter = new RecyclerMainContactsAdapter(contactList);
            contactRecycler.setAdapter(adapter);
        }
        adapter.setOnItemClickListener((contact, position) -> {
            Intent intent2 = new Intent(this, ContactDetailsActivity.class);
            intent2.putExtra("clicked contact", contact);
            startActivity(intent2);
        });
    }



    public void onAddClick() {
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivity(intent);
    }
}