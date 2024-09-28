package com.gulsengunes.workinghive.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gulsengunes.workinghive.core.data.model.Task
import com.gulsengunes.workinghive.databinding.FragmentChampionTasksBinding
import com.gulsengunes.workinghive.ui.adapter.TaskAdapter
import com.gulsengunes.workinghive.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampionTasksFragment : Fragment() {
    private lateinit var binding: FragmentChampionTasksBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter
    private val completedTasks = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChampionTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        setupRecyclerView()

        taskViewModel.completedTasks.observe(viewLifecycleOwner) { taskList ->
            completedTasks.clear()
            completedTasks.addAll(taskList)
            taskAdapter.updateTasks(completedTasks)
        }
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(completedTasks, taskViewModel)
        binding.championRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.championRecyclerview.adapter = taskAdapter
    }

}