package com.example.mobiledevelopmentcourselabapp.presentation.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.databinding.ItemAdCardBinding
import com.example.mobiledevelopmentcourselabapp.databinding.ItemCardBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.AdUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.ItemUiModel

class CardAdapter(
    private val onCardClicked: (CardUiModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ItemUiModel> = arrayListOf()

    fun updateItems(newItems: List<ItemUiModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == CARD_ID) {
            val binding =
                ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CardHolder(binding)
        } else {
            val binding =
                ItemAdCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (item is CardUiModel && holder is CardHolder) {
            holder.bind(item)

            holder.setOnClickListener {
                onCardClicked.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size


    class CardHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: CardUiModel) {
            binding.name.text = card.formattedName.toString()
            binding.price.text = card.formattedPrice
            binding.additionField.isVisible = card.isExpanded
            binding.frequency.text = card.formattedFrequency
            binding.power.text = card.formattedPower
            binding.memory.text = card.formattedMemory

            Glide
                .with(itemView)
                .load(card.photoUrl)
                .into(binding.icon)
        }

        fun bindExpansionState(card: CardUiModel) {
            binding.additionField.isVisible = card.isExpanded
        }

        fun setOnClickListener(function: () -> Unit) {
            binding.root.setOnClickListener { function.invoke() }
        }
    }

    class AdHolder(binding: ItemAdCardBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        const val CARD_ID = 0
        const val AD_ID = 1
    }
}
