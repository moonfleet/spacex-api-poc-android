package com.moonfleet.spacex.feature.launchregistry.ui.view

import android.view.LayoutInflater
import com.moonfleet.spacex.coreui.adapter.SimpleAdapter
import com.moonfleet.spacex.feature.launchregistry.databinding.ItemLaunchBinding
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchUIModel

class LaunchesSimpleAdapter(onItemClick: (LaunchUIModel) -> Unit): SimpleAdapter<LaunchUIModel, ItemLaunchBinding>(
    { parent, _ ->
        ItemLaunchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    },
    { _, binding, item ->
        binding.uiModel = item
        binding.executePendingBindings()
        binding.root
    },
    onItemClick
)