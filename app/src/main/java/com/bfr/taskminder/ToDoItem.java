package com.bfr.taskminder;

import android.content.Context;

import java.sql.Date;

/**
 * Created by BFR on 5/6/17.
 */

public class ToDoItem {

    private  String category;
    private  String toDoName;
    private String dueDate;
    private  String notes;
    private  String priority;


    // constructor
    public ToDoItem(String itemCategory, String name, String itemDueDate, String itemNotes, String itemPriority) {
        category=itemCategory;
        toDoName = name;
        dueDate = itemDueDate;
        notes = itemNotes;
        priority = itemPriority;

    }


    public void setCategory(String itemCategory){
        category = itemCategory;
    }

    public void setName(String name) {
        toDoName = name;
    }

    public void setDueDate(String date){
        dueDate = date;
    }

    public void setNotes(String itemNotes){
        notes = itemNotes;
    }

    public void setPriority(String itemPriority){
        priority = itemPriority;
    }


    public String getCategory(){
        return category;
    }

    public String getName(){
        return toDoName;
    }

    public String getDueDate(){
        return dueDate;
    }

    public String getNotes(){
        return notes;
    }

    public String getPriority(){
        return priority;
    }

}
