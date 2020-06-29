package com.codurance.training.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline {

    private final long id;
    private LocalDate date;

    public Deadline(long id){
        this.id = id;
    }

    public Deadline(long id, LocalDate date){
        this.id = id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDateToday(){
        LocalDate today = LocalDate.now();
        return today.getDayOfMonth() == date.getDayOfMonth() && today.getMonth() == date.getMonth() && today.getYear() == date.getYear();
    }
}
