package br.com.soccer.feature.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.soccer.domain.soccer.GameTurn
import br.com.soccer.feature.R
import br.com.soccer.feature.adapter.TurnAdapter
import br.com.soccer.feature.game.bottomSheet.GameBottomSheetFragment
import br.com.soccer.feature.helper.showBackButtonToolbar
import br.com.soccer.feature.helper.showGameButtonToolbar
import br.com.soccer.feature.helper.updateToolbarTitle
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.fragment_home.progressView
import kotlinx.android.synthetic.main.fragment_home.rootView
import kotlinx.android.synthetic.main.fragment_home.rvTableList
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {

    val viewModel: GameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        updateToolbarTitle(R.string.title_score)
        showGameButtonToolbar(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeLiveData()
        initializeViews()
    }

    override fun onResume() {
        super.onResume()
        showBackButtonToolbar(true)
    }

    private fun initializeLiveData() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner, Observer {
            progressView.visibility = View.GONE
            rootView.visibility = View.VISIBLE
            (rvTableList.adapter as TurnAdapter).addItems(it)
        })

    }

    private fun initializeViews() {
        rvTableList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TurnAdapter(::changeTitle, ::onTurnClick)

            ivBack.setOnClickListener {
                (rvTableList.adapter as TurnAdapter).back()
            }
            ivNext.setOnClickListener {
                (rvTableList.adapter as TurnAdapter).next()
            }
        }
    }

    private fun changeTitle(round: Int) {
        tvTitle.text = resources.getString(R.string.field_value, "Rodada ${round + 1}")
    }

    private fun onTurnClick(item: GameTurn) {
        GameBottomSheetFragment.newInstance(item).show(childFragmentManager, "MyBottomSheet")
    }
}