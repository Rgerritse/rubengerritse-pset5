package com.example.ruben.rubengerritse_pset5;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ruben on 7-10-16.
 */

public class TodoManager {
    private static TodoManager manager;
    private ArrayList<TodoList> todoLists;
    private final static String PREFIX_TDL = "tdl";

    private final static String FILE_NAME = "TODO_MANAGER_FILE";

    private TodoManager() {
        this.todoLists = new ArrayList<TodoList>();
    }

    public static TodoManager getInstance() {
        manager = new TodoManager();
        return manager;
    }

    public void writeTodos(Context context) {
        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            for (TodoList todoList : todoLists) {
                String todoListTitle = String.format("%s%s\n", PREFIX_TDL, todoList.getTitle());
                System.out.println(todoListTitle);
                fileOutputStream.write(todoListTitle.getBytes());
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readTodos(Context context) {
        manager = TodoManager.getInstance();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line = "";
            TodoList todoList;
            while((line = reader.readLine()) != null) {
                if (line.startsWith(PREFIX_TDL)) {
                    line = line.replaceFirst(PREFIX_TDL, "");
                    todoList = new TodoList(line);
                    todoLists.add(todoList);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTodoList(String title) {
        TodoList todoList = new TodoList(title);
        todoLists.add(todoList);
    }

    public String[] getTitleArray() {
        int numTitles = todoLists.size();
        String[] titles = new String[numTitles];

        for (int i = 0; i < numTitles; i++) {
            titles[i] = todoLists.get(i).getTitle();
        }
        return titles;
    }
}
