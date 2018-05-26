package com.codewithzak.smartshopper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.codewithzak.smartshopper.R;
import com.codewithzak.smartshopper.model.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class ShoppingExpandableListViewAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<String> groupList;
    private final List<ShoppingItem> shoppingItemList;
    private final OnClickListener onClickListener;

    public interface OnClickListener {
        void onClickCheckBox(int position, boolean checked);
    }

    public ShoppingExpandableListViewAdapter(final Context context,
                                             final List<ShoppingItem> shoppingItemList,
                                             final OnClickListener onClickListener) {
        this.context = context;
        this.groupList = new ArrayList<>();
        this.shoppingItemList = shoppingItemList;
        this.onClickListener = onClickListener;

        this.groupList.add("Shopping List");
        this.groupList.add("Checked List");
    }

    @Override
    public int getGroupCount() {
        int count = 0;
        for (ShoppingItem item: shoppingItemList) {
            if (item.isChecked()) {
                count++;
            }
        }

        if (count > 0) {
            return groupList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int count = 0;
        for (ShoppingItem item: shoppingItemList) {
            int checked = item.isChecked() ? 1 : 0;
            if (checked == groupPosition) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.groupList.get(groupPosition);
    }

    @Override
    public ShoppingItem getChild(int groupPosition, int childPosition) {
        int count = 0;
        for (ShoppingItem item: shoppingItemList) {
            int checked = item.isChecked() ? 1 : 0;
            if (checked == groupPosition) {
                if (count == childPosition) {
                    return item;
                }
                count++;
            }
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, final View convertView, final ViewGroup parent) {
        final View groupView;
        final String headerTitle = groupList.get(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            groupView = infalInflater.inflate(android.R.layout.simple_list_item_1, null);
            groupView.setPadding(100, 0, 0, 0);
        } else {
            groupView = convertView;
        }

        TextView textView = groupView.findViewById(android.R.id.text1);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.GRAY);
        textView.setBackgroundColor(Color.parseColor("#F0F0F0"));
        textView.setText(headerTitle);

        return groupView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild,
                             View convertView, final ViewGroup parent) {
        // Get the data item for this position
        final ShoppingItem item = getChild(groupPosition, childPosition);
        CustomListViewAdapter.RowViewHolder rowViewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.row_shopping_item, parent, false);
            rowViewHolder = new CustomListViewAdapter.RowViewHolder(convertView);
            convertView.setTag(rowViewHolder);
        } else {
            rowViewHolder = (CustomListViewAdapter.RowViewHolder) convertView.getTag();
        }

        String name = item.getName();
        if (item.getCount() > 1) {
            name += " (" + item.getCount() + ")";
        }
        rowViewHolder.textView.setText(name);
        rowViewHolder.checkBox.setTag(getChildTruePosition(groupPosition, childPosition));
        rowViewHolder.checkBox.setChecked(item.isChecked());

        if (item.isChecked()) {
            rowViewHolder.textView.setTextColor(Color.GRAY);
            rowViewHolder.textView.setPaintFlags(rowViewHolder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            rowViewHolder.textView.setTextColor(Color.BLACK);
            rowViewHolder.textView.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
        }

        rowViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                onClickListener.onClickCheckBox(position, ((CheckBox) view).isChecked());
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    private int getChildTruePosition(int groupPosition, int childPosition) {
        int i = 0;
        int count = 0;
        for (ShoppingItem item: shoppingItemList) {
            int checked = item.isChecked() ? 1 : 0;
            if (checked == groupPosition) {
                if (count == childPosition) {
                    return i;
                }
                count++;
            }
            i++;
        }
        return i;
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
