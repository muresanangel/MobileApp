package com.example.aplicatiemob.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aplicatiemob.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {

    public ListAdapter(Context context, ArrayList<Pet> petArrayList){
        super(context, R.layout.list_item,petArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Pet pet = (Pet) getItem(position);

        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView petName = convertView.findViewById(R.id.petName);
        TextView msg = convertView.findViewById(R.id.lMessage);

        imageView.setImageResource(pet.imageId);
        petName.setText(pet.name);
        msg.setText(pet.story);

        return convertView;
    }
}
