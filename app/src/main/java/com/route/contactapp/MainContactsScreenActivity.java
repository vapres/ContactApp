package com.route.contactapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
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

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    if (activityResult.getData() != null) {
                        Contact contact = activityResult.getData().getParcelableExtra("contact");
                        adapter.addContactItem(contact);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contacts_screen);
        addNewContact = (FloatingActionButton) findViewById(R.id.btn_add_contact);
        contactRecycler = findViewById(R.id.rv_contacts);

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
        launcher.launch(intent);
    }
}