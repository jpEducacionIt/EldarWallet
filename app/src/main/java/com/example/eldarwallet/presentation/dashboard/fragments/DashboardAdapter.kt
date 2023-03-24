package com.example.eldarwallet.presentation.dashboard.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.R
import com.example.eldarwallet.infrastructure.representation.UserCardsData

class DashboardAdapter : ListAdapter<UserCardsData, DashboardAdapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (UserCardsData) -> Unit

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val creditCardNumber: TextView = view.findViewById(R.id.item_list_card_number)
        private val creditCardImage: ImageView = view.findViewById(R.id.itemListCarLogo)

        fun bind (card: UserCardsData) {
            creditCardNumber.text = card.number

            val image = when(card.typeCard) {
                "3" -> R.drawable.amex
                "4" -> R.drawable.mastercard
                else -> {R.drawable.visa}
            }

            creditCardImage.setImageResource(image)

            view.setOnClickListener {
                onItemClickListener(card)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardAdapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardAdapter.ViewHolder, position: Int) {
        val userData = getItem(position)
        holder.bind(userData)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<UserCardsData>() {
        override fun areItemsTheSame(oldItem: UserCardsData, newItem: UserCardsData): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserCardsData, newItem: UserCardsData): Boolean {
            return oldItem == newItem
        }
    }
}