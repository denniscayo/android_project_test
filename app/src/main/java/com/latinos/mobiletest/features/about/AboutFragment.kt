package com.latinos.mobiletest.features.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.latinos.mobiletest.R
import com.latinos.mobiletest.databinding.FragmentAboutBinding
import com.latinos.mobiletest.features.base.BaseFragment

class AboutFragment : BaseFragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_about,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(binding.layoutToolbar.toolbar, binding.layoutToolbar.collapsingToolbarLayout)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}