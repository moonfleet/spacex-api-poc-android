package com.moonfleet.spacex.feature.launchregistry.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moonfleet.spacex.feature.launchregistry.databinding.LayoutLinksBottomsheetBinding
import com.moonfleet.spacex.feature.launchregistry.ui.model.Links

class ModalBottomSheetFragment() : BottomSheetDialogFragment() {

    private var _binding: LayoutLinksBottomsheetBinding? = null
    private val binding: LayoutLinksBottomsheetBinding get() = _binding!!
    private lateinit var links: Links

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        links = arguments?.get(KEY_LINKS) as Links
        _binding = LayoutLinksBottomsheetBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            linkUrls = links
            bottomSheetDialog = this@ModalBottomSheetFragment
        }
        return binding.root
    }

    fun onUrlClick(url: String) {
        openUrl(url)
        dismiss()
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        activity?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
        private const val KEY_LINKS = "links"

        fun newInstance(links: Links): ModalBottomSheetFragment {
            val args = Bundle()
            args.putParcelable(KEY_LINKS, links)
            val fragment = ModalBottomSheetFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
