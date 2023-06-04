package com.yosua.yourstory.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yosua.yourstory.databinding.FragmentHomeBinding
import com.yosua.yourstory.domain.model.Story
import com.yosua.yourstory.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

    private val listStory: ArrayList<Story> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnToAddStory.setOnClickListener {
            val toAddStory = HomeFragmentDirections.actionNavigationHomeToAddStoryFragment()
            findNavController().navigate(toAddStory)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvStory.setHasFixedSize(true)
        binding.rvStory.layoutManager = LinearLayoutManager(context)

        homeViewModel.getAllStories().observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    listStory.clear()
                    listStory.addAll(it.data)
                    binding.rvStory.adapter = StoryAdapter(listStory)
                }

                is Result.Loading -> {

                }

                is Result.Error -> {
                    Log.e("HomeFragment", "Error: ${it.error}")
                    Toast.makeText(requireContext(), "Terjadi kesalahan saat meload data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}