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
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.route.contactapp.databinding.ActivityMainContactsScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class MainContactsScreenActivity extends AppCompatActivity {

    private ActivityMainContactsScreenBinding binding;
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
                        binding.tvInit.setText("");
                        binding.imvInit.setImageResource(0);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainContactsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvInit.setText(R.string.no_contacts);
        binding.imvInit.setImageResource(R.drawable.ic_init);
        binding.btnAddContact.setOnClickListener(v -> onAddClick());
        if (contactList != null) {
            adapter = new RecyclerMainContactsAdapter(contactList);
            binding.rvContacts.setAdapter(adapter);
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