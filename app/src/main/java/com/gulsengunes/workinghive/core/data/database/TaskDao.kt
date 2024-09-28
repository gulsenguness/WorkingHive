package com.gulsengunes.workinghive.core.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow
import com.gulsengunes.workinghive.core.data.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    // Tüm görevleri alma
    @Query("SELECT * FROM tasks ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Task>>

    // Tamamlanan görevleri alma
    @Query("SELECT * FROM tasks WHERE isCompleted = 1 ORDER BY id ASC")
    fun getCompletedTasks(): Flow<List<Task>>

    // Tamamlanmamış görevleri alma
    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY id ASC")
    fun getPendingTasks(): Flow<List<Task>>

}