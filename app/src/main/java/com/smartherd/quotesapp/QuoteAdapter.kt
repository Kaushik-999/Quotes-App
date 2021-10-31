package com.smartherd.quotesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val quoteItem: TextView = itemView.findViewById(R.id.quote_item)
    val quoteAuthor: TextView = itemView.findViewById(R.id.quote_author)
}


class QuoteAdapter(): RecyclerView.Adapter<QuoteViewHolder>() {

    private val items: ArrayList<Quotes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)

        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val currItem = items[position]

        holder.quoteItem.text = currItem.text
        val author = currItem.author
        holder.quoteAuthor.text = "~ $author"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateQuotes(updatedQuotes: ArrayList<Quotes>){
        items.clear()
        items.addAll(updatedQuotes)
        notifyDataSetChanged()
    }

}