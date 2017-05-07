package com.bfr.taskminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.commons.io.FileUtils.*;

public class TodayActivity extends AppCompatActivity {

    int selectedPos;
    private final int REQUEST_CODE = 20;
    String category;
    ArrayList<ToDoItem>items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        category = "Today";

        items = new ArrayList<ToDoItem>();
        items.add(new ToDoItem(category, "Finish App", "Saturday", "Finish coursework", "High"));
        items.add(new ToDoItem(category, "Cook Meal", "Saturday", "Make nutritous food", "Medium"));
        items.add(new ToDoItem(category, "Exercise", "Sunday", "Get Moving", "Low"));

        final ToDoAdapter itemsAdapter = new ToDoAdapter(this, items);

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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();;
                return false;
            }


        });
//            Log.v(" TodayActivity", "Index: " + index + " Value: " + words.get(index));
    }

    public void launchEditView() {
        Intent i = new Intent(TodayActivity.this, EditItemActivity.class);
        startActivity(i);
    }


    public void addNewItem(View view) {
        Intent i = new Intent(TodayActivity.this, EditItemActivity.class);
        i.putExtra("category", category);
        startActivityForResult(i, REQUEST_CODE);
    }

//    private void readItems() {
//        File filesDir = getFilesDir();
//        File file = new File(filesDir, "todo.txt");
//        try {
//            items = new ArrayList<ToDoItem>(readLines(file));
//        } catch (IOException e) {
//
//        }
//    }
}
