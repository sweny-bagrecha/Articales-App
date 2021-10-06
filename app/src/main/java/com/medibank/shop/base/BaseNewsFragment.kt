package com.medibank.shop.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.medibank.shop.R
import com.medibank.shop.utils.showAlertDialog

/**
 * Base trip fragment
 *
 * Base class used to handle all UI functionality common
 * across fragments in the news journey
 *
 * It works in conjunction with BaseTripViewModel
 *
 * Functionality supported
 *
 * 1. Presentation of Api errors
 *
 */
abstract class BaseNewsFragment : Fragment() {

    abstract val baseViewModel: BaseNewsViewModel
    private var mLoadingSpinner: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseViewModel.loadingSpinner.observe(viewLifecycleOwner)
        { isLoading ->
            mLoadingSpinner?.dismiss()
            if (isLoading) {
                mLoadingSpinner = showLoadingSpinner()
            }
        }

        baseViewModel.apiError.observe(viewLifecycleOwner)
        {
            showAlertDialog(
                requireContext(),
                getString(R.string.error),
                it
            )
        }

    }

    /**
     * Display Android ProgressBar inside alert dialog
     */
    private fun showLoadingSpinner() =
        MaterialAlertDialogBuilder(requireContext(), R.style.BlurredDialogStyle)
            .setView(layoutInflater.inflate(R.layout.loading_spinner, null))
            .setCancelable(false)
            .setBackground(ColorDrawable(Color.TRANSPARENT))
            .show()

}
