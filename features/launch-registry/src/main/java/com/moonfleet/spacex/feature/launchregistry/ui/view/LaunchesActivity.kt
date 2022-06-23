package com.moonfleet.spacex.feature.launchregistry.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.moonfleet.spacex.coreui.base.BaseActivity
import com.moonfleet.spacex.feature.launchregistry.R
import com.moonfleet.spacex.feature.launchregistry.databinding.ActivityLaunchesBinding
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchesEffect
import com.moonfleet.spacex.feature.launchregistry.ui.viewmodel.LaunchesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LaunchesActivity : BaseActivity() {

    private val launchesViewModel: LaunchesViewModel by viewModels()
    var filterMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLaunchesBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.setGraph(R.navigation.nav_graph_launches)
        }
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launchesViewModel.launchFilter.collect { launchFilter ->
                    Timber.e("onLaunchFilter update: $launchFilter")
                    filterMenuItem?.isVisible = launchFilter != null
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_launches, menu)
        filterMenuItem = menu.findItem(R.id.action_filter)
        filterMenuItem?.isVisible = launchesViewModel.launchFilter.value != null
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                launchesViewModel.onActionFilterClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}