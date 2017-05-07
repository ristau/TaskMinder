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
        items.add(new ToDoItem("BackBurner", "Clean Closet", "Next week", "Donate to Goodwill", "High", "Not Started"));
        items.add(new ToDoItem("BackBurner", "Organize Magazines", "Next Month", "Save favorite articles", "Medium", "Not Started"));
        items.add(new ToDoItem("BackBurner", "Organize Kitchen", "Next Year", "Clean Cupboards", "Low", "Not Started"));

        ToDoAdapter itemsAdapter = new ToDoAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

    }
}
