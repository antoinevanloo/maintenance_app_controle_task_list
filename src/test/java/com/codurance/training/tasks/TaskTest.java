package com.codurance.training.tasks;

import static org.junit.Assert.*;
import org.junit.Test;

public class TaskTest {

    @Test
    public void check_id_is_correct_test(){
        Task uneTask = new Task(42,"ma petite description", true);
        assertEquals(42,uneTask.getId());
    }

    @Test
    public void check_if_description_is_correct(){
        Task uneTask = new Task(1,"T'es mauvais Jack", false);
        assertEquals("T'es mauvais Jack",uneTask.getDescription());
    }
}
