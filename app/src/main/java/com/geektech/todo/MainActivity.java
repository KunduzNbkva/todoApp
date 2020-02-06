package com.geektech.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnVHClick{

    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskAdapter adapter;
    private int editIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Task> savedTasks = Storage.read(this);
        tasks = savedTasks;

        adapter = new TaskAdapter(tasks, this);
        adapter.activity=this;

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        Button button = findViewById(R.id.open);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, 42);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 42) {
            Task task = (Task) data.getSerializableExtra("task");
            tasks.add(task);
        }
        if (resultCode == RESULT_OK && requestCode == 2) {
            if (data.getSerializableExtra("new_task")!=null){
            Task task = (Task) data.getSerializableExtra("new_task");
            tasks.remove(editIndex);
            tasks.add(editIndex, task);

        } else{
            tasks.remove(editIndex);
            }
        }
        adapter.notifyDataSetChanged();
        Storage.save(tasks, this);
    }

    @Override
    public void onItemClick(int position) {
        editIndex = position;
        Intent intent=new Intent(this,ThirdActivity.class);
        intent.putExtra("key",tasks.get(position));
        startActivityForResult(intent,2);

    }
}
