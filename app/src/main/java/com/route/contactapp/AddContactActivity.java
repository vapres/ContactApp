package com.route.contactapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.route.contactapp.databinding.ActivityAddContactBinding;
import com.route.contactapp.databinding.ActivityContactDetailsBinding;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSaveContact.setOnClickListener(v -> onSaveClick());
    }

    private void onSaveClick() {
        String name = binding.edtName.getText().toString().trim();
        String phoneNumber = binding.edtPhone.getText().toString().trim();
        String description = binding.edtDescription.getText().toString().trim();
        if (validateInput(name, phoneNumber)) {
            Contact newContact = new Contact(name, phoneNumber, description);
            Intent intent = new Intent(this, MainContactsScreenActivity.class);
            intent.putExtra("contact", newContact);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean validateInput(String name, String phoneNumber) {
        if (name.length() < 3) {
            Toast.makeText(this, "Name should be at least 3 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (phoneNumber.length() != 11) {
            Toast.makeText(this, "Phone number should be 11 digits", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
