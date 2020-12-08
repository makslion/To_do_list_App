package com.maksym.todolist.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO
{
    @Insert
    void insert(TaskEntity task);

    @Query("SELECT * from task_table")
    LiveData<List<TaskEntity>> getTasksList();

    @Query("SELECT * FROM task_table WHERE taskName = :taskName")
    LiveData<List<TaskEntity>> getTasks(String taskName);

    @Query("DELETE FROM task_table WHERE taskName = :taskName")
    void deleteTask(String taskName);

    @Query("DELETE FROM task_table")
    void deleteAll();
}