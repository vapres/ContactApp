package com.route.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ContactDetailsActivity extends AppCompatActivity {

    Button backButton;
    ImageView contactImg;
    TextView contactName;
    TextView contactNumber;
    TextView contactDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        backButton = findViewById(R.id.btn_ic_back);
        contactImg = findViewById(R.id.logo);
        contactName = findViewById(R.id.name);
        contactNumber = findViewById(R.id.number);
        contactDesc = findViewById(R.id.desc);

        Intent intent = getIntent();
        if (intent != null) {
            Contact contact = intent.getParcelableExtra("clicked contact");
            if (contact != null) {
                contactImg.setImageResource(contact.contactLogo);
                contactName.setText(contact.contactName);
                contactNumber.setText(contact.contactPhoneNumber);
                contactDesc.setText(contact.description);
            }

        }
        backButton.setOnClickListener(v -> onBackClick());
    }

    private void onBackClick() {
        Intent intent = new Intent(this, MainContactsScreenActivity.class);
        startActivity(intent);
    }
}