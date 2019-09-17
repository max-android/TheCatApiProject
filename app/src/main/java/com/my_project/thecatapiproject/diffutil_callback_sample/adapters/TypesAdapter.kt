package com.my_project.thecatapiproject.diffutil_callback_sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.my_project.thecatapiproject.R
import com.my_project.thecatapiproject.diffutil_callback_sample.data.MyType
import com.my_project.thecatapiproject.diffutil_callback_sample.data.MyType1
import com.my_project.thecatapiproject.diffutil_callback_sample.data.MyType2
import com.my_project.thecatapiproject.diffutil_callback_sample.data.MyType3
import timber.log.Timber

/**
 * Created Максим on 13.09.2019.
 * Copyright © Max
 */
//class TypesAdapter (private val action: (MyType) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
class TypesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<MyType> = mutableListOf()

    private var action: (MyType)-> Unit = { }

    fun onItemClick(action: (MyType) -> Unit){
        this.action = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
           val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            1 -> Type1Holder(inflater.inflate(R.layout.item_type_1, parent, false))
            2 -> Type2Holder(inflater.inflate(R.layout.item_type_2, parent, false))
            3 -> Type3Holder(inflater.inflate(R.layout.item_type_3, parent, false))
            else -> throw Exception("unknow viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
               is MyType1 -> 1
               is MyType2 -> 2
               is MyType3 -> 3
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //для фиксации - какие элементы обновляются при появлении новых данных
        Timber.tag("--SAMPLE").i(position.toString())
        val myType = items[position]
        when(holder){
            is Type1Holder -> holder.bindTo(myType as MyType1)
            is Type2Holder -> holder.bindTo(myType as MyType2)
            is Type3Holder -> holder.bindTo(myType as MyType3)
        }
    }

    override fun getItemCount() = items.size

    fun swapItems(newItems: List<MyType>){
        val diffResult = DiffUtil.calculateDiff(TypesDiffCallback(this.items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    //TODO убрать
//        fun setData(notify: List<Notification>) {
//            items.clear()
//            items.addAll(notify)
//            notifyDataSetChanged()
//        }

//    fun updateItem(position: Int, notify: MyType) {
//        items[position] = notify
//        notifyItemChanged(position)
//    }
//
//    fun insertItem(position: Int, notify: MyType) {
//        items.add(position, notify)
//        notifyItemInserted(position)
//    }
//
//    fun deleteItem(notify: MyType) {
//        val position = items.indexOf(notify)
//        items.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    fun clear() {
//        items.clear()
//        notifyDataSetChanged()
//    }

   private inner class Type1Holder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        private var titleTextView = containerView.findViewById(R.id.titleTextView) as AppCompatTextView
        private var descTextView = containerView.findViewById(R.id.descTextView) as AppCompatTextView

        init {
            containerView.setOnClickListener { action(items[layoutPosition]) }
        }
        fun bindTo(type: MyType1) = with(type) {
            titleTextView.text = title
            descTextView.text = description
        }
    }

   private inner class Type2Holder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        private var info2ImageView = containerView.findViewById(R.id.info2ImageView) as SimpleDraweeView
        private var info2TextView = containerView.findViewById(R.id.info2TextView) as AppCompatTextView

        init {
            containerView.setOnClickListener { action(items[layoutPosition]) }
        }

        fun bindTo(type: MyType2) = with(type) {
            info2ImageView.setImageURI(url2)
            info2TextView.text = info2
        }
    }

   private inner class Type3Holder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        private var info3ImageView = containerView.findViewById(R.id.info3ImageView) as SimpleDraweeView
        private var info3TextView = containerView.findViewById(R.id.info3TextView) as AppCompatTextView

        init {
            containerView.setOnClickListener { action(items[layoutPosition]) }
        }

        fun bindTo(type: MyType3) = with(type) {
            info3ImageView.setImageURI(url3)
            info3TextView.text = info3
        }
    }
}