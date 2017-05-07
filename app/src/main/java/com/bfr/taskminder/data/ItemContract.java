package com.bfr.taskminder.data;

import android.provider.BaseColumns;

/**
 * Created by BFR on 5/6/17.
 */

public final class ItemContract {

    public static abstract class ItemEntry implements BaseColumns {

        public static final String TABLE_NAME = "items";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "category";
        public static final String COLUMN_CATEGORY = "name";
        public static final String COLUMN_DUEDATE = "due_date";
        public static final String COLUMN_NOTES = "notes";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_STATUS = "status";

        // Possible values for Category
        public static final int CATGORY_TODAY = 0;
        public static final int CATGORY_THIS_WEEK = 1;
        public static final int CATGORY_BUCKET_LIST = 2;
        public static final int CATGORY_BACK_BURNER = 3;


        // Possible values for Priority
        public static final int PRIORITY_HIGH = 0;
        public static final int PRIORITY_MEDIUM = 1;
        public static final int PRIORITY_LOW = 2;

        // Possible values for Status

        public static final int STATUS_NOT_STARTED = 0;
        public static final int STATUS_KIND_OF_STARTED = 1;
        public static final int STATUS_MORE_THAN_HALFWAY = 2;
        public static final int STATUS_DONE = 3;

    }



}
