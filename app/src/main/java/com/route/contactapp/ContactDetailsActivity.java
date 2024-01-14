package com.route.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.route.contactapp.databinding.ActivityContactDetailsBinding;
import com.route.contactapp.databinding.ActivityMainContactsScreenBinding;


public class ContactDetailsActivity extends AppCompatActivity {
    private ActivityContactDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            Contact contact = intent.getParcelableExtra("clicked contact");
            if (contact != null) {
                binding.logo.setImageResource(contact.contactLogo);
                binding.name.setText(contact.contactName);
                binding.number.setText(contact.contactPhoneNumber);
                binding.desc.setText(contact.description);
            }

        }
        binding.btnIcBack.setOnClickListener(v -> onBackClick());
    }

    private void onBackClick() {
        finish();
    }
}