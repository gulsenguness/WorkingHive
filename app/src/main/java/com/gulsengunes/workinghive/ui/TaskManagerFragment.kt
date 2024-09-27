package com.gulsengunes.workinghive.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gulsengunes.workinghive.data.model.Task
import com.gulsengunes.workinghive.databinding.FragmentTaskManagerBinding
import com.gulsengunes.workinghive.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskManagerFragment : Fragment() {

    private lateinit var binding: FragmentTaskManagerBinding

    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    private lateinit var taskViewModel: TaskViewModel // ViewModel ekleniyor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskManagerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel'in bağlanması
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        setupRecyclerView()

        // LiveData ile veriler gözlemleniyor
        taskViewModel.allTasks.observe(viewLifecycleOwner, { taskList ->
            tasks.clear()
            tasks.addAll(taskList)
            taskAdapter.notifyDataSetChanged() // RecyclerView'i güncelle
        })

        binding.buttontextinput.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.textinput.text.toString()
            val priority = binding.taskPrioritySpinner.selectedItem?.toString() ?: "Low"

            if (title.isNotBlank() && description.isNotBlank()) {
                // Yeni görev oluşturup ViewModel üzerinden ekliyoruz
                val newTask = Task(title = title, description = description, priority = priority)
                taskViewModel.addTask(newTask) // Veritabanına kaydediyoruz

                // Text alanlarını temizle
                binding.title.text.clear()
                binding.textinput.text.clear()
            } else {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(tasks)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = taskAdapter
    }
}