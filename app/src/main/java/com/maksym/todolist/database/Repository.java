package com.maksym.todolist.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private TaskDAO taskDAO;


    public Repository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDAO = db.taskDAO();
    }

    public LiveData<List<TaskEntity>> getTasks(String taskName){
        return taskDAO.getTasks(taskName);
    }

    public LiveData<List<TaskEntity>> getTasksList() {
        return taskDAO.getTasksList();
    }

    public void deleteTask(String taskName) {
        new deleteTaskAsyncTask(taskDAO).execute(taskName);
    }


    public void insert(TaskEntity task) {
        new insertAsyncTask(taskDAO).execute(task);
    }


    private static class insertAsyncTask extends AsyncTask<TaskEntity, Void, Void> {

        private TaskDAO taskDAO;

        insertAsyncTask(TaskDAO dao) {
            taskDAO = dao;
        }

        @Override
        protected Void doInBackground(final TaskEntity... params) {
            taskDAO.insert(params[0]);
            return null;
        }
    }

    private static class deleteTaskAsyncTask extends AsyncTask<String, Void, Void>
    {

        private TaskDAO taskDAO;

        deleteTaskAsyncTask(TaskDAO dao)
        {
            taskDAO = dao;
        }

        @Override
        protected Void doInBackground(final String... params)
        {
            taskDAO.deleteTask(params[0]);
            return null;
        }
    }


}