package br.com.soccer.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.soccer.common.extensions.gone
import br.com.soccer.common.extensions.visible
import br.com.soccer.feature.R
import br.com.soccer.feature.adapter.ScoreAdapter
import br.com.soccer.feature.error.ErrorBottomSheetFragment
import br.com.soccer.feature.helper.showBackButtonToolbar
import br.com.soccer.feature.helper.showGameButtonToolbar
import br.com.soccer.feature.helper.updateToolbarTitle
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        updateToolbarTitle(R.string.title_score)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initializeLiveData()
        return layoutInflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeViews()
    }

    override fun onResume() {
        super.onResume()
        showBackButtonToolbar(false)
        showGameButtonToolbar(true)
    }

    private fun initializeLiveData() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner, Observer {
            progressView.gone()
            rootView.visibility = View.VISIBLE
            (rvTableList.adapter as ScoreAdapter).addItems(it)
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            progressView.gone()
            ErrorBottomSheetFragment.newInstance(it).addCallback { bottomSheet ->
                progressView.visible()
                viewModel.loadScore()
                bottomSheet.dismiss()
            }.show(childFragmentManager, "Error")
        })
    }

    private fun initializeViews() {
        rvTableList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ScoreAdapter()
        }
    }
}