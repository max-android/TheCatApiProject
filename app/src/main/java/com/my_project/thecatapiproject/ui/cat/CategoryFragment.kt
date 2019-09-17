package com.my_project.thecatapiproject.ui.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my_project.thecatapiproject.R
import com.my_project.thecatapiproject.cat_api_sample.TYPE_MENU_KEY
import com.my_project.thecatapiproject.cat_api_sample.pojo.Category
import kotlinx.android.synthetic.main.fragment_category.*
import timber.log.Timber

/**
 * Created Максим on 16.09.2019.
 * Copyright © Max
 */
class CategoryFragment:Fragment() {

    companion object {
        fun newInstance(type: Category): CategoryFragment {
            val fragment = CategoryFragment()
            val args = Bundle()
            args.putSerializable(TYPE_MENU_KEY, type)
            fragment.arguments = args
            return fragment
        }
    }

    private var item: Category? = null
    private lateinit var viewModel: CategoryViewModel
    private lateinit var myAdapter: ImagesAdapter
    private var screen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        initData()
        init()

        if(savedInstanceState == null && !screen){
            item?.let { viewModel.itemCategory(it.id.toString()) }
        }
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        screen = true
    }


    private fun initData() {
        item = arguments?.getSerializable(TYPE_MENU_KEY) as? Category
        Timber.tag("--CAT-initData()").i(item?.name)
    }

    private fun init() {
        myAdapter = ImagesAdapter { }

        imageRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            adapter = myAdapter
        }
    }

    private fun observeData(){
        viewModel.itemsCatLiveData.observe(this, Observer {
                state->
            state?.let {
                when(it){
                    is ResultItem -> {
                        myAdapter.setData(it.list)
                        it.list.forEach {item->
                            Timber.tag("--CAT-setData").i(item.id)
                            Timber.tag("--CAT-setData").i(item.url)
                        }
                    }
                    is ErrorItem -> Timber.tag("--CAT").i(it.error)
                }
            }
        })
    }

}