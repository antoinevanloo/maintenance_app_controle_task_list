package com.codurance.training.tasks;

import java.time.LocalDateTime;

public class Deadline {

    private int id;
    private LocalDateTime date;

    public Deadline(){

    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
