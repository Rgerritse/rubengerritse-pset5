package com.example.ruben.rubengerritse_pset5;

/**
 * Created by ruben on 5-10-16.
 */

public class TodoItem {
    private String title;
    private boolean completed;

//
    public TodoItem(String title){
        this.title = title;
        completed = false;
    }

//    Returns the title of the ToDoItem
    public String getTitle(){
        return this.title;
    }

//    Returns the completed status of the ToDoItem
    public boolean getCompleted(){
        return this.completed;
    }

//    Set the completed status of the ToDoItem
    public void setCompleted(boolean status){
        this.completed = status;
    }
}
