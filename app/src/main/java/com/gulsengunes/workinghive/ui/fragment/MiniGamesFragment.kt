package com.gulsengunes.workinghive.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gulsengunes.workinghive.R
import com.gulsengunes.workinghive.core.data.model.Task
import com.gulsengunes.workinghive.databinding.FragmentChampionTasksBinding
import com.gulsengunes.workinghive.databinding.FragmentMiniGamesBinding
import com.gulsengunes.workinghive.databinding.FragmentTaskManagerBinding
import com.gulsengunes.workinghive.ui.adapter.TaskAdapter
import com.gulsengunes.workinghive.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MiniGamesFragment : Fragment() {
    private lateinit var binding: FragmentMiniGamesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMiniGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

}