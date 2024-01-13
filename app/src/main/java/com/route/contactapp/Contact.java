package com.route.contactapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Random;

public class Contact implements Parcelable {
    String contactName;
    int contactLogo;
    String contactPhoneNumber;
    String description;

    public Contact(String contactName, String contactPhoneNumber, String description) {
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.description = description;
        int[] avatarArray = {R.drawable.ic_panda, R.drawable.ic_dog, R.drawable.ic_rabbit, R.drawable.ic_chicken};
        Random random = new Random();
        int randomIndex = random.nextInt(avatarArray.length);
        this.contactLogo = avatarArray[randomIndex];
    }

    protected Contact(Parcel in) {
        contactName = in.readString();
        contactLogo = in.readInt();
        contactPhoneNumber = in.readString();
        description = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public int getContactLogo() {
        return contactLogo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(contactName);
        dest.writeInt(contactLogo);
        dest.writeString(contactPhoneNumber);
        dest.writeString(description);
    }
}
