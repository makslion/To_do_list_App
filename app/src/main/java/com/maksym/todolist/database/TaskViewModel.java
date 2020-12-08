package com.maksym.todolist.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel
{
    private Repository repository;



    public TaskViewModel(@NonNull Application application)
    {
        super(application);

        repository = new Repository(application);
    }

    public LiveData<List<TaskEntity>> getTasksList() {
        return repository.getTasksList();
    }

    public LiveData<List<TaskEntity>> getTasks (String taskName){
        return repository.getTasks(taskName);
    }

    public void deleteTask(String taskName) {
        repository.deleteTask(taskName);
    }


    public void insert(TaskEntity task) {
        repository.insert(task);
    }
}