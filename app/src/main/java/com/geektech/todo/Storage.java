package com.geektech.todo;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Storage {


    static void save(ArrayList<Task> tasks, Context context) {
        Gson gson = new Gson();
        String jsonTasks = gson.toJson(tasks);
        SharedPreferences sp = context.getSharedPreferences("my_sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("tasks", jsonTasks);
        editor.commit();
    }

    static ArrayList<Task> read(Context context) {
        SharedPreferences sp = context.getSharedPreferences("my_sp", Context.MODE_PRIVATE);
        String jsonTasks = sp.getString("tasks", "");

        if (jsonTasks == "") { return new ArrayList<>(); }

        Gson gson = new Gson();



        ArrayList<Task> tasks = gson.fromJson(jsonTasks, new TypeToken<ArrayList<Task>>(){}.getType());
        return tasks;
    }


}
