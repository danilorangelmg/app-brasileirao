package br.com.soccer.feature.error

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.soccer.common.extensions.configBottomSheet
import br.com.soccer.feature.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottomsheet_error.*

class ErrorBottomSheetFragment : BottomSheetDialogFragment() {

    var callback: ((ErrorBottomSheetFragment) -> Unit)? = null

    companion object {
        const val MESSAGE = "MESSAGE"
        fun newInstance(it: String): ErrorBottomSheetFragment = ErrorBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(MESSAGE, it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btTryAgain.setOnClickListener {
            callback?.let {
                it(this)
            }
        }

        tvMessage.text = arguments?.getString(MESSAGE)
    }

    override fun onResume() {
        super.onResume()
        configBottomSheet()

        //setting max height of bottom sheet
        view?.minimumHeight = (Resources.getSystem().displayMetrics.heightPixels)
    }

    fun addCallback(callback: (ErrorBottomSheetFragment) -> Unit): ErrorBottomSheetFragment {
        this.callback = callback
        return this
    }

}