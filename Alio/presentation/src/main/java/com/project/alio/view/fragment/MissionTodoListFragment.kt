package com.project.alio.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.alio.R
import com.project.alio.databinding.FragmentMissionTodoListBinding

class MissionTodoListFragment : Fragment() {

    private lateinit var binding: FragmentMissionTodoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionTodoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.alarmTodoCheck.setOnClickListener {
            todoEmpty()
        }
    }

    private fun todoEmpty() {
        if (binding.alarmTodo1.text.isEmpty() || binding.alarmTodo2.text.isEmpty() || binding.alarmTodo3.text.isEmpty()) {
            Toast.makeText(context, "아직 할일을 모두 적지 않았어요.", Toast.LENGTH_SHORT).show()
        } else {
            activity?.finish()
        }
    }
}