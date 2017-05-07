package com.bfr.taskminder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bfr.taskminder.data.ItemContract;
import com.bfr.taskminder.data.ItemDbHelper;

import java.util.ArrayList;

public class BucketListActivity extends AppCompatActivity {

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

        category = "Bucket List";

//            Log.v(" TodayActivity", "Index: " + index + " Value: " + words.get(index));
    }

    public void launchEditView() {
        Intent i = new Intent(BucketListActivity.this, EditItemActivity.class);
        startActivity(i);
    }


    public void addNewItem(View view) {
        Intent i = new Intent(BucketListActivity.this, EditItemActivity.class);
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
                int itemID;

                boolean isEditing = true;
                ToDoItem itemToEdit;
                itemToEdit = items.get(position);
                itemID = itemToEdit.getId();

                Intent i = new Intent(BucketListActivity.this, EditItemActivity.class);

                i.putExtra("isEditing", isEditing);
                i.putExtra("itemID", itemID);
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
        String selection = ItemContract.ItemEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { itemName };
        db.delete(ItemContract.ItemEntry.TABLE_NAME, selection, selectionArgs);

    }

    private void displayDatabaseInfo() {

        ItemDbHelper mDbHelper = new ItemDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ItemContract.ItemEntry._ID,
                ItemContract.ItemEntry.COLUMN_CATEGORY,
                ItemContract.ItemEntry.COLUMN_NAME,
                ItemContract.ItemEntry.COLUMN_DUEDATE,
                ItemContract.ItemEntry.COLUMN_NOTES,
                ItemContract.ItemEntry.COLUMN_PRIORITY,
                ItemContract.ItemEntry.COLUMN_STATUS
        };

        String selection = ItemContract.ItemEntry.COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = { "Bucket List" };

        String sortOrder = ItemContract.ItemEntry.COLUMN_PRIORITY + " DESC";

        //Cursor cursor = db.rawQuery("SELECT * FROM " + ItemEntry.TABLE_NAME, null);

        Cursor cursor = db.query(
                ItemContract.ItemEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );


        items = new ArrayList<ToDoItem>();

        try {

            int idColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry._ID);
            int categoryColumIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_CATEGORY);
            int nameColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME);
            int notesColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NOTES);
            int dueDateColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_DUEDATE);
            int priorityColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_PRIORITY);
            int statusColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_STATUS);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentCategory = cursor.getString(categoryColumIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDueDate = cursor.getString(dueDateColumnIndex);
                String currentNotes = cursor.getString(notesColumnIndex);
                String currentPriority = cursor.getString(priorityColumnIndex);
                String currentStatus = cursor.getString(statusColumnIndex);

                items.add(new ToDoItem(currentID, currentCategory, currentName, currentDueDate, currentNotes, currentPriority, currentStatus));

            }

            initializeView();
            initializeAddItemListener();
            initializeRemoveEntryListener();
            itemsAdapter.notifyDataSetChanged();

        } finally {
            cursor.close();
        }

    }
}

