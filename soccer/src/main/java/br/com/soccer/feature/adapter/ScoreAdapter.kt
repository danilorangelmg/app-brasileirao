package br.com.soccer.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.soccer.common.extensions.loadImageUrl
import br.com.soccer.domain.soccer.Classification
import br.com.soccer.domain.soccer.Turn
import br.com.soccer.feature.R
import kotlinx.android.synthetic.main.item_soccer_table.view.*
import java.util.*

class ScoreAdapter: RecyclerView.Adapter<ScoreAdapter.ItemViewHolder>() {

    private val items = LinkedList<Classification>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_soccer_table, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = items[position]
        with(holder.itemView) {
            tvTeamName.text = item.name.trim()
            tvTeamPoint.text = resources.getString(R.string.field_value, "${item.points.toString().trim()} Pts")
            ivShield.loadImageUrl(item.shieldUlr)
        }
    }

    fun addItems(newItems: List<Classification>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}