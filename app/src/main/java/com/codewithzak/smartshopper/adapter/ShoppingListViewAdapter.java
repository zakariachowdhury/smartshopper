package com.codewithzak.smartshopper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codewithzak.smartshopper.R;
import com.codewithzak.smartshopper.model.ShoppingItem;

import java.util.List;

public final class ShoppingListViewAdapter extends ArrayAdapter {
    public ShoppingListViewAdapter(@NonNull Context context, @NonNull List<ShoppingItem> shoppingItems) {
        super(context, android.R.layout.simple_list_item_1, shoppingItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ShoppingItem item = (ShoppingItem) getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_shopping_item, parent, false);
        }

        TextView itemTextView = convertView.findViewById(R.id.textView);
        String name = item.getName();

        if (item.getCount() > 1) {
            name += " (" + item.getCount() + ")";
        }
        itemTextView.setText(name);

        return convertView;
    }

}
