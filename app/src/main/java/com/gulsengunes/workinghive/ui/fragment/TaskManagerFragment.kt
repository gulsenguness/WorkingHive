package com.gulsengunes.workinghive.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gulsengunes.workinghive.core.data.model.Task
import com.gulsengunes.workinghive.databinding.FragmentTaskManagerBinding
import com.gulsengunes.workinghive.ui.adapter.TaskAdapter
import com.gulsengunes.workinghive.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class TaskManagerFragment : Fragment() {

    private lateinit var binding: FragmentTaskManagerBinding
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskManagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        setupRecyclerView()

        taskViewModel.allTasks.observe(viewLifecycleOwner) { taskList ->
            tasks.clear()
            val incompleteTasks = taskList.filter { !it.isCompleted }
            tasks.addAll(incompleteTasks)
            taskAdapter.notifyDataSetChanged()
        }

        binding.datatext.setOnClickListener{
            showDate()
        }

        binding.buttontextinput.setOnClickListener {
            add()
        }
    }

    private fun add(){
        val title = binding.title.text.toString()
        val description = binding.textinput.text.toString()
        val priority = "Low"

        if (title.isNotBlank() && description.isNotBlank()) {
            val newTask = Task(title = title, description = description, priority = priority)
            taskViewModel.addTask(newTask)

            binding.title.text.clear()
            binding.textinput.text.clear()
        } else {
            Toast.makeText(
                requireContext(),
                "Lütfen tüm alanları doldurun!",
                Toast.LENGTH_SHORT
            ).show()
        }
        taskViewModel.allTasks.observe(viewLifecycleOwner) { taskList ->
            tasks.clear()
            tasks.addAll(taskList.filter { !it.isCompleted })
            taskAdapter.notifyDataSetChanged()
        }

        taskViewModel.completedTasks.observe(viewLifecycleOwner) { completedTaskList ->
            // İkinci sayfadaki görevleri burada güncelleyebilirsin
        }
    }

    private fun showDate(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(),
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val formattedDate = "${selectedDay}/${selectedMonth + 1}/$selectedYear" // Tarihi formatla
                binding.datatext.setText(formattedDate)
            }, year, month, day)

        datePickerDialog.show()

    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(tasks, taskViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = taskAdapter
    }
}