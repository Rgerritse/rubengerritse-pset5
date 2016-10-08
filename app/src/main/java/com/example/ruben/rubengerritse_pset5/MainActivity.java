package com.example.ruben.rubengerritse_pset5;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements TodoListFragment.ListClicked{
    private TodoManager todoManager;
    private FragmentManager fragmentManager;
    private TodoListFragment todoListFragment;
    private TodoItemFragment todoItemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoManager = TodoManager.getInstance();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        todoListFragment = new TodoListFragment();
        todoItemFragment = new TodoItemFragment();
        fragmentTransaction.add(R.id.list_fragment_fl, todoListFragment);
        fragmentTransaction.add(R.id.item_fragment_fl, todoItemFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoManager.readTodos(this);
        String[] titles = todoManager.getTitleArray();
        todoListFragment.setTodoListTitles(titles);
        todoListFragment.updateListView();
    }

    
    @Override
    public void openTodoItemFragment(String title){
        ArrayList<TodoItem> todoItems = todoManager.getTodos(title);
        FrameLayout listFrame = (FrameLayout) findViewById(R.id.list_fragment_fl);
        FrameLayout itemFrame = (FrameLayout) findViewById(R.id.item_fragment_fl);
        listFrame.setVisibility(View.GONE);
        itemFrame.setVisibility(View.VISIBLE);
        todoItemFragment.updateListView(todoItems);
    }

//    Opens the Activity to add a new list
    public void openAddListActivity(View view) {
        todoManager.writeTodos(this);
        Intent intent = new Intent(this, AddListActivity.class);
        startActivity(intent);
    }
}
