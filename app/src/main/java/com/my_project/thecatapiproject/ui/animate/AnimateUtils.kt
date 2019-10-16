package com.my_project.thecatapiproject.ui.animate

import androidx.recyclerview.widget.DiffUtil

/**
 * Created Максим on 15.10.2019.
 * Copyright © Max
 */
class AnimateUtils (
    private val oldList: List<AnimateEntity>,
    private val newList: List<AnimateEntity>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean {
        val per = oldList[oldItemPosition].name == newList[newItemPosition].name
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