package com.yosua.yourstory.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yosua.yourstory.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)

        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
                accountViewModel.logout().let {
                    if (it) {
                        val toStarted =
                            AccountFragmentDirections.actionNavigationAccountToStartedFragment()
                        findNavController().navigate(toStarted)
                    } else {
                        Toast.makeText(requireContext(), "Logout Gagal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return binding.root
    }

}