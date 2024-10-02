package com.gulsengunes.workinghive.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.gulsengunes.workinghive.R
import com.gulsengunes.workinghive.databinding.FragmentLuckyWheelBinding
import com.gulsengunes.workinghive.databinding.FragmentTaskManagerBinding
import com.gulsengunes.workinghive.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ActivityRetainedComponentBuilder

@AndroidEntryPoint
class LuckyWheelFragment : Fragment() {
    private val taskViewModel: TaskViewModel by viewModels()
    lateinit var binding: FragmentLuckyWheelBinding
    private lateinit var wheelbutton: Button
    private lateinit var lottieView: LottieAnimationView
    private var wheelCount = 0
    private var wheelMax = 3


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLuckyWheelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottieView = view.findViewById(R.id.giftAnimation)
        setupWheelButton(view)
        observeCompletedTasks()
        setupSpinButtonClickListener()
    }

    private fun setupWheelButton(view: View) {
        wheelbutton = view.findViewById(R.id.wheelButton)
        wheelbutton.isEnabled = false
    }

    private fun setupSpinButtonClickListener() {
        wheelbutton.setOnClickListener {
            if (wheelCount < wheelMax) {
                lottieView.playAnimation()
                val randomPoints: Int = (1..100).random()
                Toast.makeText(context, "Congratulations!\nYou earned $randomPoints points!", Toast.LENGTH_SHORT).show()
                wheelCount++
            } else {
                Toast.makeText(context, "Sorry, Complete new quests.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeCompletedTasks() {
        taskViewModel.completedTasks.observe(viewLifecycleOwner, Observer { tasks ->
            val completedTaskCount = tasks.size

            if (completedTaskCount > 0 && completedTaskCount % 3 == 0) {
                if (completedTaskCount >= 6) {
                    wheelCount = 0
                    wheelMax = 3
                }
                wheelbutton.isEnabled = true
            } else {
                wheelbutton.isEnabled = false
            }
        })
    }

}