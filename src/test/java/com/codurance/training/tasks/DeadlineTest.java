package com.codurance.training.tasks;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {

    @Test
    public void check_id_is_correct_test(){
        Deadline uneDeadline = new Deadline(42);
        assertEquals(42,uneDeadline.getId());
    }

    @Test
    public void check_date_is_correct_test(){
        LocalDateTime datetime = LocalDateTime.of(2017, 1, 14, 10, 34);
        Deadline uneDeadline = new Deadline(42,datetime);

        assertEquals(LocalDateTime.of(2017, 1, 14, 10, 34),uneDeadline.getDate());
    }

    @Test
    public void check_date_is_today_test(){
        Deadline uneDeadline = new Deadline(42);
        LocalDateTime dateTime = LocalDateTime.now();
        uneDeadline.setDate(dateTime);

        assertEquals(true, uneDeadline.isDateToday());
    }
}
