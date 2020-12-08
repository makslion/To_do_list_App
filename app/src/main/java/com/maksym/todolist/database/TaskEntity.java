package com.maksym.todolist.database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//SQLite thing for chord table
@Entity(tableName = "task_table")
public class TaskEntity
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int taskId;

    @NonNull
    @ColumnInfo(name = "taskName")
    private String taskName;

    @NonNull
    @ColumnInfo (name = "taskDescription")
    private String taskDescription;



    public TaskEntity(@NonNull String taskName, @NonNull String taskDescription)
    {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }



    @NonNull
    public String getTaskDescription() { return taskDescription; }

    @NonNull
    public String getTaskName() {
        return taskName;
    }


    public int getTaskId() {
        return taskId;
    }

    @NonNull
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}