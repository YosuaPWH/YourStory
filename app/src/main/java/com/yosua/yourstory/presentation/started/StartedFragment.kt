package com.yosua.yourstory.presentation.started

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yosua.yourstory.R
import com.yosua.yourstory.databinding.FragmentHomeBinding
import com.yosua.yourstory.databinding.FragmentStartedBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartedFragment : Fragment() {

    private var _binding: FragmentStartedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartedBinding.inflate(inflater, container, false)

        binding.btnToLoginPage.setOnClickListener {
            val toLogin = StartedFragmentDirections.actionStartedFragmentToLoginFragment()
            findNavController().navigate(toLogin)
        }

        binding.btnToRegisterPage.setOnClickListener {
            val toRegister = StartedFragmentDirections.actionStartedFragmentToRegisterFragment()
            findNavController().navigate(toRegister)
        }

        return binding.root
    }

}