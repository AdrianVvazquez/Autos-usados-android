package com.example.contextmenu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CarItemViewAdapter extends ArrayAdapter<CarListItemView> {

//    ArrayList<AutoListItemView> list;

    // invoke the suitable constructor of the ArrayAdapter class
    public CarItemViewAdapter(@NonNull Context context, ArrayList<CarListItemView> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // if the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.listitem_auto, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        CarListItemView autoItem = getItem(position);

        // then according to the position of the view assign the desired image for the same
        ImageView imageList = (ImageView)currentItemView.findViewById(R.id.LblCarImg_Small);
        imageList.setImageResource(autoItem.getListImageId());

        TextView lblListId = (TextView)currentItemView.findViewById(R.id.LblListId);
        lblListId.setText(String.valueOf(autoItem.getListId()));
        TextView lblListBrand = (TextView)currentItemView.findViewById(R.id.LblListBrand);
        lblListBrand.setText(String.valueOf(autoItem.getListBrand()));
        TextView lblListName = (TextView)currentItemView.findViewById(R.id.LblListName);
        lblListName.setText(String.valueOf(autoItem.getListName()));
        TextView lblListModel = (TextView)currentItemView.findViewById(R.id.LblListModel);
        lblListModel.setText(String.valueOf(autoItem.getListModel()));

        // then return the recyclable view
        return currentItemView;
    }
}

