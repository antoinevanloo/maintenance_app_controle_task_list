package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {

    @Test
    public void check_id_is_correct_test(){
        Deadline uneDeadline = new Deadline(42);
        assertEquals(42,uneDeadline.getId());
    }
}
