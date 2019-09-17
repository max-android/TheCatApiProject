package com.my_project.thecatapiproject.diffutil_callback_sample.adapters

import androidx.recyclerview.widget.DiffUtil
import com.my_project.thecatapiproject.diffutil_callback_sample.data.Noty

/**
 * Created Максим on 13.09.2019.
 * Copyright © Max
 */
class NotyUtils (
    private val oldList: List<Noty>,
    private val newList: List<Noty>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean {
        val per = oldList[oldItemPosition].id == newList[newItemPosition].id
        return per
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean{
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

//    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
//        return super.getChangePayload(oldItemPosition, newItemPosition)
//    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}