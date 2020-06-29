package com.codurance.training.tasks;

        import java.util.ArrayList;
        import java.util.List;

public final class Project {
    private final String id;
    private List<Task> tasks;

    public Project(String id) {
        this.id = id;
        this.tasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}