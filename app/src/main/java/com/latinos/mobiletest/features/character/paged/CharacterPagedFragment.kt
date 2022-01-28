package com.latinos.mobiletest.features.character.paged

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.latinos.mobiletest.R
import com.latinos.mobiletest.databinding.FragmentCharacterPagedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterPagedFragment : Fragment() {

    private val viewModel: CharacterPagedViewModel by viewModels()

    private var _binding: FragmentCharacterPagedBinding? = null
    private val binding get() = _binding!!

    private val characterPagedAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_character_paged,
            container,
            false
        )

        //binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupCharacterList()
        setupToolbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterPaged.collectLatest { characterPagedAdapter.submitData(it) }
        }
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.layoutToolbar.toolbar)
        NavigationUI.setupWithNavController(
            binding.layoutToolbar.collapsingToolbarLayout,
            binding.layoutToolbar.toolbar,
            findNavController(),
            AppBarConfiguration.Builder(R.id.navigation_character_paged, R.id.navigation_dashboard)
                .build()
        )
    }

    private fun setupCharacterList() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = characterPagedAdapter
            characterPagedAdapter.clickListener = { character ->
                //CharacterPagedFragmentDirections
                findNavController().navigate(
                    CharacterPagedFragmentDirections.actionNavigationCharacterPagedToNavigationCharacterDetail(
                        character.id, character.name))
            }
        }
    }
}