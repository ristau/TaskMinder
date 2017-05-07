package com.bfr.taskminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Find the View that shows the Back Burner category
        TextView backBurner = (TextView) findViewById(R.id.backBurner);

// Set a click listener on that View
        backBurner.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Back Burner View is clicked on.
            @Override
            public void onClick(View view) {
                Intent backBurnerIntent = new Intent(MainActivity.this, BackBurnerActivity.class);
                startActivity(backBurnerIntent);
            }
        });
    }

    public void openTodayList(View view) {
        Intent i = new Intent(this, TodayActivity.class);
        startActivity(i);
    }

    public void openThisWeekList(View view) {
        Intent i = new Intent(this, ThisWeekActivity.class);
        startActivity(i);
    }

    public void openBucketList(View view) {
        Intent i = new Intent(this, BucketListActivity.class);
        startActivity(i);
    }

}
