package com.example.ruben.rubengerritse_pset5;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ruben on 8-10-16.
 */

public class ItemListAdapter extends ArrayAdapter<TodoItem>{

    public ItemListAdapter(Context context, int resourceId, ArrayList<TodoItem> todoItems) {
        super(context, resourceId, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem todoItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tditem_list_view, parent, false);
        }
        TextView textTV = (TextView) convertView.findViewById(R.id.item_text_tv);
        textTV.setText(todoItem.getTitle());
        if (todoItem.getCompleted()) {
            textTV.setPaintFlags(textTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            textTV.setPaintFlags(textTV.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        return convertView;
    }
}
