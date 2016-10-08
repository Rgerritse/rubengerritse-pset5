package com.example.ruben.rubengerritse_pset5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ruben on 7-10-16.
 */

public class TodoItemFragment extends Fragment{
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_todo_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.item_lv);
    }

    public void updateListView(ArrayList<TodoItem> todoItems){
        if (getActivity() != null) {
            TodoItemListAdapter adapter = new TodoItemListAdapter(getActivity(),
                    R.layout.item_todo_item_list_view, todoItems);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
        }
    }
}
