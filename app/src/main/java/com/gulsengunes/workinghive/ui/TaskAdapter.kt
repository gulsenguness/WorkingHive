package com.gulsengunes.workinghive.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gulsengunes.workinghive.data.model.Task
import com.gulsengunes.workinghive.databinding.ItemTaskManagerListBinding

class TaskAdapter(
    private var tasks: List<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: ItemTaskManagerListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.titleid.text = task.title
            binding.descriptionid.text = task.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            ItemTaskManagerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

//    fun updateTasks(newTasks: List<Task>) {
//        tasks = newTasks
//        notifyDataSetChanged() // RecyclerView'ın verileri güncellemesi için
//    }

}