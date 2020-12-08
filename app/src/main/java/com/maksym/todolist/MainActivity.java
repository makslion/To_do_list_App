package com.maksym.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.maksym.todolist.database.TaskEntity;
import com.maksym.todolist.database.TaskModel;
import com.maksym.todolist.database.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskViewModel taskViewModel;
    private List<TaskModel> tasks;
    private TextView toDoListTextView;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findReferences();
    }


    private void findReferences(){
        tasks = new ArrayList<>();
        recyclerView = findViewById(R.id.taskRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toDoListTextView = findViewById(R.id.doDoListView);
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        populateRecycler();
    }


    private void populateRecycler(){
        final TaskAdapter adapter = new TaskAdapter(this);

        taskViewModel.getTasksList().observe(this, new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(List<TaskEntity> taskEntities) {
                for (TaskEntity entity : taskEntities){
                    Log.d(TAG,"Adding new task: "+entity.getTaskName());

                    tasks.add(new TaskModel(
                            entity.getTaskName(),
                            entity.getTaskDescription()
                    ));
                }
                adapter.setTasks(tasks);
                recyclerView.setAdapter(adapter);

                if (!taskEntities.isEmpty()) toDoListTextView.setText("To do list:");
                else  toDoListTextView.setText("You done 'em all! YEEEY!!!");
            }
        });
    }


    public void addNewTaskButtonListener(View view){
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);
    }
}