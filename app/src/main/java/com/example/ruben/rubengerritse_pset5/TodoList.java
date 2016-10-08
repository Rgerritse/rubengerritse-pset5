package com.example.ruben.rubengerritse_pset5;

import java.util.ArrayList;

/**
 * Created by ruben on 7-10-16.
 */

public class TodoList {
    private String title;
    private ArrayList<TodoItem> todoItems;

    public TodoList(String title) {
        this.title = title;
        this.todoItems = new ArrayList<>();
        todoItems.add(new TodoItem("black"));
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<TodoItem> getTodoItems() {
        return this.todoItems;
    }
}

