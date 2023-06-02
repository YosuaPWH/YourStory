package com.yosua.yourstory.presentation.add_story

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yosua.yourstory.R
import com.yosua.yourstory.databinding.FragmentAddStoryBinding
import com.yosua.yourstory.utils.Result
import com.yosua.yourstory.utils.Util.uriToFile
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@AndroidEntryPoint
class AddStoryFragment : Fragment() {

    private var _binding: FragmentAddStoryBinding? = null
    private val binding get() = _binding!!
    private val addStoryViewModel: AddStoryViewModel by viewModels()
    private var getFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddStoryBinding.inflate(inflater, container, false)

        setupButton()

        return binding.root
    }

    private fun setupButton() {
        binding.btnGallery.setOnClickListener {
            launchGallery()
        }

        binding.btnPost.setOnClickListener {
            postStory()
        }
    }

    private fun postStory() {
        val description =
            binding.inputDescription.text.toString().toRequestBody("text/plain".toMediaType())

        val file = getFile as File

        val imagePost = file.asRequestBody("image/*".toMediaTypeOrNull())
        val imageMultiPart = MultipartBody.Part.createFormData("image", file.name, imagePost)

        addStoryViewModel.createStory(
            image = imageMultiPart,
            description = description
        ).observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    Toast.makeText(requireContext(), "Post story sukses", Toast.LENGTH_SHORT).show()
                    Log.d("SUKSES ADD STORY", it.data)
                    val toHome = AddStoryFragmentDirections.actionAddStoryFragmentToNavigationHome()
                    findNavController().navigate(toHome)
                }

                is Result.Loading -> {

                }

                is Result.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    Log.e("AddStory", it.error)
                }
            }
        }

    }

    private fun launchGallery() {
        pickPhotoFromGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val pickPhotoFromGallery =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                val file = uriToFile(uri, requireActivity())

                getFile = file
                binding.ivStory.setImageBitmap(BitmapFactory.decodeFile(file.path))
                binding.ivStory.visibility = View.VISIBLE
            }
        }

}