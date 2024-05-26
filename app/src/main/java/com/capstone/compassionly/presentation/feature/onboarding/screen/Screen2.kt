package com.capstone.compassionly.presentation.feature.onboarding.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.compassionly.databinding.FragmentScreen2Binding
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.onboarding.OnBoardingActivity
import com.capstone.compassionly.presentation.feature.onboarding.viewmodel.OnBoardViewModel
import com.capstone.compassionly.repository.di.StateInjection

class Screen2 : Fragment() {

    private var _binding: FragmentScreen2Binding? = null
    private val binding get() = _binding!!
    private val onBoardViewModel : OnBoardViewModel by viewModels {
        StateInjection.onBoardInjection(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen2Binding.inflate(inflater, container, false)

        val viewPager = (requireActivity() as OnBoardingActivity).getCurrentPager()

        binding.btnBack.setOnClickListener {
            viewPager.currentItem = 0
        }

        binding.btnSignIn.setOnClickListener {
            val intentToLogin = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intentToLogin)
            requireActivity().finish()
            onBoardViewModel.updateOnBoardState()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}