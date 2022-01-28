package com.latinos.mobiletest.features.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.latinos.mobiletest.R

abstract class BaseFragment : Fragment() {
    protected fun setupToolbar(toolbar: Toolbar, collapsingToolbar: CollapsingToolbarLayout) {
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        NavigationUI.setupWithNavController(
            collapsingToolbar,
            toolbar,
            findNavController(),
            AppBarConfiguration.Builder(R.id.navigation_character_paged, R.id.navigation_about)
                .build()
        )
    }

    protected fun setupToolbarUp(toolbar: Toolbar, collapsingToolbar: CollapsingToolbarLayout) {
        val navController = findNavController()
        collapsingToolbar.setupWithNavController(
            toolbar,
            navController,
            AppBarConfiguration(navController.graph)
        )
    }
}