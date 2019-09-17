package com.my_project.thecatapiproject.ui.cat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.my_project.thecatapiproject.cat_api_sample.pojo.ItemCategory



/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */
class ImagesAdapter (private val action: (ItemCategory) -> Unit): RecyclerView.Adapter<ImagesAdapter.NotifyHolder>() {

    private val items: MutableList<ItemCategory> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.my_project.thecatapiproject.R.layout.item_category, parent, false)
        return NotifyHolder(view)
    }

    override fun onBindViewHolder(holder: NotifyHolder, position: Int) {
        val user = items[position]
        holder.bindTo(user)
    }

    override fun getItemCount() = items.size

        fun setData(notify: List<ItemCategory>) {
            items.clear()
            items.addAll(notify)
            notifyDataSetChanged()
        }

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
        private var idTextView = containerView.findViewById(com.my_project.thecatapiproject.R.id.idTextView) as AppCompatTextView
        private var imageView = containerView.findViewById(com.my_project.thecatapiproject.R.id.catImageView) as SimpleDraweeView
        init {
            containerView.setOnClickListener { action(items[layoutPosition]) }
//            imageView.setController(
//                Fresco.newDraweeControllerBuilder()
//                    .setOldController( imageView.getController())
//                    .setImageRequest(
//                        ImageRequestBuilder.newBuilderWithSource(uri)
//                            .setResizeOptions(ResizeOptions(50, 50))
//                            .build()
//                    )
//                    .build())
        }
        fun bindTo(notify: ItemCategory) = with(notify) {
            idTextView.text = id
            imageView.setImageURI(url)

          //  imageView.setImageURI(Uri.parse("https://cdn2.thecatapi.com/images/4s9.jpg"))
        }
    }
}