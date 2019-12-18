package br.com.soccer.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.soccer.common.extensions.visible
import br.com.soccer.domain.soccer.DescriptionPeriod
import br.com.soccer.domain.soccer.GameDescription
import br.com.soccer.feature.R
import kotlinx.android.synthetic.main.item_game_description.view.*

class GameDescriptionAdapter(private val items: List<GameDescription>): RecyclerView.Adapter<GameDescriptionAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game_description, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        with(holder.itemView) {
            tvTitle.apply {
                if (!item.title.isNullOrEmpty()) {
                    text = item.title
                    this.visible()
                }
            }
            tvPeriod.apply {
                    text = item.period?.periodValue
                    this.visible()
            }
            tvMoment.apply {
                if (!item.moment.isNullOrEmpty()) {
                    text = item.moment
                    this.visible()
                }
            }
            tvDescription.apply {
                if (!item.text.isNullOrEmpty()) {
                    text = item.text
                    this.visible()
                }
            }
        }
    }


    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view)
}