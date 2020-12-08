package com.maksym.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.maksym.todolist.database.TaskEntity;
import com.maksym.todolist.database.TaskModel;
import com.maksym.todolist.database.TaskViewModel;

import java.util.List;

public class Task extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private TaskModel taskModel;

    private TextView taskNameView;
    private EditText taskDescriptionEditText;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        Intent intent = getIntent();
        String extraTaskName = intent.getStringExtra(Constants.EXTRA_TASK_NAME);
        Log.d(TAG, extraTaskName);

        findReferences();
        getDescription(extraTaskName);
    }


    private void findReferences(){
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskNameView = findViewById(R.id.taskNameTextView);
        taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText);
    }


    private void getDescription(final String taskName){
        Log.d(TAG, "describing "+taskName);
        taskViewModel.getTasks(taskName).observe(this, new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(List<TaskEntity> taskEntities) {

                if (taskEntities.size() > 0) {
                    taskModel = new TaskModel(taskEntities.get(0).getTaskName(), taskEntities.get(0).getTaskDescription());

                    taskNameView.setText(taskModel.getTaskName());
                    taskDescriptionEditText.setText(taskModel.getTaskDescription());
                }
            }
        });
    }


    public void deleteTaskButtonListener (View view){
        taskViewModel.deleteTask(taskModel.getTaskName());

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}