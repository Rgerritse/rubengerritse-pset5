package com.example.ruben.rubengerritse_pset5;

import java.util.ArrayList;

/**
 * Created by ruben on 7-10-16.
 */

public class TodoManager {
    private static TodoManager manager;
    private ArrayList<TodoList> todoLists;

    private TodoManager(){
        this.todoLists = new ArrayList<TodoList>();
    }

    public static TodoManager getInstance() {
        manager = new TodoManager();
        return manager;
    }
}
