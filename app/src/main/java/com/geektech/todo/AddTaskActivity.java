package com.geektech.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {


    EditText title;
    EditText description;
    TextView deadline;
    DatePickerDialog datePickerDialog;
    Calendar calendar=Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
        deadline = findViewById(R.id.task_deadline);
      deadline.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              int mYear= calendar.get(calendar.YEAR);
              int mMonth= calendar.get(calendar.MONTH);
              final int mDay= calendar.get(calendar.DAY_OF_MONTH);


              datePickerDialog=new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                      calendar.set(year,month,dayOfMonth);
                      deadline.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                  }
              },mYear,mMonth,mDay);
              datePickerDialog.show();

          }
      });

        Button saveBtn = findViewById(R.id.task_save);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();

                if (title.getText().toString().trim().equals("")) {
                    showMessage("Input title please");
                    return;
                } else {
                    task.title = title.getText().toString().trim();
                }

                if (description.getText().toString().trim().equals("")) {
                    showMessage("Input description please");
                    return;
                } else {
                    task.description = description.getText().toString().trim();
                }

                Log.e("---------", calendar.getTime()+"");
                task.deadline = calendar.getTime();


                Intent intent = new Intent();
                intent.putExtra("task", task);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
