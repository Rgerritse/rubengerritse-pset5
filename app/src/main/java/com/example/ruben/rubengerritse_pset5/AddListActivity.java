package com.example.ruben.rubengerritse_pset5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddListActivity extends AppCompatActivity {
    private TodoManager todoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        todoManager = TodoManager.getInstance();
    }

    public void addTodoList(View view) {
        todoManager.readTodos(this);
        EditText titleET = (EditText) findViewById(R.id.title_et);
        String title = titleET.getText().toString();
        todoManager.addTodoList(title);
        todoManager.writeTodos(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
