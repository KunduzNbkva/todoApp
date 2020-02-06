package com.geektech.todo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    OnVHClick listener;

    ArrayList<Task> tasks;
    MainActivity activity;


    public TaskAdapter(ArrayList<Task> tasks, OnVHClick onVHClick) {
        this.tasks = tasks;
        this.listener = onVHClick;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_task, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.onBind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView deadline;
        CheckBox checkbox;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.vh_title);
            description = itemView.findViewById(R.id.vh_description);
            deadline = itemView.findViewById(R.id.vh_deadline);
            checkbox=itemView.findViewById(R.id.chekbox);
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void onBind(Task task) {
            title.setText(task.title);
            description.setText(task.description);
            deadline.setText(task.deadline);
        }
    }

}



