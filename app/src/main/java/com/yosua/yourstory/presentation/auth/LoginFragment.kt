package com.yosua.yourstory.presentation.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yosua.yourstory.R
import com.yosua.yourstory.data.remote.request.LoginRequest
import com.yosua.yourstory.databinding.FragmentLoginBinding
import com.yosua.yourstory.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val loginRequest = LoginRequest(
            email = binding.edLoginEmail.text.toString(),
            password = binding.edLoginPassword.text.toString()
        )

        authViewModel.login(loginRequest).observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
                    Log.d("LoginFrag", "Success: ${it.data}")
                    val toHome = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
                    findNavController().navigate(toHome)
                }
                is Result.Loading -> {

                }
                is Result.Error -> {
                    if (it.error == "User tidak ditemukan!") {
                        binding.edLoginPassword.getEditTextLayout().error = null
                        binding.edLoginEmail.getEditTextLayout().error = it.error
                    } else {
                        binding.edLoginPassword.getEditTextLayout().error = it.error
                        binding.edLoginEmail.getEditTextLayout().error = null
                    }
                    Log.d("LoginFrag", "Error: ${it.error}")
                }
            }
        }
    }

    private fun TextInputEditText.getEditTextLayout() : TextInputLayout {
        return this.parent.parent as TextInputLayout
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}