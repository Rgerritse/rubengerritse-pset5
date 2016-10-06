package com.example.ruben.rubengerritse_pset5;

import java.util.ArrayList;

/**
 * Created by ruben on 5-10-16.
 */

public class TodoManager {
    private static TodoManager instance;
    private ArrayList<TodoList> todoLists;

    private TodoManager(){
        this.todoLists = new ArrayList<TodoList>();
    }

    public static TodoManager getInstance(){
        instance = new TodoManager();
        return instance;
    }

    public void readTodos(){

    }

    public void writeTodos(){

    }

    public ArrayList<TodoList> getTodoLists(){
        return this.todoLists;
    }

    public String[] getTitleArray(){
        int numTitles = todoLists.size();
        String[] titles = new String[numTitles];

        for (int i = 0; i < todoLists.size();i++) {
            titles[i] = todoLists.get(i).getTitle();
        }
        return titles;
    }
}
