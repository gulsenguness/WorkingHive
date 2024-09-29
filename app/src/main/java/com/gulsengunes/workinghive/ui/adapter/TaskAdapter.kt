package com.gulsengunes.workinghive.ui.adapter

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.gulsengunes.workinghive.core.data.model.Task
import com.gulsengunes.workinghive.databinding.ItemTaskManagerListBinding
import com.gulsengunes.workinghive.ui.viewmodel.TaskViewModel
import java.util.Calendar

class TaskAdapter(
    private var tasks: List<Task>,
    private val viewModel: TaskViewModel,

    ) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: ItemTaskManagerListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.titleid.text = task.title
            binding.descriptionid.text = task.description
//            binding.datatext.text = task.dueDate ?: "Son tarih  belirlenmemiş"

            binding.btnDelete.setOnClickListener {
                viewModel.deleteTask(task)
            }

            binding.btnbitti.setOnClickListener {
                viewModel.markTaskAsCompleted(task)
            }

            binding.btnbitti.visibility = if (task.isCompleted) View.GONE else View.VISIBLE

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

    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged() // RecyclerView'ın verileri güncellemesi için
    }

}