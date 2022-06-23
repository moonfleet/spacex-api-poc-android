package com.moonfleet.spacex.feature.launchregistry.ui.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.moonfleet.spacex.feature.launchregistry.R
import com.moonfleet.spacex.feature.launchregistry.asYearsMap
import com.moonfleet.spacex.feature.launchregistry.databinding.FragmentFilterBinding
import com.moonfleet.spacex.feature.launchregistry.toYearsList
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchFilter
import com.moonfleet.spacex.feature.launchregistry.ui.viewmodel.LaunchesViewModel

class FilterDialog : DialogFragment() {

    private val launchesViewModel: LaunchesViewModel by activityViewModels<LaunchesViewModel>()

    private var _binding: FragmentFilterBinding? = null
    private val binding: FragmentFilterBinding get() = _binding!!

    var savedStateLaunchFilter : LaunchFilter? = null

    private val yearsAdapter: YearsSimpleAdapter by lazy {
        YearsSimpleAdapter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_ORDER, binding.switchOrder.isChecked)
        when(binding.groupSuccess.checkedRadioButtonId) {
            R.id.button_show_successful -> outState.putBoolean(KEY_SUCCESS_STATUS, true)
            R.id.button_show_unsuccessful -> outState.putBoolean(KEY_SUCCESS_STATUS, false)
            else -> {}
        }
        outState.putParcelableArray(KEY_LAUNCH_YEARS_ARRAY, yearsAdapter.items.toTypedArray())
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if(savedInstanceState != null) {
            val yearsParcelableArray = savedInstanceState.getParcelableArray(KEY_LAUNCH_YEARS_ARRAY)
            savedStateLaunchFilter = LaunchFilter(
                yearsParcelableArray.asYearsMap(), savedInstanceState.getBoolean(
                    KEY_SUCCESS_STATUS
                ), savedInstanceState.getBoolean(KEY_ORDER)
            )
        } else {
            savedStateLaunchFilter = null
        }
        yearsAdapter.items = (savedStateLaunchFilter?.years ?: launchesViewModel.launchFilter.value?.years).toYearsList()
        _binding = FragmentFilterBinding.inflate(requireActivity().layoutInflater, null, false).apply {
            launchFilter = savedStateLaunchFilter ?: launchesViewModel.launchFilter.value
            adapter = yearsAdapter
        }

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(binding.root)
                .setPositiveButton(R.string.apply) { dialog, id ->
                    val newAscendingOrder = binding.switchOrder.isChecked
                    val newSuccessStatus = when(binding.groupSuccess.checkedRadioButtonId) {
                        R.id.button_show_all -> null
                        R.id.button_show_successful -> true
                        R.id.button_show_unsuccessful -> false
                        else -> false
                    }
                    launchesViewModel.updateFilter(yearsAdapter.items, newAscendingOrder = newAscendingOrder, newSuccessStatus = newSuccessStatus)
                    dismiss()
                }
                .setNegativeButton(R.string.cancel) { dialog, id -> dismiss() }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val KEY_ORDER = "order"
        val KEY_SUCCESS_STATUS = "successstatus"
        val KEY_LAUNCH_YEARS_ARRAY = "launchyearslist"
    }
}