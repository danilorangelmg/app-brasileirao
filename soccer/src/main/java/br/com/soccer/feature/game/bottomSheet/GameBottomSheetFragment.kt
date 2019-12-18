package br.com.soccer.feature.game.bottomSheet

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.soccer.common.extensions.configBottomSheet
import br.com.soccer.common.extensions.loadImageUrl
import br.com.soccer.common.extensions.parseInternationalDateToBr
import br.com.soccer.domain.soccer.GameTurn
import br.com.soccer.feature.R
import br.com.soccer.feature.adapter.GameDescriptionAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottomsheet.*
import kotlinx.android.synthetic.main.fragment_bottomsheet.progressView
import kotlinx.android.synthetic.main.fragment_bottomsheet.rootView
import kotlinx.android.synthetic.main.item_game.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameBottomSheetFragment : BottomSheetDialogFragment() {

    private val viewModel: GameBottomSheetViewModel by viewModel()

    companion object {
        const val GAME_ID = "game_id"
        fun newInstance(game: GameTurn): GameBottomSheetFragment =
            GameBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_ID, game)
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeLiveDatas()
        val game = arguments?.getParcelable<GameTurn>(GAME_ID)
        setupHeader(game)
        viewModel.load(game?.id ?: 0)
    }

    override fun onResume() {
        super.onResume()
        configBottomSheet()

        //setting max height of bottom sheet
        view?.minimumHeight = (Resources.getSystem().getDisplayMetrics().heightPixels) / 2

    }

    private fun initializeLiveDatas() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner, Observer {
            progressView.visibility = View.GONE
            rootView.visibility = View.VISIBLE
            rvDescription.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = GameDescriptionAdapter(it)
            }
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun setupHeader(item: GameTurn?) {
        with(headerView) {
            item?.apply {
                this.teams.apply {
                    this?.let { teams ->
                        teams.home.let {
                            ivShield.loadImageUrl(it.shieldUlr)
                            tvTeamName.text = it.nickname
                        }
                        teams.guest.let {
                            ivShieldVisiting.loadImageUrl(it.shieldUlr)
                            tvTeamVisiting.text = it.nickname
                        }
                    }
                }
                tvTeamCount.text = (this.homeScore ?: 0).toString()
                tvTeamVisitingCount.text = (this.guestScore ?: 0).toString()

                val data = this.gameDate?.substring(0, 10)?.parseInternationalDateToBr()

                val text = this.gameHour?.let { "$data - ${this.gameHour}" } ?: data

                tvDate.text = context.getString(br.com.soccer.feature.R.string.field_value, text)
                tvStadium.text = this.stadium?.name ?: ""
            }
        }
    }

}