package br.com.soccer.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.soccer.common.extensions.loadImageUrl
import br.com.soccer.common.extensions.parseInternationalDateToBr
import br.com.soccer.domain.soccer.GameTurn
import br.com.soccer.domain.soccer.Turn
import br.com.soccer.feature.R
import kotlinx.android.synthetic.main.item_game.view.*
import kotlinx.android.synthetic.main.item_soccer_table.view.ivShield
import kotlinx.android.synthetic.main.item_soccer_table.view.tvTeamName
import java.text.SimpleDateFormat
import java.util.*

class TurnAdapter(val callback: (Int) -> Unit, val callbackItemClick: (GameTurn) -> Unit): RecyclerView.Adapter<TurnAdapter.ItemViewHolder>() {

    private val items = LinkedList<GameTurn>()
    private val turns = LinkedList<Turn>()
    private var position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = items[position]
        holder.itemView.setOnClickListener(null)
        with(holder.itemView) {
            item.apply {
                this.teams.apply {
                    this?.let {
                        home.let {
                            ivShield.loadImageUrl(it.shieldUlr)
                            tvTeamName.text = it.nickname
                        }
                        guest.let {
                            ivShieldVisiting.loadImageUrl(it.shieldUlr)
                            tvTeamVisiting.text = it.nickname
                        }
                    }
                }
                tvTeamCount.text = (this.homeScore?:0).toString()
                tvTeamVisitingCount.text = (this.guestScore?:0).toString()

                val data = this.gameDate?.substring(0, 10)?.parseInternationalDateToBr()

                val canClick = run {
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(data).before(Date())
                }

                val text = this.gameHour?.let { "$data - ${this.gameHour}"}?: data

                tvDate.text = context.getString(R.string.field_value, text)
                tvStadium.text = this.stadium?.name ?:""
                println(this.id)

                if (canClick) {
                    holder.itemView.setOnClickListener {
                        callbackItemClick(this)
                    }
                }
            }
        }
    }

    fun addItems(turnsItems: List<Turn>) {
        turns.addAll(turnsItems)
        items.clear()
        items.addAll(turns[position].games)
        notifyDataSetChanged()
    }

    fun next() {
        position++
        if (position >= turns.size) {
            return
        }
        items.clear()
        items.addAll(turns[position].games)
        notifyDataSetChanged()
        callback(position)
    }

    fun back() {
        position--
        if (position < 0) {
            position = 0
        }

        items.clear()
        items.addAll(turns[position].games)
        notifyDataSetChanged()
        callback(position)
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}