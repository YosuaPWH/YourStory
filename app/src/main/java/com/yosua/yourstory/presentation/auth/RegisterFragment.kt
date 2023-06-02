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
import com.yosua.yourstory.data.remote.request.RegisterRequest
import com.yosua.yourstory.databinding.FragmentLoginBinding
import com.yosua.yourstory.databinding.FragmentRegisterBinding
import com.yosua.yourstory.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val registerRequest = RegisterRequest(
            name = binding.edRegisterName.text.toString(),
            email = binding.edRegisterEmail.text.toString(),
            password = binding.edRegisterPassword.text.toString()
        )

        authViewModel.register(registerRequest).observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    Toast.makeText(requireContext(), "Register Berhasil, Segera Login", Toast.LENGTH_SHORT).show()
                    Log.d("RegisterFrag", "Success: ${it.data}")
                    val toLogin = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    findNavController().navigate(toLogin)
                }
                is Result.Loading -> {

                }
                is Result.Error -> {
                    if (it.error.contains("email")) {
                        binding.edRegisterEmail.getEditTextLayout().error = it.error
                    }

                    Log.d("RegisterFrag", "Error: ${it.error}")
                }
            }
        }
    }

    private fun TextInputEditText.getEditTextLayout(): TextInputLayout {
        return this.parent.parent as TextInputLayout
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}