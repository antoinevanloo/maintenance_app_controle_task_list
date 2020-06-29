package com.codurance.training.tasks;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {

    @Test
    public void check_id_is_correct_test(){
        Deadline uneDeadline = new Deadline(42);
        assertEquals(42,uneDeadline.getId());
    }

    @Test
    public void check_date_is_correct_test(){
        LocalDate datetime = LocalDate.of(2017, 1, 14);
        Deadline uneDeadline = new Deadline(42,datetime);

        assertEquals(LocalDate.of(2017, 1, 14),uneDeadline.getDate());
    }

    @Test
    public void check_date_is_today_test(){
        Deadline uneDeadline = new Deadline(42);
        LocalDate dateTime = LocalDate.now();
        uneDeadline.setDate(dateTime);

        assertEquals(true, uneDeadline.isDateToday());
    }
}
