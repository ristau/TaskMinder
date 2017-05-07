package com.bfr.taskminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity {

    EditText updateName;
    TextView dueDate;
    Button btn;
    int year_x, month_x, day_x;

    static final int DIALOG_ID = 0;

    // Spinners
    Spinner prioritySpinner;
    ArrayAdapter<CharSequence> priorityAdapter;

    Spinner statusSpinner;
    ArrayAdapter<CharSequence> statusAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        showDialogOnButtonClick();

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);


        updateName = (EditText) findViewById(R.id.tvItemName);
        String textToEdit = getIntent().getStringExtra("nametext");
        int arrPosition = getIntent().getIntExtra("position", 0);

        String newStr = textToEdit + " at position " + arrPosition;
        updateName.setText(newStr);

        int textLength = updateName.getText().length();
        updateName.setSelection(textLength, textLength);

        dueDate = (TextView) findViewById(R.id.tvSelectedDueDate);

        // Priority Spinnter
        prioritySpinner = (Spinner) findViewById(R.id.spPriority);
        priorityAdapter = ArrayAdapter.createFromResource(this,R.array.priority_values,android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getBaseContext(),parent.getItemAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Statis Spinnter
        statusSpinner = (Spinner) findViewById(R.id.spStatus);
        statusAdapter = ArrayAdapter.createFromResource(this,R.array.status_values,android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void showDialogOnButtonClick(){
        btn = (Button) findViewById(R.id.btnSelectDate);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);

                    }
                }
        );

    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Toast.makeText(EditItemActivity.this,year_x + "/" + month_x + "/" + day_x,Toast.LENGTH_LONG).show();
            String selectedDate =  month_x + "/" + day_x + "/" + year_x;
            dueDate.setText(selectedDate);
        }
    };

    public void onSubmit(View view) {

            Intent newData = new Intent();

            String newText = updateName.getText().toString();

            newData.putExtra("nametext", newText);
            newData.putExtra("code", 200);

            setResult(RESULT_OK, newData);
            finish();
    }
}