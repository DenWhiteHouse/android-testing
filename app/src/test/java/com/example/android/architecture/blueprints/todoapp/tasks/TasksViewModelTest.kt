package com.example.android.architecture.blueprints.todoapp.tasks


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.Matchers.not
import org.hamcrest.core.IsNull.nullValue

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class TasksViewModelTest {

    private lateinit var tasksRepository: FakeTestRepository
    private lateinit var tasksViewModel: TasksViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewTask_setsNewTaskEvent(){
        tasksRepository = FakeTestRepository()
        tasksViewModel = TasksViewModel(tasksRepository)

        @Before
        fun setupViewModel() {
            // We initialise the tasks to 3, with one active and two completed

            val task1 = Task("Title1", "Description1")
            val task2 = Task("Title2", "Description2", true)
            val task3 = Task("Title3", "Description3", true)
            tasksRepository.addTasks(task1, task2, task3)
        }

        //Given a fresh ViewModel
        val observer = Observer<Event<Unit>>{}
        try {
            //Observe the LiveData forever
            tasksViewModel.newTaskEvent.observeForever(observer)

            // When adding a new task
            tasksViewModel.addNewTask()

            // Then the new task event is triggered
            val value = tasksViewModel.newTaskEvent.value
            assertThat(value?.getContentIfNotHandled(), (not(nullValue())))
        } finally {
            // Whatever happen, don't forget to remove the observer!!!
            tasksViewModel.newTaskEvent.removeObserver(observer)
        }
        }

    @Test
    fun addNewTask_setsNewTaskEventSimple() {
        tasksRepository = FakeTestRepository()
        tasksViewModel = TasksViewModel(tasksRepository)
        //Given a fresh ViewModel
        // When adding a new task
        tasksViewModel.addNewTask()
        // Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled(), not(nullValue()))

    }
    }
