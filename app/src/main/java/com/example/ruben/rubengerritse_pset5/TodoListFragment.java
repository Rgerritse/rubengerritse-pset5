package com.example.ruben.rubengerritse_pset5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by ruben on 7-10-16.
 */

public class TodoListFragment extends Fragment{
    public String[] todoListTitles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    public void updateListView() {
        ListView listView = (ListView) getView().findViewById(R.id.todo_list_lv);
        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                todoListTitles);
        listView.setAdapter(adapter);
    }

    public void setTodoListTitles (String[] todoListTitles){
        this.todoListTitles = todoListTitles;
    }
}
