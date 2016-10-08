package com.example.ruben.rubengerritse_pset5;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ruben on 7-10-16.
 */

public class TodoListFragment extends Fragment{
    public String[] todoListTitles;
    private ListClicked listClicked;
    private ListView listView;

    public interface ListClicked {
        public void openTodoItemFragment(String title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.todo_list_lv);
        setListViewClickListeners();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listClicked = (ListClicked) context;
    }

    public void updateListView() {
        ListAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.item_todo_list_list_view,
                R.id.todo_list_item_tv, todoListTitles);
        listView.setAdapter(adapter);
    }

    public void setTodoListTitles (String[] todoListTitles){
        this.todoListTitles = todoListTitles;
    }

    private void setListViewClickListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.todo_list_item_tv);
                listClicked.openTodoItemFragment(title.getText().toString());
            }
        });
    }
}
