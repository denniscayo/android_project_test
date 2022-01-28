package com.latinos.mobiletest.features.character.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.graphics.ColorUtils.blendARGB
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.latinos.data.utils.collectInLifeCycle
import com.latinos.mobiletest.R
import com.latinos.mobiletest.databinding.FragmentCharacterDetailBinding
import com.latinos.mobiletest.features.base.AppBarOffsetListener
import com.latinos.mobiletest.features.base.isNightModeActive
import com.latinos.mobiletest.features.base.setTranslucentStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(), AppBarOffsetListener.OnScrollStateListener {

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
        setupToolbar()
        setExpandedToolbar(true)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val characterId = CharacterDetailFragmentArgs.fromBundle(requireArguments()).characterId
        viewModel.getCharacterByIdUseCase(characterId)
        observeViewModel()
    }

    private fun setExpandedToolbar(expanded: Boolean) {
        binding.collapsedTitle.alpha = if (expanded) 0f else 1f
        binding.appbar.setExpanded(expanded, false)
        if (expanded) binding.toolbar.setUpButtonColor(Color.WHITE)
        requireActivity().setTranslucentStatusBar(expanded)
    }

    private fun setupToolbar() {
        val navController = findNavController()
        binding.collapsingToolbarLayout.setupWithNavController(
            binding.toolbar,
            navController,
            AppBarConfiguration(navController.graph)
        )
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
            is CharacterDetailViewModel.Event.ErrorCharacter -> {
                Toast.makeText(requireContext(), event.text, Toast.LENGTH_SHORT).show()

            }
            is CharacterDetailViewModel.Event.CharacterError -> {

            }
            is CharacterDetailViewModel.Event.Error -> {
            }
        }
    }

    fun MaterialToolbar?.setUpButtonColor(@ColorInt color: Int) {
        if (this == null) return
        navigationIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
            color,
            BlendModeCompat.SRC_ATOP)
    }

    override fun onScrollStateChangedListener(scrollState: AppBarOffsetListener.ScrollState) {
        val scrolled = scrollState.scrolledPercentile // (0..1)

        // the higher up the scrim trigger is, the faster the fade animation must be
        val speedMultiplier = 1 / (1 - SCRIM_TRIGGER_THRESHOLD)
        val fadeInAlpha = (scrolled - SCRIM_TRIGGER_THRESHOLD) * speedMultiplier

        binding.collapsingToolbarLayout.setScrimsShown(scrolled >= SCRIM_TRIGGER_THRESHOLD)
        binding.expandedTitle.alpha = 1 - scrolled // fade out
        binding.collapsedTitle.alpha = fadeInAlpha

        // only animate up-arrow color in light mode from white to black in collapsed mode
        if (!isNightModeActive() && scrollState is AppBarOffsetListener.ScrollState.Expanded) {
            // if you swipe too fast, sometimes the button doesn't become fully white. This enforces it.
            binding.toolbar.setUpButtonColor(Color.WHITE)
        } else if (!isNightModeActive() && scrolled >= SCRIM_TRIGGER_THRESHOLD) {
            binding.toolbar.setUpButtonColor(blendARGB(Color.WHITE, Color.BLACK, fadeInAlpha))
        }
        requireActivity().setTranslucentStatusBar(scrolled < SCRIM_TRIGGER_THRESHOLD)
    }

    private fun showLoading() {
        //TODO: Implement
    }

    private fun hideLoading() {
//TODO: Implement
    }

    companion object {
        private const val SCRIM_TRIGGER_THRESHOLD = 0.75f // 3 quarter ways up
    }
}