package com.latinos.mobiletest.features.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.latinos.data.utils.collectInLifeCycle
import com.latinos.mobiletest.R
import com.latinos.mobiletest.databinding.FragmentCharacterDetailBinding
import com.latinos.mobiletest.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_character_detail,
            container,
            false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar(binding.toolbar, binding.collapsingToolbarLayout)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val characterId = CharacterDetailFragmentArgs.fromBundle(requireArguments()).characterId
        viewModel.getCharacterByIdUseCase(characterId)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.collectInLifeCycle(this) { renderState(it) }
        viewModel.events.collectInLifeCycle(this) { renderEvents(it) }
    }

    private fun renderState(state: CharacterDetailViewModel.State) =
        when (state) {
            is CharacterDetailViewModel.State.Loading -> if (state.showLoading) showLoading() else hideLoading()
            else -> {}
        }

    private fun renderEvents(event: CharacterDetailViewModel.Event) {
        when (event) {
            is CharacterDetailViewModel.Event.CharacterError -> {
                Toast.makeText(requireContext(), R.string.character_error, Toast.LENGTH_SHORT)
                    .show()
            }
            is CharacterDetailViewModel.Event.Error -> {
                Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading() {
        //TODO: it could you show a dialog or spinner
    }

    private fun hideLoading() {
        //TODO: Hide the dialog or spinner
    }
}