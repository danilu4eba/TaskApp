package com.example.taskapp.ui.profile


import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import com.example.taskapp.data.local.Pref
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.utils.loadImage
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.contracts.contract

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref


    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.profileImage.loadImage(pref.getImg())
        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.

                if (uri != null) {
                    pref.saveImg(uri.toString())
                    Log.d("asas", "No media gtrgtrtrt")

                } else {
                    Log.d("asas", "No media selected")


                }
                binding.profileImage.loadImage(pref.getImg())
            }


        binding.etProfile.setText(pref.getName())
        binding.etProfile.addTextChangedListener {
            pref.saveName(binding.etProfile.text.toString())
        }

        binding.profileImage.setOnClickListener {
           pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

            Toast.makeText(requireContext(), "feferfer $", Toast.LENGTH_SHORT).show()

        }



    }
}















