package com.bfr.taskminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BackBurnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        ArrayList<ToDoItem> items = new ArrayList<ToDoItem>();
        items.add(new ToDoItem("BackBurner", "Finish App", "Next week", "Finish coursework", "High"));
        items.add(new ToDoItem("BackBurnery", "Cook Meal", "Next Month", "Make nutritous food", "Medium"));
        items.add(new ToDoItem("BackBurner", "Exercise", "Next Year", "Get Moving", "Low"));

        ToDoAdapter itemsAdapter = new ToDoAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

    }
}
