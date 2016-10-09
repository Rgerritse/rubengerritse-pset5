package com.example.ruben.rubengerritse_pset5;

import java.util.ArrayList;

/**
 * Created by ruben on 8-10-16.
 * This class describes a TodoList object, which contains an ArrayList of TodoItems.
 * This class handles the interaction towards its underlying TodoItems.
 */

public class TodoList {
    private String title;
    private ArrayList<TodoItem> itemList;

    public TodoList(String title) {
        this.title = title;
        this.itemList = new ArrayList<>();
    }

    //    Returns the title of this TodoList
    public String getTitle() {
        return this.title;
    }

    //    Returns the TodoItems of this TodoList
    public ArrayList<TodoItem> getTodoItems() {
        return this.itemList;
    }

    //    Adds a new todoItem to this TodoList
    public void addTodoItem (TodoItem item) {
        itemList.add(item);
    }

    public void removeItem (int position) {
        itemList.remove(position);
    }
}
