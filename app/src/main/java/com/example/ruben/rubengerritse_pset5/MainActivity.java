package com.example.ruben.rubengerritse_pset5;

import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;

/**
 * Created by ruben on 8-10-16.
 * This class describes the MainActivity Activity of this app. It displays all Fragments and handles
 * all interaction from the user with the TodoManager
 */

public class MainActivity extends FragmentActivity implements ListFragment.ListClicked,
        ItemFragment.ItemClicked{
    private TodoManager manager;
    private ListFragment listFragment;
    private ItemFragment itemFragment;
    private AddListFragment addListFragment;
    private FrameLayout listFrame;
    private FrameLayout itemFrame;
    private FrameLayout addListFrame;
    private int openFragment;
    private int selectedList;

//    Define the Fragments and FrameLayout upon creation of the Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFragment = 0;
        selectedList = -1;
        manager = TodoManager.getInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        listFragment = new ListFragment();
        itemFragment = new ItemFragment();
        addListFragment = new AddListFragment();
        fragmentTransaction.add(R.id.list_fragment_fl, listFragment);
        fragmentTransaction.add(R.id.item_fragment_fl, itemFragment);
        fragmentTransaction.add(R.id.add_list_fragment_fl, addListFragment);
        fragmentTransaction.commit();

        listFrame = (FrameLayout) findViewById(R.id.list_fragment_fl);
        itemFrame = (FrameLayout) findViewById(R.id.item_fragment_fl);
        addListFrame = (FrameLayout) findViewById(R.id.add_list_fragment_fl);
    }

//    Set the layout, read the TodoManager and update the TodoLists List View upon start of the
//    Activity.
    @Override
    protected void onStart() {
        super.onStart();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            listFrame.setVisibility(View.VISIBLE);
            itemFrame.setVisibility(View.GONE);
            findViewById(R.id.to_lists_b).setVisibility(View.VISIBLE);
        } else {
            listFrame.setVisibility(View.GONE);
            itemFrame.setVisibility(View.VISIBLE);
            findViewById(R.id.to_lists_b).setVisibility(View.GONE);
        }
        manager.readTodos(this);
        String[] listTitles = manager.getListTitles();
        listFragment.updateListView(listTitles);
    }

//    Write the TodoItems the activity is stopped
    @Override
    protected void onStop() {
        super.onStop();
        manager.writeTodos(this);
    }

//    Change the layout when the screen orientation is changed
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            findViewById(R.id.to_lists_b).setVisibility(View.INVISIBLE);
            if (openFragment == 0) {
                listFrame.setVisibility(View.VISIBLE);
                itemFrame.setVisibility(View.INVISIBLE);
                addListFrame.setVisibility(View.GONE);
            } else if (openFragment == 1) {
                listFrame.setVisibility(View.VISIBLE);
                itemFrame.setVisibility(View.VISIBLE);
                addListFrame.setVisibility(View.GONE);
            } else {
                listFrame.setVisibility(View.GONE);
                itemFrame.setVisibility(View.GONE);
                addListFrame.setVisibility(View.VISIBLE);
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            findViewById(R.id.to_lists_b).setVisibility(View.VISIBLE);
            if (openFragment == 0) {
                listFrame.setVisibility(View.VISIBLE);
                itemFrame.setVisibility(View.GONE);
                addListFrame.setVisibility(View.GONE);
            } else if (openFragment == 1) {
                listFrame.setVisibility(View.GONE);
                itemFrame.setVisibility(View.VISIBLE);
                addListFrame.setVisibility(View.GONE);
            } else {
                listFrame.setVisibility(View.GONE);
                itemFrame.setVisibility(View.GONE);
                addListFrame.setVisibility(View.VISIBLE);
            }
        }
    }

//    Open the ItemFragment
    @Override
    public void openItemFragment(int position){
        selectedList = position;
        openFragment = 1;
        ArrayList<TodoItem> todoItems = manager.getTodos(selectedList);
        int orientation = getResources().getConfiguration().orientation;
        itemFrame.setVisibility(View.VISIBLE);
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            listFrame.setVisibility(View.GONE);
        } else {
            listFrame.setVisibility(View.VISIBLE);
        }
        String listTitle = manager.getListTitle(selectedList);
        itemFragment.updateListView(listTitle, todoItems);
    }

//    Remove a TodoItem from a TodoList
    @Override
    public void removeItem(int position) {
        manager.removeItem(position, selectedList);
        ArrayList<TodoItem> todoItems = manager.getTodos(selectedList);
        String listTitle = manager.getListTitle(selectedList);
        itemFragment.updateListView(listTitle, todoItems);
    }

//    Switch the completed status from a TodoItem
    @Override
    public void switchCompleted(int position) {
        manager.switchCompleted(position, selectedList);
        ArrayList<TodoItem> todoItems = manager.getTodos(selectedList);
        String listTitle = manager.getListTitle(selectedList);
        itemFragment.updateListView(listTitle, todoItems);
    }

//    Remove a TodoList
    @Override
    public void removeList(int position) {
        manager.removeList(position);
        String[] listTitles = manager.getListTitles();
        listFragment.updateListView(listTitles);
        if (position == selectedList) {
            openFragment = 0;
            selectedList = -1;
            itemFrame.setVisibility(View.INVISIBLE);
        }
    }

//    Open the addListFragment
    public void openAddListFragment(View view) {
        openFragment = 2;
        listFrame.setVisibility(View.GONE);
        itemFrame.setVisibility(View.GONE);
        addListFrame.setVisibility(View.VISIBLE);
        selectedList = -1;
    }

//    Add a TodoItem to an TodoList
    public void addItemToList(View view) {
        EditText itemEditText = (EditText) findViewById(R.id.add_item_et);
        String item = itemEditText.getText().toString();
        manager.addItem(this, item, selectedList);
        ArrayList<TodoItem> todoItems = manager.getTodos(selectedList);
        String listTitle = manager.getListTitle(selectedList);
        itemFragment.updateListView(listTitle, todoItems);
        itemEditText.setText("");
    }

//    Add a TodoList to the TodoManager
    public void addTodoList(View view) {
        openFragment = 1;

        EditText titleET = (EditText) findViewById(R.id.title_et);
        String title = titleET.getText().toString();
        titleET.setText("");
        manager.addList(title);

        selectedList = manager.getNumberOfLists() - 1;
        ArrayList<TodoItem> todoItems = manager.getTodos(selectedList);
        String listTitle = manager.getListTitle(selectedList);
        itemFragment.updateListView(listTitle, todoItems);

        String[] listTitles = manager.getListTitles();
        listFragment.updateListView(listTitles);

        addListFrame.setVisibility(View.GONE);
        itemFrame.setVisibility(View.VISIBLE);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            listFrame.setVisibility(View.GONE);
        } else {
            listFrame.setVisibility(View.VISIBLE);
        }
    }

//    Open the ListFragment
    public void toLists(View view) {
        openFragment = 0;
        listFrame.setVisibility(View.VISIBLE);
        itemFrame.setVisibility(View.GONE);
    }
}
