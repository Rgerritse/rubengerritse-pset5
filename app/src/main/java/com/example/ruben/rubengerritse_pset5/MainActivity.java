package com.example.ruben.rubengerritse_pset5;



import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
    private TodoManager manager;
    private ListView listView;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = TodoManager.getInstance();

//      Set the fragment manager and the initial fragment
        Bundle bundle = new Bundle();
        String[] todoListTitles = manager.getTitleArray();
        bundle.putStringArray("todoListTitles", todoListTitles);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TodoListFragment todoListFragment = new TodoListFragment();
        todoListFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_fl, todoListFragment);
        fragmentTransaction.commit();
    }

    public TodoManager getTodoManager() {
        return manager;
    }
}
