package com.my_project.thecatapiproject.diffutil_callback_sample.adapters

import androidx.recyclerview.widget.DiffUtil
import com.my_project.thecatapiproject.diffutil_callback_sample.data.MyType

/**
 * Created Максим on 13.09.2019.
 * Copyright © Max
 */
class TypesDiffCallback(
private val oldList: List<MyType>,
private val newList: List<MyType>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean {

        val per = oldList[oldItemPosition].getItemId() == newList[newItemPosition].getItemId()

       // Timber.tag("--SAMPLE").i(per.toString())

        return per
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}