package com.example.ruben.rubengerritse_pset5;

import java.util.ArrayList;

/**
 * Created by ruben on 5-10-16.
 */

public class TodoList {
    private String title;
    private ArrayList<TodoItem> todos;

    public TodoList(String title){
        this.title = title;
        this.todos = new ArrayList<TodoItem>();
    }

    public String getTitle(){
        return this.title;
    }

    public ArrayList<TodoItem> getTodos() {
        return this.todos;
    }
}
