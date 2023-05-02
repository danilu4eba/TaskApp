package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.app.AlertDialog.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.model.Task
import com.example.taskapp.ui.home.adapter.TaskAdapter


class HomeFragment( ) : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = TaskAdapter(onLongClick = this::onLongClick, onCLick = this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root ?: binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val list = App.db.taskDao().getAll()
        adapter.addTasks(list)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

    }
    private fun onLongClick(task: Task){
        val aboutDialog: AlertDialog = Builder(requireContext())
            .setMessage("Delete?")
            .setNegativeButton("cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("OK") { dialog, which ->

                App.db.taskDao().delete(task)
                val list = App.db.taskDao().getAll()
                adapter.addTasks(list)
            }.create()

        aboutDialog.show()
    }
private fun onClick (task: Task){
   App.db.taskDao().update(task)
    val list = App.db.taskDao().getAll()
    adapter.addTasks(list)
    findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment(task))
}
}


