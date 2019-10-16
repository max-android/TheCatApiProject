package com.my_project.thecatapiproject.ui.animate

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.item_animate_sample.view.*
import timber.log.Timber


/**
 * Created Максим on 15.10.2019.
 * Copyright © Max
 */
class AnimateSampleAdapter: RecyclerView.Adapter<AnimateSampleAdapter.AnimateSampleHolder>() {

    private val items: MutableList<AnimateEntity> = mutableListOf()
    private var action: (AnimateEntity)-> Unit = { }

    //var DURATION = 500L
    var DURATION = 200L
    private var on_attach = true

    fun onItemClick(action: (AnimateEntity) -> Unit){
        this.action = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimateSampleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.my_project.thecatapiproject.R.layout.item_animate_sample, parent, false)
        return AnimateSampleHolder(view)
    }

    override fun onBindViewHolder(holder: AnimateSampleHolder, position: Int) {
        val noty = items[position]
        holder.bindTo(noty)
        //setAnimation(holder.itemView, position)
        //setAnimationFromLeftToRight(holder.itemView, position)
        setAnimationFromRightToLeft(holder.itemView, position)
        var isExpanded =  false
        holder.itemView.arrowImageView.setOnClickListener {
            isExpanded = !isExpanded
       toggleLayout(isExpanded, it,holder.itemView.infoTextView)

        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                on_attach = false
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemCount() = items.size

    fun swapItems(notify: List<AnimateEntity>){
        val diffResult = DiffUtil.calculateDiff(AnimateUtils(this.items, notify))
        items.clear()
        items.addAll(notify)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun setAnimation(itemView: View, position: Int) {
        var i = position
        if (!on_attach) {
            i = -1
        }
        val isNotFirstItem = i == -1
        i++
        itemView.alpha = 0f
        val animatorSet = AnimatorSet()
        val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 0.5f, 1.0f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        animator.startDelay = if (isNotFirstItem) DURATION / 2 else i * DURATION / 3
        animator.duration = 500
        animatorSet.play(animator)
        animator.start()
    }


    private fun setAnimationFromLeftToRight(itemView: View, position: Int) {
        var i = position
        if (!on_attach) {
            i = -1
        }
        val not_first_item = i == -1
        i++
        itemView.translationX = -400f
        itemView.alpha = 0f
        val animatorSet = AnimatorSet()
        val animatorTranslateY = ObjectAnimator.ofFloat(itemView, "translationX", -400f, 0f)
        val animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        animatorTranslateY.startDelay = if (not_first_item) DURATION else i * DURATION
        animatorTranslateY.duration = (if (not_first_item) 2 else 1) * DURATION
        animatorSet.playTogether(animatorTranslateY, animatorAlpha)
        animatorSet.start()
    }

    private fun setAnimationFromRightToLeft(itemView: View, position: Int) {
        var i = position
        if (!on_attach) {
            i = -1
        }
        val not_first_item = i == -1
        i++
        itemView.translationX = itemView.x + 400
        itemView.alpha = 0f
        val animatorSet = AnimatorSet()
        val animatorTranslateY =
            ObjectAnimator.ofFloat(itemView, "translationX", itemView.x + 400, 0f)
        val animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        animatorTranslateY.setStartDelay(if (not_first_item) DURATION else i * DURATION)
        animatorTranslateY.setDuration((if (not_first_item) 2 else 1) * DURATION)
        animatorSet.playTogether(animatorTranslateY, animatorAlpha)
        animatorSet.start()
    }

    //анимация нажатия на кнопку
    private fun toggleLayout(isExpanded: Boolean, v: View, layoutExpand:View): Boolean {
        MyAnimations().toggleArrow(v, isExpanded)
        if (isExpanded) {
            Timber.tag("--PRESS").i(isExpanded.toString())
            MyAnimations().expand(layoutExpand)
        } else {
            Timber.tag("--PRESS").i(isExpanded.toString())
            MyAnimations().collapse(layoutExpand)
        }
        return isExpanded
    }

    inner class AnimateSampleHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {
        private var personImageView = containerView.findViewById(com.my_project.thecatapiproject.R.id.personImageView) as SimpleDraweeView
        private var personTextView = containerView.findViewById(com.my_project.thecatapiproject.R.id.personTextView) as TextView
        private var infoTextView = containerView.findViewById(com.my_project.thecatapiproject.R.id.infoTextView) as TextView

        init {
            containerView.setOnClickListener { action(items[layoutPosition]) }
        }

        fun bindTo(notify: AnimateEntity) = with(notify) {
            personImageView.setImageURI(url)
            personTextView.text = name
            infoTextView.text = info
        }
    }
}