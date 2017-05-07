package com.bfr.taskminder.data;

/**
 * Created by BFR on 5/6/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bfr.taskminder.data.ItemContract.ItemEntry;

public class ItemDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ItemDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "taskItems.db";

    private static final int DATABASE_VERSION = 1;

    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ITEMS_TABLE = "CREATE TABLE " + ItemEntry.TABLE_NAME + " ("
                + ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemEntry.COLUMN_CATEGORY + " TEXT NOT NULL, "
                + ItemEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + ItemEntry.COLUMN_DUEDATE + " TEXT, "
                + ItemEntry.COLUMN_NOTES + " TEXT, "
                + ItemEntry.COLUMN_PRIORITY + " TEXT, "
                + ItemEntry.COLUMN_STATUS + " TEXT);";

        db.execSQL(SQL_CREATE_ITEMS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }







}
