package br.com.soccer.common.extensions

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun BottomSheetDialogFragment.configBottomSheet() {
    val bottomSheetBehavior = BottomSheetBehavior.from(view?.parent as View)

    bottomSheetBehavior.apply {
        isHideable = true
        state = BottomSheetBehavior.STATE_EXPANDED
        peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
    }

}