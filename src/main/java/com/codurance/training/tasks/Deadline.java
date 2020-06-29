package com.codurance.training.tasks;

import java.time.LocalDateTime;

public class Deadline {

    private final long id;
    private LocalDateTime date;

    public Deadline(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isDateToday(){
        LocalDateTime today = LocalDateTime.now();
        return today.getDayOfMonth() == date.getDayOfMonth() && today.getMonth() == date.getMonth() && today.getYear() == date.getYear();
    }
}
