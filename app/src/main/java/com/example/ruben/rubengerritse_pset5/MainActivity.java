package com.example.ruben.rubengerritse_pset5;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ListFragment.ListClicked,
        ItemFragment.ItemClicked{
    private TodoManager manager;
    private ListFragment listFragment;
    private ItemFragment itemFragment;
    private int selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = TodoManager.getInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        listFragment = new ListFragment();
        itemFragment = new ItemFragment();
        fragmentTransaction.add(R.id.list_fragment_fl, listFragment);
        fragmentTransaction.add(R.id.item_fragment_fl, itemFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager.readTodos(this);
        String[] listTitles = manager.getListTitles();
        listFragment.updateListView(listTitles);
    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.writeTodos(this);
    }

    @Override
    public void openItemFragment(int position){
        selectedList = position;
        ArrayList<TodoItem> todoItems = manager.getTodos(selectedList);
        FrameLayout listFrame = (FrameLayout) findViewById(R.id.list_fragment_fl);
        FrameLayout itemFrame = (FrameLayout) findViewById(R.id.item_fragment_fl);
        listFrame.setVisibility(View.GONE);
        itemFrame.setVisibility(View.VISIBLE);
        itemFragment.updateListView(todoItems);
    }

    @Override
    public void removeItem(int position) {
        manager.removeItem(position, selectedList);
        ArrayList<TodoItem> todoItems = manager.getTodos(selectedList);
        itemFragment.updateListView(todoItems);
    }

    @Override
    public void removeList(int position) {
        manager.removeList(position);
        String[] listTitles = manager.getListTitles();
        listFragment.updateListView(listTitles);
    }

    public void openAddListActivity(View view) {
        Intent intent = new Intent(this, AddListActivity.class);
        startActivity(intent);
    }

    public void addItemToList(View view) {
        EditText itemEditText = (EditText) findViewById(R.id.add_item_et);
        String item = itemEditText.getText().toString();
        manager.addItem(this, item, selectedList);
        ArrayList<TodoItem> todoItems = manager.getTodos(selectedList);
        itemFragment.updateListView(todoItems);
        itemEditText.setText("");
    }
}
