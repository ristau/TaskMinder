package com.bfr.taskminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BucketListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        ArrayList<ToDoItem> items = new ArrayList<ToDoItem>();
        items.add(new ToDoItem("BucketList", "Finish App", "Someday", "Finish coursework", "High"));
        items.add(new ToDoItem("BucketList", "Cook Meal", "One Day", "Make nutritous food", "Medium"));
        items.add(new ToDoItem("BucketList", "Exercise", "Tomorrow", "Get Moving", "Low"));

        ToDoAdapter itemsAdapter = new ToDoAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

    }
}
