package com.route.contactapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class AddContactActivity extends AppCompatActivity {

    MaterialButton saveButton;
    EditText nameEditText;
    EditText phoneEditText;

    EditText descriptionEditText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        saveButton = findViewById(R.id.btn_save_contact);
        nameEditText = findViewById(R.id.edt_name);
        phoneEditText = findViewById(R.id.edt_phone);
        descriptionEditText = findViewById(R.id.edt_description);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick();
            }
        });
    }

    private void onSaveClick() {
        String name = nameEditText.getText().toString().trim();
        String phoneNumber = phoneEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        if(validateInput(name, phoneNumber)){
            Contact newContact = new Contact(name, R.drawable.ic_avatar, phoneNumber, description);
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
