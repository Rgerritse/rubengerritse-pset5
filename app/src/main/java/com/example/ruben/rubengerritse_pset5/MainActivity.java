package com.example.ruben.rubengerritse_pset5;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private TodoManager todoManager;
    private FragmentManager fragmentManager;
    private TodoListFragment todoListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoManager = TodoManager.getInstance();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        todoListFragment = new TodoListFragment();
        fragmentTransaction.add(R.id.fragment_fl, todoListFragment);
        fragmentTransaction.commit();
    }

    public void openAddListActivity(View view) {
        todoManager.writeTodos(this);
        Intent intent = new Intent(this, AddListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoManager.readTodos(this);
        String[] titles = todoManager.getTitleArray();
        todoListFragment.setTodoListTitles(titles);
        todoListFragment.updateListView();
    }
}
