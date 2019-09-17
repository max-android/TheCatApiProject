package com.my_project.thecatapiproject.diffutil_callback_sample.adapters

import androidx.recyclerview.widget.DiffUtil
import com.my_project.thecatapiproject.diffutil_callback_sample.data.Noty

/**
 * Created Максим on 14.09.2019.
 * Copyright © Max
 */
class NotyUtils2 : DiffUtil.ItemCallback<Noty>() {

    override fun areItemsTheSame(oldItem: Noty, newItem: Noty): Boolean {
      return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Noty, newItem: Noty): Boolean {
       return  oldItem == newItem
    }

}
