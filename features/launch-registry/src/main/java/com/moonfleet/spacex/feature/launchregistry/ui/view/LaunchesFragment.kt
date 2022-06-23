package com.moonfleet.spacex.feature.launchregistry.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.moonfleet.spacex.coreui.base.BaseActivity
import com.moonfleet.spacex.coreui.base.BaseFragment
import com.moonfleet.spacex.feature.launchregistry.R
import com.moonfleet.spacex.feature.launchregistry.databinding.FragmentLaunchesBinding
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchesAction
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchesEffect
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchesState
import com.moonfleet.spacex.feature.launchregistry.ui.viewmodel.LaunchesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class LaunchesFragment : BaseFragment() {

    private val launchesViewModel: LaunchesViewModel by activityViewModels<LaunchesViewModel>()

    private val launchesAdapter: LaunchesSimpleAdapter by lazy {
        LaunchesSimpleAdapter(launchesViewModel::onLaunchClick)
    }

    private var _binding: FragmentLaunchesBinding? = null
    private val binding: FragmentLaunchesBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = launchesViewModel
            adapter = launchesAdapter
        }
        binding.recyclerViewLaunches.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            launchesViewModel.state.collect { state ->
                onState(state)
            }
        }
        launchesViewModel.observeEffects(this, ::onEffect)
        launchesViewModel.action(LaunchesAction.OnScreenLoaded)
    }

    private fun onEffect(effect: LaunchesEffect) {
        when(effect) {
            is LaunchesEffect.BottomSheet -> {
                with(effect.links) {
                    if(articleUrl == null && wikiUrl == null && videoUrl == null) {
                        snackbar(getString(R.string.no_url_resources))
                    } else {
                        val modalBottomSheet = ModalBottomSheetFragment.newInstance(this)
                        modalBottomSheet.show(parentFragmentManager, ModalBottomSheetFragment.TAG)
                    }
                }
            }
            is LaunchesEffect.Filter -> {
                openFilterDialog()
            }
        }
    }

    private fun onState(state: LaunchesState) {
        when(state) {
            is LaunchesState.Loading -> {
                toBusy()
                binding.recyclerViewLaunches.visibility = View.INVISIBLE
                binding.layoutProgress.visibility = View.VISIBLE
            }
            is LaunchesState.Loaded -> {
                toIdle()
                launchesAdapter.items = state.launches.toMutableList()
                binding.recyclerViewLaunches.visibility = View.VISIBLE
                binding.layoutProgress.visibility = View.INVISIBLE
            }
            is LaunchesState.Error -> {
                Timber.e("onError ${state.error}")
                toIdle()
                binding.recyclerViewLaunches.visibility = View.INVISIBLE
                binding.layoutProgress.visibility = View.INVISIBLE
                (activity as BaseActivity).snackbar(state.error)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openFilterDialog() {
        val newFragment = FilterDialog()
        newFragment.show(parentFragmentManager, FilterDialog::class.java.simpleName)
    }

}