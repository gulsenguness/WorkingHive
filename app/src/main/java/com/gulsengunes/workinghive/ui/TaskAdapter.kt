package com.gulsengunes.workinghive.ui


import androidx.recyclerview.widget.RecyclerView
import com.gulsengunes.workinghive.data.model.Task

class TaskAdapter(
    private val task: List<Task>,
    private val onTaskClicked: (Task) -> Unit
)  {

}