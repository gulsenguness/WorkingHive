package com.gulsengunes.workinghive.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    val priority: String,
    var description: String,
    var isCompleted: Boolean = false
)