package com.geektech.todo;

import java.io.Serializable;

public class Task implements Serializable {
    public String title;
    public String description;
    public String deadline;
    public boolean checked;// TODO: Change to Date type

}
