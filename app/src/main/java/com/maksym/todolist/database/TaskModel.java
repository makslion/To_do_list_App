package com.maksym.todolist.database;

public class TaskModel {
    private String taskName, taskDescription;

    public TaskModel() {
        taskName = "dummy name";
        taskDescription = "dummy desc";
    }

    public TaskModel(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}


