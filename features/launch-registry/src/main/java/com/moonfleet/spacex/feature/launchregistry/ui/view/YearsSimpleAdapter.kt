package com.moonfleet.spacex.feature.launchregistry.ui.view

import android.view.LayoutInflater
import com.moonfleet.spacex.coreui.adapter.SimpleAdapter
import com.moonfleet.spacex.feature.launchregistry.databinding.ItemYearBinding
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchYear

class YearsSimpleAdapter(): SimpleAdapter<LaunchYear, ItemYearBinding>(
    { parent, _ ->
        ItemYearBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    },
    { _, binding, item ->
        binding.checkboxYear.setOnCheckedChangeListener(null)
        binding.launchYear = item
        binding.executePendingBindings()
        binding.checkboxYear.setOnCheckedChangeListener { _, isChecked -> item.selected = isChecked }
        binding.root
    },
    { item ->
    }
)