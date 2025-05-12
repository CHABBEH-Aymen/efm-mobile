package com.example.efm.models

import androidx.lifecycle.ViewModel
import com.example.efm.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel: ViewModel(){

    private var _tasks = MutableStateFlow(emptyList<Task>())
    val tasks: MutableStateFlow<List<Task>> get() = _tasks

    init {
        val list = mutableListOf<Task>()
        for (i in 1..10){
            list.add(Task(i, "Task $i", false, priority = "Low"))
        }
        _tasks.value = list
    }

    fun createTask(task: Task){
        _tasks.value = _tasks.value.toMutableList().apply { add(task) }
    }

    fun updateTask(task: Task)
    {
        _tasks.value = _tasks.value.map {
            if (it.id == task.id) it.copy(
                title = task.title,
                completed = task.completed,
                priority = task.priority
            )else it
        }.toMutableList()
    }

    fun destroyTask(id: Int){
        _tasks.value = _tasks.asStateFlow().value.filter { task -> task.id != id }.toMutableList()
    }
}