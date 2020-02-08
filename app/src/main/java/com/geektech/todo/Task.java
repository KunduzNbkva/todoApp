package com.geektech.todo;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    public String title;
    public String description;
    public Date deadline;// TODO: Change to Date type
    public boolean checked;

}
