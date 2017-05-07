package com.bfr.taskminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class TodayActivity extends AppCompatActivity {

    int selectedPos;
    private final int REQUEST_CODE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        final ArrayList<ToDoItem>items = new ArrayList<ToDoItem>();
        items.add(new ToDoItem("Today", "Finish App", "Saturday", "Finish coursework", "High"));
        items.add(new ToDoItem("Today", "Cook Meal", "Saturday", "Make nutritous food", "Medium"));
        items.add(new ToDoItem("Today", "Exercise", "Sunday", "Get Moving", "Low"));

        ToDoAdapter itemsAdapter = new ToDoAdapter(this, items);

         ListView listView = (ListView) findViewById(R.id.list);
         listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedPos = position;

                String editNameText;
                editNameText = items.get(position).getName();

                Intent i = new Intent(TodayActivity.this, EditItemActivity.class);

                 i.putExtra("nametext", editNameText);
                 i.putExtra("position", position);
                 startActivityForResult(i, REQUEST_CODE);
            }
        });



//            Log.v(" TodayActivity", "Index: " + index + " Value: " + words.get(index));

    }

    public void launchEditView() {
        Intent i = new Intent(TodayActivity.this, EditItemActivity.class);
        startActivity(i);
    }


}
