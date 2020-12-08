package com.maksym.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.maksym.todolist.database.TaskEntity;
import com.maksym.todolist.database.TaskModel;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter <TaskAdapter.TaskViewHolder>
{

    class TaskViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView taskNameView;

        private TaskViewHolder(View itemView)
        {
            super(itemView);
            taskNameView = itemView.findViewById(R.id.taskNameLisItem);
        }
    }


    private final LayoutInflater inflater;

    private List<TaskModel> tasks; // Cached copy of words

    public TaskAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = inflater.inflate(R.layout.task_list_item, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView taskName = v.findViewById(R.id.taskNameLisItem);
                Intent it = new Intent(v.getContext(), Task.class);
                it.putExtra(Constants.EXTRA_TASK_NAME, taskName.getText());
                v.getContext().startActivity(it);
            }
        });

        return new TaskViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position)
    {
        TaskModel current = tasks.get(position);
        holder.taskNameView.setText(current.getTaskName());
    }

    void setTasks(List<TaskModel> tasks)
    {
        this.tasks = tasks;
        notifyDataSetChanged();
    }



    // getItemCount() is called many times, and when it is first called,
    // chords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount()
    {
        if (tasks != null)
            return tasks.size();
        else return 0;
    }
}