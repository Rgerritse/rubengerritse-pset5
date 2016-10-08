package com.example.ruben.rubengerritse_pset5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddListActivity extends AppCompatActivity {
    private TodoManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager = TodoManager.getInstance();
        manager.readTodos(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.writeTodos(this);
    }
    
    public void addTodoList(View view) {
        EditText titleET = (EditText) findViewById(R.id.title_et);
        String title = titleET.getText().toString();
        manager.addList(title);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
