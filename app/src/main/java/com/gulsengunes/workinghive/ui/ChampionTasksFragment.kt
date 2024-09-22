package com.gulsengunes.workinghive.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gulsengunes.workinghive.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampionTasksFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_champion_tasks, container, false)
    }

}