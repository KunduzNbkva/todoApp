package com.geektech.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThirdActivity extends AppCompatActivity {
    EditText editTitle;
    EditText editDescription;
    TextView editDeadline;
    Task task;
    Button save,delete;
    Calendar calendar=Calendar.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        editTitle=findViewById(R.id.edit_title);
        editDescription=findViewById(R.id.edit_description);
        editDeadline=findViewById(R.id.edit_deadline);
        editDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nyear= calendar.get(calendar.YEAR);
                int nmonth= calendar.get(calendar.MONTH);
               int nday= calendar.get(calendar.DAY_OF_MONTH);


                new DatePickerDialog(editDeadline.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        editDeadline.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },nyear,nmonth,nday).show();

            }
        });

        delete=findViewById(R.id.delete);
        save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();

                if (editTitle.getText().toString().trim().equals("")) {
                    showMessage("Input title please");
                    return;
                } else {
                    task.title = editTitle.getText().toString().trim();
                }

                if (editDescription.getText().toString().trim().equals("")) {
                    showMessage("Input description please");
                    return;
                } else {
                    task.description = editDescription.getText().toString().trim();
                }

                task.deadline = calendar.getTime();


                Intent intent = new Intent();
                intent.putExtra("new_task", task);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });


        Intent i=getIntent();
         task= (Task)i.getSerializableExtra("key");
         editTitle.setText(task.title);
        editDescription.setText(task.description);

        editDeadline.setText(new SimpleDateFormat("dd-MM-yyyy").format(task.deadline));


    }
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
