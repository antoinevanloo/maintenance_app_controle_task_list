package com.codurance.training.tasks;

import static org.junit.Assert.*;
import org.junit.Test;

public class TaskTest {

    @Test
    public void check_id_is_correct_test(){

        Task uneTask = new Task(42,"ma petite description", true);

        assertEquals(42,uneTask.getId());
    }
}
