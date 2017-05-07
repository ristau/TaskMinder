package com.bfr.taskminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BFR on 5/6/17.
 */

public class ToDoAdapter extends ArrayAdapter<ToDoItem> {

    private static final String LOG_TAG = ToDoAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param items A List of To-Do objects to display in a list
     */
    public ToDoAdapter(Context context, ArrayList<ToDoItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ToDoItem itemToDo = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.tvToDoName);
        nameTextView.setText(itemToDo.getName());

        TextView dueDateTextView = (TextView) listItemView.findViewById(R.id.tvDueDate);
        dueDateTextView.setText(itemToDo.getDueDate());

        TextView priorityTextView = (TextView) listItemView.findViewById(R.id.tvPriority);
        priorityTextView.setText(itemToDo.getPriority());

       return listItemView;
    }
}
