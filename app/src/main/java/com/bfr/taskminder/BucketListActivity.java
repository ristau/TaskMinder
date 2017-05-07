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
        items.add(new ToDoItem("BucketList", "Sail Around the World", "Someday", "Start in Carribbean", "High", "Not Started"));
        items.add(new ToDoItem("BucketList", "Travel to Iceland", "One Day", "Visit Hot Springs", "Medium", "Not Started"));
        items.add(new ToDoItem("BucketList", "Drive Cross Country", "Tomorrow", "Get Moving", "Low", "Not Started"));

        ToDoAdapter itemsAdapter = new ToDoAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

    }
}
