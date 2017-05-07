package com.bfr.taskminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ThisWeekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        ArrayList<ToDoItem> items = new ArrayList<ToDoItem>();
        items.add(new ToDoItem("This Week", "Data Structures HW", "Monday", "Finish coursework", "High", "Not Started"));
        items.add(new ToDoItem("This Week", "Unix Assmt 3", "Wednesday", "Make nutritous food", "High", "Not Started"));
        items.add(new ToDoItem("This Week", "Exercise", "Sunday", "Get Moving", "Low", "Not Started"));

        ToDoAdapter itemsAdapter = new ToDoAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
