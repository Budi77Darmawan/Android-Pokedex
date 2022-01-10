package com.bd_drmwan.pokedex.presentation.splash_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bd_drmwan.commonextension.extensions.hideStatusBar
import com.bd_drmwan.commonextension.extensions.showStatusBar
import com.bd_drmwan.pokedex.R
import com.bd_drmwan.pokedex.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideStatusBar()

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000L)
            showStatusBar()
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }

}