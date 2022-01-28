package com.latinos.mobiletest.features.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.latinos.data.utils.collectInLifeCycle
import com.latinos.domain.characters.model.CharacterErrorModel
import com.latinos.mobiletest.R
import com.latinos.mobiletest.databinding.FragmentCharacterDetailBinding
import com.latinos.mobiletest.features.base.BaseFragment
import com.latinos.mobiletest.features.base.setTranslucentStatusBar
import com.latinos.mobiletest.features.base.state.StateView
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
        requireActivity().setTranslucentStatusBar(true)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val characterId = CharacterDetailFragmentArgs.fromBundle(requireArguments()).characterId
        viewModel.getCharacterById(characterId)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.collectInLifeCycle(this) { renderState(it) }
        viewModel.events.collectInLifeCycle(this) { renderEvents(it) }
    }

    private fun renderState(state: StateView) =
        when (state) {
            is StateView.Loading -> if (state.showLoading) showLoading() else hideLoading()
            is StateView.Error -> {}
            is StateView.Success -> {}
            StateView.Idle -> {}
        }

    private fun renderEvents(event: CharacterDetailViewModel.Event) {
        when (event) {
            is CharacterDetailViewModel.Event.CharacterError -> {
                renderEventCharacterError(event.error)
            }
            is CharacterDetailViewModel.Event.Error -> showToast(R.string.generic_error)
            CharacterDetailViewModel.Event.NavigateToComicList -> { /*TODO: navigate to Comic List*/
            }
            CharacterDetailViewModel.Event.NavigateToEventList -> { /*TODO: navigate to Event List*/
            }
            CharacterDetailViewModel.Event.NavigateToSeriesList -> { /*TODO: navigate to Series List*/
            }
        }
    }

    private fun renderEventCharacterError(error: CharacterErrorModel) {
        when (error) {
            CharacterErrorModel.CharacterNotExits -> showToast(R.string.character_error_not_found)
            CharacterErrorModel.Generic -> showToast(R.string.character_error)
        }
    }

    private fun showLoading() {
        //TODO: it could you show a dialog or spinner
    }

    private fun hideLoading() {
        //TODO: Hide the dialog or spinner
    }
}