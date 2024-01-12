package com.route.contactapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Contact implements Parcelable {
    String contactName;
    int contactLogo;
    String contactPhoneNumber;
    String description;

    public Contact(String contactName, int contactLogo, String contactPhoneNumber, String description) {
        this.contactName = contactName;
        this.contactLogo = contactLogo;
        this.contactPhoneNumber = contactPhoneNumber;
        this.description = description;
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
