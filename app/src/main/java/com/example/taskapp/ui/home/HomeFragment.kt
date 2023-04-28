package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.app.AlertDialog.*
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.ui.home.adapter.TaskAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root ?: binding.root
       /* binding.recyclerView.item {
            val aboutDialog: AlertDialog = Builder(requireContext())
                .setMessage("fdfddf")
                .setNegativeButton("cancel") { dialog, which ->
                    dialog.dismiss()
                }
                .setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }.create()

            aboutDialog.show()
        }*/
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

}


