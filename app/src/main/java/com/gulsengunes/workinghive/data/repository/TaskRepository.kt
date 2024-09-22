package com.gulsengunes.workinghive.data.repository

import com.gulsengunes.workinghive.data.database.TaskDao
import com.gulsengunes.workinghive.data.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks():Flow<List<Task>>{
        return taskDao.getAllTasks()
    }

    fun getCompletedTasks(): Flow<List<Task>> {
        return taskDao.getCompletedTasks()
    }

    fun getPendingTasks(): Flow<List<Task>> {
        return taskDao.getPendingTasks()
    }

    suspend fun addTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}