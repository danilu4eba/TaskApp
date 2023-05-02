package com.example.taskapp.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.databinding.FragmentTaskBinding
import com.example.taskapp.model.Task

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private lateinit var navArgs: TaskFragmentArgs
    private var task: Task? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            navArgs = TaskFragmentArgs.fromBundle(it)
            task = navArgs.task
        }
        if(task!= null){
            binding.btnSave.text="Update"
            binding.etTitle.setText(task?.title)
            binding.etDescription.setText(task?.desc)
        }else{
            binding.btnSave.text="Save"
        }
        binding.btnSave.setOnClickListener {
            if(task!=null){
                update()
            }else{
                save()
            }


        }

    }


    private fun save() {
        val data = Task(

            title = binding.etTitle.text.toString(),
            desc = binding.etDescription.text.toString()

        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }

    fun update() {
        task?.title = binding.etTitle.text.toString()
        task?.desc = binding.etDescription.text.toString()
        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()
    }


    companion object {
        const val TASK_REQUEST = "task.requestKey"
        const val TASK_KEY = "task.key"
    }
}

