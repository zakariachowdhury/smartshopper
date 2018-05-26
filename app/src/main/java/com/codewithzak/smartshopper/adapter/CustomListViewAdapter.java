package com.codewithzak.smartshopper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.codewithzak.smartshopper.R;
import com.codewithzak.smartshopper.model.ShoppingItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CustomListViewAdapter extends ArrayAdapter {
    public CustomListViewAdapter(@NonNull Context context, @NonNull List<ShoppingItem> shoppingItems) {
        super(context, android.R.layout.simple_list_item_1, shoppingItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ShoppingItem item = (ShoppingItem) getItem(position);
        RowViewHolder rowViewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_shopping_item, parent, false);
            rowViewHolder = new RowViewHolder(convertView);
            convertView.setTag(rowViewHolder);
        } else {
            rowViewHolder = (RowViewHolder) convertView.getTag();
        }

        String name = item.getName();
        if (item.getCount() > 1) {
            name += " (" + item.getCount() + ")";
        }
        rowViewHolder.textView.setText(name);
        rowViewHolder.checkBox.setChecked(item.isChecked());

        if (item.isChecked()) {
            rowViewHolder.textView.setTextColor(Color.GRAY);
            rowViewHolder.textView.setPaintFlags(rowViewHolder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        return convertView;
    }

    protected static class RowViewHolder {

        @BindView(R.id.textView)
        protected TextView textView;

        @BindView(R.id.checkBox)
        protected CheckBox checkBox;

        protected RowViewHolder(@NonNull final View viewToBind) {
            ButterKnife.bind(this, viewToBind);
        }
    }
}
