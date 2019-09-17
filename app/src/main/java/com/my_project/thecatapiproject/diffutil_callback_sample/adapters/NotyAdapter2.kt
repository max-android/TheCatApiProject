package com.my_project.thecatapiproject.diffutil_callback_sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my_project.thecatapiproject.R
import com.my_project.thecatapiproject.diffutil_callback_sample.data.Noty
import timber.log.Timber

/**
 * Created Максим on 13.09.2019.
 * Copyright © Max
 */

class NotyAdapter2 : ListAdapter<Noty,  NotyAdapter2.NotifyHolder2>(NotyUtils2()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyHolder2 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noty, parent, false)
        return NotifyHolder2(view)
    }

    override fun onBindViewHolder(holder: NotifyHolder2, position: Int) {
        //для фиксации - какие элементы обновляются при появлении новых данных
        Timber.tag("--SAMPLE").i(position.toString())
        holder.bindTo(getItem(position))
    }

    inner class NotifyHolder2(private val containerView: View) : RecyclerView.ViewHolder(containerView) {
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