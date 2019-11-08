package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest{

    // If there's no completed task and one active task,
    // then there are 100% percent active tasks and 0% completed

    @Test
    fun getActiveAndCompletedStats_noCompleted_returns_ZeroHundred(){

        //GIVEN a list of tasks with a single, active, task
        val tasks = listOf<Task>(
                Task("title","description",false)
        )

        //WHEN you call getActiveAndCompletedStats
        val result = getActiveAndCompletedStats(tasks)

        //THEN there are 0% completed tasks and 100% active tasks
        assertEquals(0f, result.completedTasksPercent)
        assertThat(result.completedTasksPercent, `is` (0f))
        assertEquals(100f,result.activeTasksPercent)

    }

    // If there's 2 completed task and 3 active tasks,
    // thene there are 40% percent active tasks and 6% complete
    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty(){

        val tasks = listOf<Task>(
                Task("title","description",true),
                Task("title","description",true),
                Task("title","description",false),
                Task("title","description",false),
                Task("title","description",false)
        )

        val result = getActiveAndCompletedStats(tasks)
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f,result.activeTasksPercent)

    }

    @Test
    fun getActiveAndCompletedStats_empty_returnZeros(){

        val tasks = emptyList<Task>()

        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f,result.activeTasksPercent)

    }

    @Test
    fun getActiveAndCompletedStats_null_returnZeros(){

        val tasks = null

        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f,result.activeTasksPercent)

    }



}