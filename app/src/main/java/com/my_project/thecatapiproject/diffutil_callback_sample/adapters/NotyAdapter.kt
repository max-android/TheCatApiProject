package com.my_project.thecatapiproject.diffutil_callback_sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.my_project.thecatapiproject.R
import com.my_project.thecatapiproject.diffutil_callback_sample.data.Noty
import timber.log.Timber

/**
 * Created Максим on 13.09.2019.
 * Copyright © Max
 */
class NotyAdapter : RecyclerView.Adapter<NotyAdapter.NotifyHolder>() {

    private val items: MutableList<Noty> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noty, parent, false)
        return NotifyHolder(view)
    }

    override fun onBindViewHolder(holder: NotifyHolder, position: Int) {
        val noty = items[position]
        //для фиксации - какие элементы обновляются при появлении новых данных
        Timber.tag("--SAMPLE").i(position.toString())
        holder.bindTo(noty)
    }

    override fun getItemCount() = items.size

    fun swapItems(notify: List<Noty>){

        //вариант из статьи
//        val diffResult = DiffUtil.calculateDiff(NotyUtils(this.items, notify))
//        diffResult.dispatchUpdatesTo(this)
//        items.clear()
//        items.addAll(notify)

        val diffResult = DiffUtil.calculateDiff(NotyUtils(this.items, notify))
        items.clear()
        items.addAll(notify)
        diffResult.dispatchUpdatesTo(this)
    }

    //TODO убрать
//        fun setData(notify: List<Notification>) {
//            items.clear()
//            items.addAll(notify)
//            notifyDataSetChanged()
//        }

//    fun updateItem(position: Int, notify: Notification) {
//        items[position] = notify
//        notifyItemChanged(position)
//    }
//
//    fun insertItem(position: Int, notify: Notification) {
//        items.add(position, notify)
//        notifyItemInserted(position)
//    }
//
//    fun deleteItem(notify: Notification) {
//        val position = items.indexOf(notify)
//        items.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    fun clear() {
//        items.clear()
//        notifyDataSetChanged()
//    }

    inner class NotifyHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {
        private var idTextView = containerView.findViewById(R.id.idTextView) as TextView
        private var nameTextView = containerView.findViewById(R.id.nameTextView) as TextView
        private var numberTextView = containerView.findViewById(R.id.numberTextView) as TextView


        fun bindTo(notify: Noty) = with(notify) {
            idTextView.text = id.toString()
            nameTextView.text = name
            numberTextView.text = number.toString()
        }
    }
}