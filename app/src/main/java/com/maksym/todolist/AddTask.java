package com.maksym.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maksym.todolist.database.TaskEntity;
import com.maksym.todolist.database.TaskViewModel;

public class AddTask extends AppCompatActivity {
    private EditText taskNameEditText, taskDescEditText;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        findReferences();
    }


    private void findReferences(){
        taskDescEditText = findViewById(R.id.taskDescEditText);
        taskNameEditText = findViewById(R.id.taskNameEditText);
    }



    public void addTaskButtonListener(View view){
        String taskName = taskNameEditText.getText().toString();
        String taskDesc = taskDescEditText.getText().toString();

        Log.d(TAG,"task name "+taskName);
        Log.d(TAG, "task desc "+taskDesc);

        if (!taskName.equals("") && !taskDesc.equals("")){
            // add task
            TaskViewModel taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
            taskViewModel.insert(new TaskEntity(taskName, taskDesc));

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (taskName.equals("")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                taskNameEditText.setBackgroundTintList(getColorStateList(R.color.red));
            }
        }
        if (taskDesc.equals("")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                taskDescEditText.setBackgroundTintList(getColorStateList(R.color.red));
            }
        }

    }

}