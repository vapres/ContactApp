package com.route.contactapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerMainContactsAdapter extends RecyclerView.Adapter<RecyclerMainContactsAdapter.ViewHolder> {
    List<Contact> contacts;

    public RecyclerMainContactsAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        if (contact != null) {
            holder.avatar.setImageResource(contact.contactLogo);
            holder.name.setText(contact.contactName);
            holder.phone.setText(contact.contactPhoneNumber);
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(contact, position));
        }
    }


    public void addContactItem(Contact contact){
        this.contacts.add(contact);
        notifyDataSetChanged();
    }


    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(Contact contact, int position);
    }


    @Override
    public int getItemCount() {
        if (contacts == null) return 0;
        else return contacts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.imv_avatar);
            name = itemView.findViewById(R.id.tv_contact_name);
            phone = itemView.findViewById(R.id.tv_contact_phone);
        }
    }
}
