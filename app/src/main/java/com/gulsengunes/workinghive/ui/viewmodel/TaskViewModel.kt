package com.gulsengunes.workinghive.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gulsengunes.workinghive.core.data.model.Task
import com.gulsengunes.workinghive.core.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    val allTasks: LiveData<List<Task>> = repository.getAllTasks().asLiveData()
    val completedTasks: LiveData<List<Task>> = repository.getCompletedTasks().asLiveData()


    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }


    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun markTaskAsCompleted(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(isCompleted = true)
            repository.updateTask(updatedTask)
        }
    }

}