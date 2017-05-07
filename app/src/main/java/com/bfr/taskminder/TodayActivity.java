package com.bfr.taskminder;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bfr.taskminder.data.ItemContract.ItemEntry;
import com.bfr.taskminder.data.ItemDbHelper;


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
    ListView listView;
    ToDoAdapter itemsAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        category = "Today";

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


    public void initializeView(){
        itemsAdapter = new ToDoAdapter(this, items);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }

    public void initializeAddItemListener(){
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
    }

    public void initializeRemoveEntryListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                removeItemFromDB(position);
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return false;
            }


        });
    }

    public void removeItemFromDB(int position){
        int pos = position;
        ToDoItem itemToRemove = items.get(position);
        String itemName = itemToRemove.getName().toString();
        ItemDbHelper itemDbHelper = new ItemDbHelper(this);
        SQLiteDatabase db = itemDbHelper.getWritableDatabase();
        String selection = ItemEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { itemName };
        db.delete(ItemEntry.TABLE_NAME, selection, selectionArgs);

        Toast.makeText(TodayActivity.this,"Number of rows in database: " + items.size(),Toast.LENGTH_LONG).show();

    }




    private void displayDatabaseInfo() {

        ItemDbHelper mDbHelper = new ItemDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ItemEntry._ID,
                ItemEntry.COLUMN_CATEGORY,
                ItemEntry.COLUMN_NAME,
                ItemEntry.COLUMN_DUEDATE,
                ItemEntry.COLUMN_NOTES,
                ItemEntry.COLUMN_PRIORITY,
                ItemEntry.COLUMN_STATUS
        };

        String selection = ItemEntry.COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = { "Today" };

        String sortOrder = ItemEntry.COLUMN_PRIORITY + " DESC";

        //Cursor cursor = db.rawQuery("SELECT * FROM " + ItemEntry.TABLE_NAME, null);

        Cursor cursor = db.query(
                ItemEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );


         items = new ArrayList<ToDoItem>();

        try {

            int idColumnIndex = cursor.getColumnIndex(ItemEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_NAME);
            int notesColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_NOTES);
            int dueDateColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_DUEDATE);
            int priorityColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_PRIORITY);
            int statusColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_STATUS);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDueDate = cursor.getString(dueDateColumnIndex);
                String currentNotes = cursor.getString(notesColumnIndex);
                String currentPriority = cursor.getString(priorityColumnIndex);
                String currentStatus = cursor.getString(statusColumnIndex);

                items.add(new ToDoItem(category, currentName, currentDueDate, currentNotes, currentPriority, currentStatus));

            }

            initializeView();
            initializeAddItemListener();
            initializeRemoveEntryListener();
            itemsAdapter.notifyDataSetChanged();

            Toast.makeText(TodayActivity.this,"Number of rows in database: " + cursor.getCount(),Toast.LENGTH_LONG).show();

        } finally {
            cursor.close();
        }

    }
}
